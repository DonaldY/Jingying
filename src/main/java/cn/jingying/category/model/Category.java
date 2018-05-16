package cn.jingying.category.model;

import com.jfinal.plugin.activerecord.Model;

/**
 * Created by DonaldY on 2018/3/9.
 */
public class Category extends Model<Category> {
    
    
    // TODO: hash 
    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        return !(this.id != null ? !id.equals(category.id) : category.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }*/
}
