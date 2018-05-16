package cn.jingying.cart.dao;

import cn.jingying.cart.model.Cart;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;

/**
 * Created by DonaldY on 2018/3/15.
 */
public class CartDao {
    private final Cart dao = new Cart().dao();


    public List<Cart> findCartByUid(Integer userId) {
        String sql = "SELECT id, product_id FROM j_cart WHERE user_id=? AND status=1";
        return this.dao.find(sql, userId);
    }

    public List<Record> getCartListByUid(Integer userId) {
        String sql = "SELECT c.id, c.quantity, c.checked, c.sku_id, k.spec_name, k.stock, p.name, p.main_image, " +
            "k.price FROM j_cart c, j_product_sku k, j_product_spu p WHERE user_id=? AND c.sku_id=k.id " +
            "AND k.spu_id=p.id AND c.status=1";
        return Db.find(sql, userId);
    }

    public Cart queryCartByUserIdAndSkuId(Integer userId, Integer skuId) {
        String sql = "SELECT id, quantity FROM j_cart WHERE user_id=? AND sku_id=? AND status=1";
        return this.dao.findFirst(sql, userId, skuId);
    }

    public void insertCart(Cart cart) {
        cart.save();
    }

    public void updateCartById(Cart cart) {
        cart.update();
    }

    public int updateSelectedById(Integer id, Integer cartId, Integer status) {
        String sql = "UPDATE j_cart SET checked=? WHERE user_id=? AND id=? AND status=1";
        return Db.update(sql, status, id, cartId);
    }

    public int deleteCartById(Integer id) {
        String sql = "UPDATE j_cart SET status=0 WHERE id=? AND status=1";
        return Db.update(sql, id);
    }

    public List queryCheckedCartList(Integer userId) {
        String sql = "SELECT c.id, c.quantity, c.sku_id, k.spec_name, k.stock, p.name, p.main_image, " +
            "k.price FROM j_cart c, j_product_sku k, j_product_spu p WHERE user_id=? AND c.sku_id=k.id " +
            "AND k.spu_id=p.id AND c.status=1 AND c.checked=1";
        return Db.find(sql, userId);
    }
}
