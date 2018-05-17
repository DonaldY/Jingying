package cn.jingying.cart.service;

import cn.jingying.cart.dao.CartDao;
import cn.jingying.cart.model.Cart;
import cn.jingying.common.Const;
import cn.jingying.common.ResponseCode;
import cn.jingying.common.ServerResponse;
import com.jfinal.plugin.activerecord.Record;

import java.util.Collections;
import java.util.List;

/**
 * Created by DonaldY on 2018/3/15.
 */
public class CartService {
    
    private final CartDao cartDao = new CartDao();

    public ServerResponse add(Integer userId, Integer skuId, Integer count) {
        
        if (skuId == null || count == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode
                .ILLEGAL_ARGUMENT.getDesc());
        }

        Cart cart = this.cartDao.queryCartByUserIdAndSkuId(userId, skuId);
        
        if (null == cart) {
            // 购物车中没有这个产品
            Cart cartItem = new Cart();
            cartItem.set("quantity",count);
            cartItem.set("checked", Const.Cart.CHECKED);
            cartItem.set("sku_id",skuId);
            cartItem.set("user_id", userId);
            this.cartDao.insertCart(cartItem);
        } else if (!cart.getBoolean("status")) {
            /**
              购物车中有，但是status = 0
              即曾经存在过，后有删除
             */
            cart.set("quantity", count);
            cart.set("status", true);
            this.cartDao.updateCartById(cart);
        } else {
            count += cart.getInt("quantity");
            cart.set("quantity", count);
            this.cartDao.updateCartById(cart);
        }
        return ServerResponse.createBySuccess("添加成功");
    }
    
    public ServerResponse<List<Record>> list(Integer userId) {
        List<Record> cartVoList = this.cartDao.getCartListByUid(userId);
        return ServerResponse.createBySuccess(cartVoList);
    }

    public ServerResponse updateCheckedById(Integer id, Integer cartId, Integer status) {
        int isSuccess = this.cartDao.updateSelectedById(id, cartId, status);
        if (isSuccess == 1) {
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }

    public ServerResponse deleteCartById(Integer id) {
        int count = this.cartDao.deleteCartById(id);
        if (count > 0) {
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }

    public ServerResponse<List<Record>> getCheckedCartList(Integer userId) {
        List list = this.cartDao.queryCheckedCartList(userId);
        if (list.isEmpty()) {
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess(list);
    }

    /*public ServerResponse<Integer> getCartItemNum() {
        this.cartDao.queryCartItemNum()
        return cartItemNum;
    }*/
}
