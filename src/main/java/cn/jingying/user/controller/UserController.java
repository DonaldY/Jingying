package cn.jingying.user.controller;

import cn.jingying.common.Const;
import cn.jingying.common.ResponseCode;
import cn.jingying.common.ServerResponse;
import cn.jingying.user.model.User;
import cn.jingying.user.service.UserService;
import com.jfinal.core.Controller;

/**
 * Created by DonaldY on 2018/3/12.
 */
public class UserController extends Controller{
    
    private UserService userService = new UserService();
    
    public void index() {
        User user = new User().dao().findById(1);
        renderJson(user);
    }
    
    public void login() {
        User user = getModel(User.class,"", true);
        String username = user.getStr("username");
        String password = user.getStr("password");
        ServerResponse<User> response = this.userService.login(username, password);
        
        // TODO: 去session
        if (response.isSuccess()) {
            setSessionAttr(Const.CURRENT_USER, response.getData());
        }
        renderJson(response);
    }
    
    public void quit() {
        removeSessionAttr(Const.CURRENT_USER);
        renderJson(ServerResponse.createBySuccess());
    }
    
    public void regist() {
        // modelName前缀为空
        User formUser = getModel(User.class, "");
        
    }
    
    public void getInfo() {
        User user = getSessionAttr(Const.CURRENT_USER);
        if (user == null) {
            renderJson(ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录"));
        }
        renderJson(this.userService.getInfo((Integer) user.get("id")));
    }
}
