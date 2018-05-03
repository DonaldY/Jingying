package cn.jingying.cart.service;

import cn.jingying.cart.dao.CartDao;
import cn.jingying.cart.model.Cart;
import cn.jingying.common.Const;
import cn.jingying.common.ResponseCode;
import cn.jingying.common.ServerResponse;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;

/**
 * Created by DonaldY on 2018/3/15.
 */
public class CartService {
    
    private final CartDao cartDao = new CartDao();
    
    public ServerResponse<List<Record>> add(Integer userId, Integer productId, Integer count) {
        
        if (productId == null || count == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        Record cartVo = this.cartDao.queryCartByUserIdAndProductId(userId, productId);
        
        if (null == cartVo) {
            // 购物车中没有这个产品
            Cart cartItem = new Cart();
            cartItem.set("quantity",count);
            cartItem.set("check", Const.Cart.CHECKED);
            cartItem.set("productId",productId);
            cartItem.set("userId", userId);
            
        } else {
            count += cartVo.getInt("quantity");
            cartVo.set("quantity", count);
        }
        return this.list(userId);
    }
    
    public ServerResponse<List<Record>> list(Integer userId) {
        List<Record> cartVoList = this.cartDao.getCartListByUid(userId);
        return ServerResponse.createBySuccess(cartVoList);
    }
    
}
