package cn.jingying.category.dao;

import cn.jingying.category.model.Category;
import com.jfinal.plugin.activerecord.Db;

import java.util.List;

/**
 * Created by DonaldY on 2018/3/14.
 */
public class CategoryDao {
    private final Category dao = new Category().dao();
    public List<Category> findChildByPid(Integer id) {
        String sql = "SELECT id, name FROM j_category WHERE parent_id=? AND status=1";
        return this.dao.find(sql, id);
    }

    public Category findById(Integer id) {
        return this.dao.findById(id);
    }

    public List findRootList() {
        String sql = "SELECT id, name FROM j_category WHERE parent_id=0 AND status=1";
        return Db.find(sql);
    }
}
