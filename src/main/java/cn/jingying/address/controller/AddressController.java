package cn.jingying.address.controller;

import cn.jingying.address.model.Address;
import cn.jingying.address.service.AddressService;
import cn.jingying.common.Const;
import cn.jingying.common.ServerResponse;
import cn.jingying.interceptor.user.UserInterceptor;
import cn.jingying.user.model.User;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

import javax.jnlp.IntegrationService;

/**
 * Created by DonaldY on 2018/3/18.
 */
@Before(UserInterceptor.class)
public class AddressController extends Controller {
    private final AddressService addressService = new AddressService();
    
    public void index() {
        render("address/add.html");
    }
    
    public void add() {
        User user = getSessionAttr(Const.CURRENT_USER);
        Integer userId = user.getInt("id");
        Address address = getModel(Address.class, "",true);
        ServerResponse serverResponse = this.addressService.add(userId, address);
        if (serverResponse.isSuccess()) {
            forwardAction("/order/confirm");
        } else {
            setAttr("msg", serverResponse.getMsg());
            renderJson();
        }
    }

    public void delete() {
        User user = getSessionAttr(Const.CURRENT_USER);
        Integer userId = user.getInt("id");
        Integer addressId = getParaToInt("addressId");
        renderJson(this.addressService.delete(userId, addressId));
    }
    
    public void edit() {
        User user = getSessionAttr(Const.CURRENT_USER);
        Integer userId = user.getInt("id");
        Integer id = getParaToInt("id");
        ServerResponse serverResponse = this.addressService.getAddressById(userId, id);
        if (serverResponse.isSuccess()) {
            setAttr("address", serverResponse.getData());
            render("address/edit.html");
        } else {
            setAttr("msg", serverResponse.getMsg());
            renderJson();
        }
    }
    
    public void update() {
        User user = getSessionAttr(Const.CURRENT_USER);
        Address address = getModel(Address.class, "",true);
        Integer userId = user.getInt("id");
        ServerResponse serverResponse = this.addressService.update(userId, address);
        if (serverResponse.isSuccess()) {
            forwardAction("/order/confirm");
        } else {
            setAttr("msg", serverResponse.getMsg());
            renderJson();
        }
    }
    
    public void list() {
        User user = getSessionAttr(Const.CURRENT_USER);
        Integer userId = user.getInt("id");
        Integer pageNum = getParaToInt("pageNumber");
        final Integer pageSize = 10;
        ServerResponse serverResponse = this.addressService.list(userId, pageNum, pageSize);
        System.out.println(serverResponse.getData());
        renderJson(serverResponse.getData());
    }
    
    public void updateChecked() {
        User user = getSessionAttr(Const.CURRENT_USER);
        Integer userId = user.getInt("id");
        Integer addressId = getParaToInt("addressId");
        ServerResponse serverResponse = this.addressService.updateChecked(userId, addressId);
        if (serverResponse.isSuccess()) {
            setAttr("msg", "更新成功");
        } else {
            setAttr("msg", "更新失败");
        }
        renderJson();
    }

    public void updateDefault() {
        User user = getSessionAttr(Const.CURRENT_USER);
        Integer userId = user.getInt("id");
        Integer addressId = getParaToInt("addressId");
        ServerResponse serverResponse = this.addressService.updateDefault(userId, addressId);
        if (serverResponse.isSuccess()) {
            setAttr("msg", "更新成功");
        } else {
            setAttr("msg", "更新失败");
        }
        renderJson();
    }
}
