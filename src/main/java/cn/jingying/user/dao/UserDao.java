package cn.jingying.user.dao;

import cn.jingying.user.model.User;

import java.util.List;

/**
 * Created by DonaldY on 2018/3/12.
 */
public class UserDao {
    private final User dao = new User().dao();
    
    public User findByName(String username) {
        return this.dao.findFirst("SELECT id, username, password FROM j_user WHERE username=?", username);
    }
    
    public User findById(Integer id) {
        return this.dao.findById(id);
    }

    public void addUser(User user) {
        user.save();
    }

    public User findByOpenId(String openId) {
        return this.dao.findFirst("SELECT id, nickname, headimgurl, token From j_user WHERE openid=?", openId);
    }
}
