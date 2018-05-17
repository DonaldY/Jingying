package cn.jingying.order.controller;

import cn.jingying.address.service.AddressService;
import cn.jingying.common.Const;
import cn.jingying.common.ServerResponse;
import cn.jingying.interceptor.user.UserInterceptor;
import cn.jingying.order.service.OrderService;
import cn.jingying.product.service.ProductService;
import cn.jingying.user.model.User;
import cn.jingying.utils.BigDecimalUtil;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;

import java.math.BigDecimal;
import java.util.List;


/**
 * Created by DonaldY on 2018/3/8.
 */
@Before(UserInterceptor.class)
public class OrderController extends Controller{
    
    private final OrderService orderService = new OrderService();
    private final ProductService productService = new ProductService();
    private final AddressService addressService = new AddressService();
    
    public void index() {
       render("center.html");
    }

    /**
     * 确认订单
     * 单个商品购买
     */
    public void add() {
        Integer skuId = getParaToInt("skuId");
        Integer count = getParaToInt("count");
        User user = getSessionAttr(Const.CURRENT_USER);
        ServerResponse serverResponse = this.productService.getProductBySkuId(skuId);
        ServerResponse addressResponnse = this.addressService.getCheckedAddress(user.getInt("id"));

        setAttr("address", addressResponnse.getData());
        
        if (serverResponse.isSuccess()) {
            List<Record> list  = (List) serverResponse.getData();
            for (Record record : list) {
                record.set("quantity", count);
            }
            double totalPrice = calculateTotalPrice(list);
            setAttr("list", list);
            setAttr("totalCount", 1);
            setAttr("totalPrice", totalPrice);
            render("submit.html");
        } else {
            renderError(404);
        }
    }

    /**
     * 确认订单
     * 根据购物车checked
     */
    public void confirm() {
        User user = getSessionAttr(Const.CURRENT_USER);
        ServerResponse serverResponse = this.productService.getProductFromCartByUid(user.getInt("id"));
        ServerResponse addressResponnse = this.addressService.getCheckedAddress(user.getInt("id"));
        
        setAttr("address", addressResponnse.getData());
        
        if (serverResponse.isSuccess()) {
            List list = (List) serverResponse.getData();
            double totalPrice = calculateTotalPrice(list);
            Integer totalCount = calculateTotalCount(list);
            setAttr("list", list);
            setAttr("totalCount", totalCount);
            setAttr("totalPrice", totalPrice);
            render("submit.html");
        } else {
            renderError(404);
        }
    }
    
    private double calculateTotalPrice(List<Record> list) {
        BigDecimal totalPrice = new BigDecimal("0");
        for (Record record : list) {
            double price = record.getDouble("price");
            price *= record.getInt("quantity");
            totalPrice = BigDecimalUtil.add(totalPrice.doubleValue(), price);
        }
        return totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    private Integer calculateTotalCount(List<Record> list) {
        Integer cnt = 0;
        for (Record record : list) {
            cnt++;
        }
        return cnt;
    }

    /**
     * 创建订单
     */
    public void create() {
        User user = getSessionAttr(Const.CURRENT_USER);
        Integer addressId = getParaToInt("addressId");
        ServerResponse serverResponse = this.orderService.createOrder(user.getInt("id"), addressId);
        if (serverResponse.isSuccess()) {
            setAttr("order", serverResponse.getData());
            System.out.println(serverResponse.getData());
            render("pay.html");
        } else {
            renderError(500);
        }
    }
}
