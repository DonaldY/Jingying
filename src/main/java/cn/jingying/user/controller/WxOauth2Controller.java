package cn.jingying.user.controller;

import cn.jingying.common.Const;
import cn.jingying.common.ServerResponse;
import cn.jingying.user.model.User;
import cn.jingying.user.service.UserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;
import com.jfinal.qyweixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.SnsAccessToken;
import com.jfinal.weixin.sdk.api.SnsAccessTokenApi;
import com.jfinal.weixin.sdk.api.SnsApi;

import java.sql.Timestamp;


/**
 * Created by DonaldY on 2018/3/8.
 */
public class WxOauth2Controller extends Controller{
    
    private final UserService userService = new UserService();
    
    public void index() {
        String calbackUrl = PropKit.get("domain") + "/wechatOAuth/oauth2";
        String url = SnsAccessTokenApi.getAuthorizeURL(PropKit.get("corpId"), calbackUrl, "jingying", false);
        redirect(url);
    }
    
    public void oauth2() {
        
        String code = getPara("code");
        String state = getPara("state");
        
        if (null != code) {
            String coreId = ApiConfigKit.getApiConfig().getCorpId();
            String secret = ApiConfigKit.getApiConfig().getCorpSecret();

            SnsAccessToken snsAccessToken = SnsAccessTokenApi.getSnsAccessToken(coreId, secret, code);
            
            String token = snsAccessToken.getAccessToken();
            String openId = snsAccessToken.getOpenid();

            ServerResponse<User> serverResponse =  this.userService.wxOauthLogin(openId, token);
            User user = serverResponse.getData();
            
            if (!serverResponse.isSuccess()) {
                if ("not register".equals(serverResponse.getMsg())) {
                    user = remoteUserByTokenAndOpenId(token, openId);
                    if (null == user) {
                        // 貌似出了点小差
                        render("error.html");
                    }
                    this.userService.register(user);
                } else {
                    // token 失效了
                    render("error.html");
                }
            }

            setCookie("openId", openId, -1);

            System.out.println(user.toString());
            
            setSessionAttr(Const.CURRENT_USER, user);
            
            render("/WEB-INF/pages/index/index.html");
        }
        
        
    }
    
    private User remoteUserByTokenAndOpenId(String token, String openId) {
        ApiResult apiResult = SnsApi.getUserInfo(token, openId);

        if (apiResult.isSucceed()) {

            JSONObject jsonObject= JSON.parseObject(apiResult.getJson());
            String nickName = jsonObject.getString("nickname");
            int sex = jsonObject.getIntValue("sex");
            String headimgurl = jsonObject.getString("headimgurl");

            User user = new User();
            user.set("nickname", nickName);
            user.set("sex", sex);
            user.set("headimgurl", headimgurl);
            user.set("openid", openId);
            user.set("password", "123");
            user.set("username", "jy" + openId);
            user.set("token", token);

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            user.set("create_time", timestamp.toString());
            user.set("update_time", timestamp.toString());

            return user;
        }
        
        return new User();
    }

    
}
