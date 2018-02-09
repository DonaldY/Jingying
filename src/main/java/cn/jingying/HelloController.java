package cn.jingying;

import com.jfinal.core.Controller;

/**
 * Created by DonaldY on 2018/2/8.
 */
public class HelloController extends Controller {
    public void index(){
        renderText("hello world! hello Donald!");
    }
}
