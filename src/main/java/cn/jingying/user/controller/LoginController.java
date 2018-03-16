package cn.jingying.user.controller;

import com.jfinal.core.Controller;
import com.jfinal.qyweixin.sdk.api.*;
import com.jfinal.qyweixin.sdk.utils.HttpUtils;


/**
 * Created by DonaldY on 2018/3/8.
 */
public class LoginController extends Controller{
    
    public void index() {
        String backUrl = "http://qyf9az.natappfree.cc/Jingying/wechatOAuth/callBack";
        // TODO: 企业认证后，才能使用把false改true(snsapi_base 改为了 snsapi_userinfo)
        String url = OAuthApi.getCodeUrl(backUrl, "STATE", false);
        redirect(url);
    }
    
    public void callBack() {
        String code = getPara("code");
        
        // TODO: 企业认证后，可尝试使用OAuthApi.getUserInfoByCode();  
        ApiConfig kit = ApiConfigKit.getApiConfig();
        String getUserInfoUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + kit.getCorpId() +
            "&secret=" + kit.getCorpSecret() +
            "&code=" + code +
            "&grant_type=authorization_code";
        String jsonResult = HttpUtils.get(getUserInfoUrl);
        ApiResult apiResult = new ApiResult(jsonResult);
        System.out.println("openid: " + apiResult.get("openid"));
        
        String getInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + apiResult.get("access_token") +
            "&openid=" + apiResult.get("openid") + 
            "&lang=zh_CN";
        String _jsonResult = HttpUtils.get(getInfoUrl);
        System.out.println(_jsonResult);
        
        render("wechat.html");
    }
    
}
