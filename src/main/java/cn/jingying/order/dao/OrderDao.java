package cn.jingying.order.dao;

import cn.jingying.order.model.Order;
import cn.jingying.order.model.OrderItem;
import cn.jingying.utils.DateTimeUtil;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Created by DonaldY on 2018/5/14.
 */
public class OrderDao {
    
    private final Order orderDao = new Order().dao();
    private final OrderItem orderItemDao = new OrderItem().dao();

    public boolean insertOrder(Order order) {
        return order.save();
    }

    public void insertOrderItemList(List<OrderItem> orderItemList) {
        for (OrderItem item : orderItemList) {
            item.save();
        }
    }

    public List<Record> queryAllOrderList(Integer userId) {
        String sql = "SELECT id, order_no, payment, postage, status FROM j_order WHERE user_id=?";
        return Db.find(sql, userId);
    }

    public List<Record> queryOrderListByStatus(Integer userId, Integer status) {
        String sql = "SELECT id, order_no, payment, postage, status FROM j_order WHERE user_id=? AND status=?";
        return Db.find(sql, userId, status);
    }

    public List<Record> queryOrderItemListByOrderNo(Integer userId, Long order_no) {
        String sql = "SELECT i.id, i.sku_id, i.product_name, i.product_image, i.curr_unit_price, i.quantity, " +
            "i.total_price, k.spec_name FROM j_order_item i, j_product_sku k " +
            "WHERE user_id=? AND order_no=? AND k.id=i.sku_id";
        return Db.find(sql, userId, order_no);
    }

    /**
     * 查询可继续支付的订单
     * @param id
     * @param orderNo
     * @return
     */
    public Order findContinueOrderByOno(Integer id, Long orderNo) {
        String sql = "SELECT id, order_no, payment FROM j_order WHERE user_id=? AND order_no=?";
        return this.orderDao.findFirst(sql, id, orderNo);
    }

    public int closeOrderByOid(Integer id, Long orderNo) {
        String nowDate = DateTimeUtil.dateToStr(new Date());
        String sql = "UPDATE j_order SET status=60, close_time=? WHERE user_id=? AND order_no=? AND status=10";
        return Db.update(sql, nowDate, id, orderNo);
    }
}
