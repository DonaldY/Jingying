package cn.jingying.order.service;

import cn.jingying.cart.model.Cart;
import cn.jingying.cart.service.CartService;
import cn.jingying.common.Const;
import cn.jingying.common.ServerResponse;
import cn.jingying.order.dao.OrderDao;
import cn.jingying.order.model.Order;
import cn.jingying.order.model.OrderItem;
import cn.jingying.product.model.ProductSku;
import cn.jingying.product.service.ProductService;
import cn.jingying.utils.BigDecimalUtil;
import cn.jingying.utils.DateTimeUtil;
import com.jfinal.plugin.activerecord.Record;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by DonaldY on 2018/5/9.
 */
public class OrderService {

    private final CartService cartService = new CartService();
    private final ProductService productService = new ProductService();
    private final OrderDao orderDao = new OrderDao();
    
    public ServerResponse createOrder(Integer userId, Integer addressId) {
        
        // 从购物车中获取数据(已选择的)
        List<Record> cartList = this.cartService.getCheckedCartList(userId).getData();
        
        // 计算这个订单的总价
        ServerResponse serverResponse = this.getCartOrderItem(userId, cartList);
        if (!serverResponse.isSuccess()) {
            return serverResponse;
        }
        
        List<OrderItem> orderItemList = (List<OrderItem>) serverResponse.getData();
        BigDecimal payment = this.getOrderTotalPrice(orderItemList);
        
        // 生成订单
        Order order = this.assembleOrder(userId, addressId, payment);
        if (null == order) {
            return ServerResponse.createByErrorMessage("生成订单错误");
        }
        
        if (orderItemList.isEmpty()) {
            return ServerResponse.createByErrorMessage("购物车为空");
        }
        
        // 设置订单号
        for (OrderItem orderItem : orderItemList) {
            orderItem.set("order_no", order.getLong("order_no"));
        }
        
        // 批量插入
        this.orderDao.insertOrderItemList(orderItemList);
        
        // 清空下购物车
        this.cleanCart(cartList);
        // 返回前段
        
        return ServerResponse.createBySuccess(order);
    }

    private void cleanCart(List<Record> cartList) {
        for (Record cart : cartList) {
            this.cartService.deleteCartById(cart.getInt("id"));
        }
    }

    /**
     * 清空库存，支付完之后
     * @param orderItemList
     */
    private void reduceProductStock(List<OrderItem> orderItemList) {
        for (OrderItem orderItem : orderItemList) {
            ProductSku productSku = this.productService.getProductSkuBySkuId(orderItem.getInt("sku_id"));
            productSku.set("stock", productSku.getInt("stock"));
            this.productService.updateProductSku(productSku);
        }
    }

    private Order assembleOrder(Integer userId, Integer addressId, BigDecimal payment) {
        Order order = new Order();
        long orderNo = this.generateOrderNo();
        order.set("order_no",orderNo);
        order.set("status", Const.OrderStatusEnum.NO_PAY.getCode());
        order.set("postage", 0);
        order.set("payment_type", Const.PaymentTypeEnum.ONLINE_PAY.getCode());
        order.set("payment", payment);

        order.set("user_id", userId);
        order.set("address_id", addressId);
        
        // 创建时间
        order.set("create_time", DateTimeUtil.dateToStr(new Date()));
        // 订单关闭时间
        order.set("end_time", DateTimeUtil.nextDayStr());
        
        boolean isSuccess = this.orderDao.insertOrder(order);
        if(isSuccess){
            return order;
        }
        return null;
    }


    private long generateOrderNo(){
        long currentTime =System.currentTimeMillis();
        return currentTime + new Random().nextInt(100);
    }


    private ServerResponse getCartOrderItem(Integer userId, List<Record> cartList) {
        List orderItemList = new ArrayList<OrderItem>();
        
        if (cartList.isEmpty()) {
            return ServerResponse.createByErrorMessage("购物车为空");
        }
        
        // 校验购物车的数据, 包括产品的状态和数量
        for (Record cartItem : cartList) {
            OrderItem orderItem = new OrderItem();
            Record product = this.productService.getProductBySkuId(cartItem.getInt("sku_id"))
                .getData().get(0);
            
            // 商品是否在售，在SQL中判断了
            
            // 校验库存
            if (cartItem.getInt("quantity") > product.getInt("stock")) {
                return ServerResponse.createByErrorMessage("产品" + product.getStr("name") + "库存不足");
            }
            
            orderItem.set("user_id", userId);
            orderItem.set("sku_id", product.getInt("id"));
            orderItem.set("product_name", product.getStr("name"));
            orderItem.set("product_image", product.getStr("main_image"));
            orderItem.set("curr_unit_price", product.getBigDecimal("price"));
            orderItem.set("quantity", cartItem.getInt("quantity"));
            orderItem.set("total_price", BigDecimalUtil.mul(product.getDouble("price").doubleValue(), cartItem
                .getInt("quantity")));
            orderItemList.add(orderItem);
        }
        return ServerResponse.createBySuccess(orderItemList);
    }
    
    private BigDecimal getOrderTotalPrice(List<OrderItem> orderItemList) {
        BigDecimal payment = new BigDecimal("0");
        for (OrderItem orderItem : orderItemList) {
            payment = BigDecimalUtil.add(payment.doubleValue(), orderItem.getDouble("total_price"));
        }
        return payment;
    }

    public ServerResponse queryListByStatus(Integer userId, Integer status) {
        Map map = new HashMap<Record, List<Record>>();
        List<Record> orderList = null;
        if (status == 0) {
            orderList = this.orderDao.queryAllOrderList(userId);
        } else {
            orderList = this.orderDao.queryOrderListByStatus(userId, status);
        }
        for (Record order : orderList) {
            List<Record> list = this.orderDao.queryOrderItemListByOrderNo(userId, order.getLong("order_no"));
            order.set("count", list.size());
            if (!list.isEmpty()) {
                map.put(order, list);
            }
        }
        return ServerResponse.createBySuccess(map);
    }

    public ServerResponse getContinueOrderByOno(Integer id, Long orderNo) {
        Order order = this.orderDao.findContinueOrderByOno(id, orderNo);
        if (null == order) {
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess(order);
    }

    public ServerResponse closeOrderByOid(Integer id, Long orderNo) {
        int count = this.orderDao.closeOrderByOid(id, orderNo);
        if (count == 0) {
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess();
    }
}
