package cn.jingying.cart.dao;

import cn.jingying.cart.model.Cart;
import cn.jingying.common.ServerResponse;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;

/**
 * Created by DonaldY on 2018/3/15.
 */
public class CartDao {
    private final Cart dao = new Cart().dao();


    public List<Cart> findCartByUid(Integer userId) {
        String sql = "SELECT id, product_id FROM j_cart WHERE user_id=?";
        return this.dao.find(sql, userId);
    }

    public void updateQuantityById(Cart cartForQuantity) {
        //UPDATE runoob_tbl SET runoob_title='学习 C++' WHERE runoob_id=3;
        String sql = "UPDATE j_cart SET ";
    }

    public List<Record> getCartListByUid(Integer userId) {
        String sql = "SELECT c.id, c.quantity, c.checked, c.sku_id, k.spec_name, k.stock, p.name, p.main_image FROM " +
            "j_cart c, j_product_sku k, j_product_spu p WHERE user_id=? AND c.sku_id=k.id AND k.spu_id=p.id";
        return Db.find(sql, userId);
    }

    public Record queryCartByUserIdAndProductId(Integer userId, Integer productId) {
        String sql = "SELECT id, quantity, checked, ";
        return null;
    }
}
