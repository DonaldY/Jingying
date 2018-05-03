package cn.jingying.cart.controller;

import cn.jingying.cart.service.CartService;
import cn.jingying.common.Const;
import cn.jingying.common.ServerResponse;
import cn.jingying.interceptor.user.UserInterceptor;
import cn.jingying.user.model.User;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;

/**
 * Created by DonaldY on 2018/3/15.
 */
@Before(UserInterceptor.class)
public class CartController extends Controller{
    private final CartService cartService = new CartService();
    
    public void list() {
        User user = getSessionAttr(Const.CURRENT_USER);
        ServerResponse<List<Record>> cartServerResponse = this.cartService.list(user.getInt("id"));
        setAttr("cartList", cartServerResponse.getData());
        render("cart.html");
    }
}
