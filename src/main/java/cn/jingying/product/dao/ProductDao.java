package cn.jingying.product.dao;

import cn.jingying.product.model.ProductSku;
import cn.jingying.product.model.ProductSpu;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DonaldY on 2018/3/16.
 */
public class ProductDao {
    
    private final ProductSpu spuDao = new ProductSpu().dao();
    private final ProductSku skuDao = new ProductSku().dao();

    public List<Record> querySpuListByCategoryId(Integer categoryId) {
        
        List<Record> list = Db.find("SELECT id, name, main_image, min_price FROM j_product_spu WHERE category_id=?", 
            categoryId);
        
        return list;
    }

    public List<Record> queryBrand() {
        List<Record> list = Db.find("SELECT name FROM j_brand WHERE is_show=1");
        return list;
    }

    public Map<String, List<Record>> querySpuList() {
        Map<String, List<Record>> map = new HashMap<>();
        List<Record> categoryList = Db.find("SELECT id, name FROM j_category WHERE status=1");
        
        for (Record record : categoryList) {
            Integer categoryId = record.getInt("id");
            List<Record> list = this.querySpuListByCategoryId(categoryId);
            if (!list.isEmpty()) {
                map.put(record.getStr("name"), list);
            }
            
        }
        return map;
    }

    public Page<Record> queryProductByKeyword(Integer pageNumber, Integer pageSize, String keyword) {
        String select = "SELECT id, name, main_image, min_price ";
        String sqlExceptSelect = " FROM j_product_spu WHERE name like'%" + keyword + "%'";

        return Db.paginate(pageNumber, pageSize, select, sqlExceptSelect);
    }

    public ProductSpu queryProductSpuById(Integer prodectSpuId) {
        String sql = "SELECT id, name, main_image, sub_images, min_price, max_price, stock FROM j_product_spu " +
            "WHERE id=? AND status=1";
        ProductSpu productSpu = this.spuDao.findFirst(sql, prodectSpuId);
        return productSpu;
    }

    public List<ProductSku> querySkuListBySpuId(Integer spuId) {
        String sql = "SELECT id, spec_name, stock, price FROM j_product_sku WHERE spu_id=?";
        List<ProductSku> productSkuList = this.skuDao.find(sql, spuId);
        return productSkuList;
    }

    public List<Record> queryProductBySkuId(Integer skuId) {
        String sql = "SELECT k.id, k.spec_name, k.price, k.stock, p.name, p.main_image FROM j_product_sku k," +
            " j_product_spu p WHERE k.id=? AND k.spu_id=p.id AND p.status=1";
        List list = Db.find(sql, skuId);
        return list;
    }

    public List<Record> queryProductFromCartByUid(Integer id) {
        String sql = "SELECT k.id, k.spec_name, k.price, k.stock, p.name, p.main_image, c.quantity " +
            "FROM j_cart c LEFT JOIN j_product_sku k ON c.sku_id=k.id " + 
            "LEFT JOIN j_product_spu p ON k.spu_id=p.id " + 
            "WHERE c.user_id=? AND c.checked=1 AND c.status=1";
        List list = Db.find(sql, id);
        return list;
    }

    public ProductSku queryProductSkuBySkuId(Integer skuId) {
        return this.skuDao.findById(skuId);
    }

    public void updateProductSku(ProductSku productSku) {
        productSku.update();
    }
}
