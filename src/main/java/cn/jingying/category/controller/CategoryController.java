package cn.jingying.category.controller;

import cn.jingying.category.model.Category;
import cn.jingying.category.service.CategoryService;
import cn.jingying.common.ServerResponse;
import com.jfinal.core.Controller;

import java.util.List;

/**
 * Created by DonaldY on 2018/3/13.
 */
public class CategoryController extends Controller {
    private final CategoryService categoryService = new CategoryService();
    
    public void getChildrenById() {
        Integer id = getParaToInt("pid");
        //ServerResponse<List<Category>> response = this.categoryService.findChildById(id);
        //renderJson(response);
    }
    
    public void getDeepChildById() {
        Integer id = getParaToInt("pid");
        ServerResponse<List<Category>> response = this.categoryService.findDeepChildById(id);
        renderJson(response);
    }
    
}
