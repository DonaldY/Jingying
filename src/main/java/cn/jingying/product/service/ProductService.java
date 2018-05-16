package cn.jingying.product.service;

import cn.jingying.common.ServerResponse;
import cn.jingying.product.dao.ProductDao;
import cn.jingying.product.model.ProductSku;
import cn.jingying.product.model.ProductSpu;
import cn.jingying.utils.BigDecimalUtil;
import cn.jingying.utils.PropertiesUtil;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.mysql.fabric.Server;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by DonaldY on 2018/3/16.
 */
public class ProductService {
    private final ProductDao productDao = new ProductDao();
    
    public ServerResponse<List<Record>> getSpuListByCategoryId(Integer categoryId) {
        List<Record> list = this.productDao.querySpuListByCategoryId(categoryId);
        if (list.isEmpty()) {
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess(list);
    }

    public ServerResponse<List<Record>> getBrandList() {
        List<Record> list = this.productDao.queryBrand();
        if (list.isEmpty()) {
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess(list);
    }

    public ServerResponse<Map<String, List<Record>>> getSpuList() {
        Map<String, List<Record>> map = this.productDao.querySpuList();
        if (map.isEmpty()) {
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess(map);
    }

    public ServerResponse<Page<Record>> getProductListByKeyword(Integer pageNumber, Integer pageSize, String keyword) {
        Page<Record> page = this.productDao.queryProductByKeyword(pageNumber, pageSize, keyword);
        if (page.getList().isEmpty()) {
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess(page);
    }

    public ServerResponse<ProductSpu> getProductSpuById(Integer prodectSpuId) {
        ProductSpu productSpu = this.productDao.queryProductSpuById(prodectSpuId);
        if (null == productSpu) {
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess(productSpu);
    }

    public ServerResponse<List<ProductSku>> getSkuListBySpuId(Integer spuId) {
        List<ProductSku> productSkuList = this.productDao.querySkuListBySpuId(spuId);
        if (productSkuList.isEmpty()) {
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess(productSkuList);
    }
    
    public ServerResponse<List<Record>> getProductBySkuId(Integer skuId) {
        List<Record> list = this.productDao.queryProductBySkuId(skuId);
        if (list.isEmpty()) {
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess(list);
    }

    public ServerResponse<List<Record>> getProductFromCartByUid(Integer id) {
        List<Record> list = this.productDao.queryProductFromCartByUid(id);
        if (list.isEmpty()) {
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess(list);
    }


    public ProductSku getProductSkuBySkuId(Integer skuId) {
        ProductSku productSku = this.productDao.queryProductSkuBySkuId(skuId);
        return productSku;
    }

    public void updateProductSku(ProductSku productSku) {
        this.productDao.updateProductSku(productSku);
    }
}
