package cn.jingying.cart.controller;

import cn.jingying.cart.service.CartService;
import cn.jingying.common.Const;
import cn.jingying.common.ServerResponse;
import cn.jingying.interceptor.user.UserInterceptor;
import cn.jingying.user.model.User;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.mysql.fabric.Server;

import java.util.List;

/**
 * Created by DonaldY on 2018/3/15.
 */
@Before(UserInterceptor.class)
public class CartController extends Controller{
    private final CartService cartService = new CartService();
    
    public void index() {
        render("cart.html");
    }
    
    public void list() {
        User user = getSessionAttr(Const.CURRENT_USER);
        ServerResponse<List<Record>> cartServerResponse = this.cartService.list(user.getInt("id"));
        renderJson(cartServerResponse.getData());
    }
    
    public void add() {
        User user = getSessionAttr(Const.CURRENT_USER);
        Integer count = getParaToInt("count");
        Integer skuId = getParaToInt("skuId");
        ServerResponse serverResponse = this.cartService.add(user.getInt("id"), skuId, count);
        if (serverResponse.isSuccess()) {
            render("cart.html");
        } else {
            // TODO: 参数错误
            renderError(404);
        }
    }
    
    public void ajaxAdd() {
        User user = getSessionAttr(Const.CURRENT_USER);
        Integer count = getParaToInt("count");
        Integer skuId = getParaToInt("skuId");
        ServerResponse serverResponse = this.cartService.add(user.getInt("id"), skuId, count);
        if (serverResponse.isSuccess()) {
            setAttr("msg", 1);
        } else {
            setAttr("msg", 0);
        }
        renderJson();
    }
    
    public void updateChecked() {
        User user = getSessionAttr(Const.CURRENT_USER);
        Integer cartId = getParaToInt("cartId");
        Integer status = getParaToInt("status");
        ServerResponse serverResponse = this.cartService.updateCheckedById(user.getInt("id"), cartId, status);
        if (serverResponse.isSuccess()) {
            setAttr("success", 1);
        } else {
            setAttr("error", 0);
        }
        renderJson();
    }
}
