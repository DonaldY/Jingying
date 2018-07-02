import com.alibaba.fastjson.JSON;
import com.jfinal.plugin.activerecord.Page;
import org.junit.Test;

import java.util.Map;

/**
 * Created by DonaldY on 2018/3/9.
 */
public class TestJson {
    @Test
    public void testJson() {
        String str = "{\"0\":\"zhangsan\",\"1\":\"lisi\",\"2\":\"wangwu\",\"3\":\"maliu\"}";
        String str1 = "{\"access_token\":\"7_ic9iJbc55D31Xbhrnx5PBK5ghYed-q2oKQCLn38Fx_sHel47YPTXXvEuVjy0str9YopZk9U" +
            "--146uBn6p-HwXAjG5w7-UFdZ-ezssLjvJT8\",\"expires_in\":7200,\"refresh_token\":\"7_hzvonkJYKrAE6SKJvFZcsnBPyKKx6sTzw-hxXGcVkXK2ox4B4kB7VGO9Rqyv4wZuZs9uwIfdG8H6dcILGaiFMH2-cLkMwL7JQIXNfDr4-Z8\",\"openid\":\"oLlXc1DOhyREAJhCcHGKPJrowWAc\",\"scope\":\"snsapi_base\"}";
        //第一种方式  
        Map maps = (Map) JSON.parse(str1);
        System.out.println("这个是用JSON类来解析JSON字符串!!!");
        for (Object map : maps.entrySet()){
            System.out.println(((Map.Entry)map).getKey()+"     " + ((Map.Entry)map).getValue());
        }
    }
    
    @Test
    public void test() {
      
    }
}
