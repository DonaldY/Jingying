package cn.jingying.interceptor.user;

import cn.jingying.common.Const;
import cn.jingying.user.model.User;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

/**
 * Created by DonaldY on 2018/4/15.
 */
public class UserInterceptor implements Interceptor{
    
    public void intercept(Invocation invocation) {
        Controller controller = invocation.getController();
        User user = controller.getSessionAttr(Const.CURRENT_USER);
        if (null == user) {
            String actionKey = invocation.getActionKey();
            controller.redirect("/login?from=" + actionKey);
        } else {
            invocation.invoke();
        }
    }
}
