package cn.jingying.user.service;

import cn.jingying.common.ServerResponse;
import cn.jingying.user.dao.UserDao;
import cn.jingying.user.model.User;
import cn.jingying.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;


/**
 * Created by DonaldY on 2018/3/12.
 */
public class UserService {
    private final UserDao userDao = new UserDao();
    
    public ServerResponse<User> login(String username, String password) {
        User user = this.userDao.findByName(username);
        
        if (null == user) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        
//        String md5Pwd = MD5Util.MD5EncodeUtf8(password);
//        user.set("password", md5Pwd);
        if (!password.equals(user.getStr("password"))) {
            return ServerResponse.createByErrorMessage("密码错误");
        }
        
        user.set("password", StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功", user);
    }
    
    public ServerResponse<String> register(User user) {
        // user.set("password", MD5Util.MD5EncodeUtf8(user.getStr("password")));
        this.userDao.addUser(user);
        return ServerResponse.createBySuccessMessage("注册成功");
    }
    
    public ServerResponse<User> getInfo(Integer id) {
        User user = this.userDao.findById(id);
        if (null == user) {
            return ServerResponse.createByErrorMessage("找不到当前用户");
        }
        user.set("password", StringUtils.EMPTY);
        return ServerResponse.createBySuccess(user);
    }

    public ServerResponse<User> wxOauthLogin(String openId) {
        User user = this.userDao.findByOpenId(openId);

        if (null == user) {
            return ServerResponse.createByErrorMessage("not register");
        }

        user.set("token", StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功", user);
    }
}
