package cn.jingying.category.service;

import cn.jingying.category.dao.CategoryDao;
import cn.jingying.category.model.Category;
import cn.jingying.common.ServerResponse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by DonaldY on 2018/3/14.
 */
public class CategoryService {
    private final CategoryDao categoryDao = new CategoryDao();
    
    public ServerResponse<List<Category>> findChildByPid(Integer id) {
        List<Category> lists = this.categoryDao.findChildByPid(id);
        if (lists.isEmpty()) {
            // TODO: LOGGER
        }
        return ServerResponse.createBySuccess(lists);
    }

    public ServerResponse<List<Category>> findDeepChildById(Integer id) {
        Set<Category> categorieSet = new HashSet<Category>();
        deepFindCategory(categorieSet, id);
        
        List<Category> lists = new ArrayList<Category>();
        if (null != id) {
            for (Category category : categorieSet) {
                lists.add(category);
            }
        }
        return ServerResponse.createBySuccess(lists);
    }

    private Set<Category> deepFindCategory(Set<Category> categorieSet, Integer id) {
        Category category = this.categoryDao.findById(id);
        if (null != category) {
            categorieSet.add(category);
        }
        
        List<Category> lists = this.categoryDao.findChildByPid(id);
        for (Category c : lists) {
            deepFindCategory(categorieSet, c.getInt("id"));
        }
        return categorieSet;
    }
}
