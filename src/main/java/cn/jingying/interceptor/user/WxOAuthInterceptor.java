package cn.jingying.interceptor.user;

import cn.jingying.common.Const;
import cn.jingying.user.model.User;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

/**
 * Created by DonaldY on 2018/5/5.
 */
public class WxOAuthInterceptor implements Interceptor {

    public void intercept(Invocation invocation) {
        Controller controller = invocation.getController();
        User user = controller.getSessionAttr(Const.CURRENT_USER);
        if (null == user) {
            invocation.invoke();
        } else {
            controller.redirect("/user");
        }
    }
}
