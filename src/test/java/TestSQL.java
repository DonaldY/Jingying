import cn.jingying.utils.DateTimeUtil;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by DonaldY on 2018/4/21.
 */
public class TestSQL {
    
    @Test
    public void testInsert() {
        String [] categoryNameArr = {"PPR管材管件", "PVC管材管件", "PE管材管件", "五金类", "波纹管"};
        List<String> categoryList = Arrays.asList(categoryNameArr);
        StringBuilder buffer = null;
        for (String categoryName : categoryList) {
            buffer = new StringBuilder("INSERT INTO j_category(parent_id, name, create_time) VALUES ('");
            buffer.append(0);
            buffer.append("','");
            buffer.append(categoryName);
            buffer.append("','");
            buffer.append(DateTimeUtil.dateToStr(new Date()));
            buffer.append("');");
            System.out.println(buffer.toString());
        }
        
    }
}
