package cn.jingying.order.dao;

import cn.jingying.order.model.Order;
import cn.jingying.order.model.OrderItem;

import java.util.List;

/**
 * Created by DonaldY on 2018/5/14.
 */
public class OrderDao {
    
    public boolean insertOrder(Order order) {
        return order.save();
    }

    public void insertOrderItemList(List<OrderItem> orderItemList) {
        for (OrderItem item : orderItemList) {
            item.save();
        }
    }
}
