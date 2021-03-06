package cn.jingying.index.controller;

import cn.jingying.cart.service.CartService;
import cn.jingying.category.model.Category;
import cn.jingying.category.service.CategoryService;
import cn.jingying.common.ServerResponse;
import cn.jingying.product.service.ProductService;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;
import java.util.Map;


/**
 * Created by DonaldY on 2018/3/8.
 */
public class IndexController extends Controller{
    
    private final ProductService productService = new ProductService();
    private final CartService cartService = new CartService();
    private final CategoryService categoryService = new CategoryService();
    
    public void index() {
        
        ServerResponse serverResponse = this.productService.getSpuList();
        if (serverResponse.isSuccess()) {
            Map<String, List<Record>> map = (Map<String, List<Record>>) serverResponse.getData();
            setAttr("map", map);
        }
        
        ServerResponse brandResponse = this.productService.getBrandList();
        if (brandResponse.isSuccess()) {
            List<Record> list = (List<Record>) brandResponse.getData();
            setAttr("brandList", list);
        }
        
        ServerResponse categoryResponse = this.categoryService.findSecondCategory();
        if (categoryResponse.isSuccess()) {
            Map<String, List<Category>> categoryMap = (Map<String, List<Category>>) categoryResponse.getData();
            System.out.println(categoryMap);
            setAttr("categoryMap", categoryMap);
        }
        
        /*ServerResponse cartResponse = this.cartService.getCartItemNum();
        if (cartResponse.isSuccess()) {
            Integer num = (Integer) cartResponse.getData();
            setAttr("cartItemNum", num);
        } else {
            setAttr("cartItemNum", 0);
        }*/
        
        render("index.html");
    }
    
}
