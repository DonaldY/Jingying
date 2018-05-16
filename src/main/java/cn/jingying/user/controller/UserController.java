package cn.jingying.user.controller;

import cn.jingying.common.Const;
import cn.jingying.common.ResponseCode;
import cn.jingying.common.ServerResponse;
import cn.jingying.interceptor.user.UserInterceptor;
import cn.jingying.user.model.User;
import cn.jingying.user.service.UserService;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by DonaldY on 2018/3/12.
 */
@Before(UserInterceptor.class)
public class UserController extends Controller{
    
    private UserService userService = new UserService();

    @Clear
    public void index() {
        render("center.html");
    }
    
    @Clear
    @ActionKey("/login")
    public void login() {
        User user = getModel(User.class,"", true);
        String username = user.getStr("username");
        String password = user.getStr("password");
        String fromUrl = getPara("from");
        ServerResponse<User> response = this.userService.login(username, password);
        
        // TODO: 改分布式session
        if (response.isSuccess()) {
            setSessionAttr(Const.CURRENT_USER, response.getData());
            if (StringUtils.isNotBlank(fromUrl)) {
                redirect(fromUrl);
            } else {
                redirect("/user");
            }
            
        } else {
            setAttr("from", getPara("from"));
            render("login.html");
        }
        
    }

    @Clear
    public void regist() {
        // modelName前缀为空
        User formUser = getModel(User.class, "");

    }
    
    public void quit() {
        removeSessionAttr(Const.CURRENT_USER);
        redirect("/");
    }
    
    @ActionKey("/user/setting")
    public void getInfo() {
        User user = getSessionAttr(Const.CURRENT_USER);
        ServerResponse<User> userInfo = this.userService.getInfo((Integer) user.get("id"));
        if (userInfo.isSuccess()) {
            setAttr("userInfo", userInfo.getData());
            render("setting.html");
        } else {
            renderError(500);
        }
    }
    
}
