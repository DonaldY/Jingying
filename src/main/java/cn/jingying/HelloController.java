package cn.jingying;

import com.jfinal.core.Controller;

/**
 * Created by DonaldY on 2018/2/8.
 */
public class HelloController extends Controller {
    
    public void index(){
        User user = (User) new User().dao().findById(1);
        setAttr("user", user);
        render("text.html");
    }
}
