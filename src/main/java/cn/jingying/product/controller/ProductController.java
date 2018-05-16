package cn.jingying.product.controller;

import cn.jingying.common.ServerResponse;
import cn.jingying.product.model.ProductSku;
import cn.jingying.product.model.ProductSpu;
import cn.jingying.product.service.ProductService;
import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import java.util.Arrays;
import java.util.List;

/**
 * Created by DonaldY on 2018/4/21.
 */
public class ProductController extends Controller{
    private final ProductService productService = new ProductService();
    
    @ActionKey("/product/detail")
    public void getProductSpuDetail() {
        Integer productSpuId = getParaToInt("id");
        ServerResponse spuServerResponse = this.productService.getProductSpuById(productSpuId);
        if (!spuServerResponse.isSuccess()) {
            renderError(404);
        }
        
        ServerResponse skuServerResponse = this.productService.getSkuListBySpuId(productSpuId);
        if (!skuServerResponse.isSuccess()) {
            renderError(404);
        }
        
        ProductSpu productSpu = (ProductSpu) spuServerResponse.getData();
        List<String> imgList = Arrays.asList(productSpu.getStr("sub_images").split(","));
        List<ProductSku> skuList = (List<ProductSku>) skuServerResponse.getData();
        setAttr("skuList", skuList);
        setAttr("productSpu", productSpu);
        setAttr("imgList", imgList);
        render("product.html");
    }
    
    @ActionKey("/product/search")
    public void searchProductByKeyword() {
        String keyword = getPara("keyword");
        setAttr("keyword", keyword);
        render("search.html");
    }
    
    @ActionKey("/product/ajaxProductList")
    public void ajaxProductByKeyword() {
        String keyword = getPara("keyword");
        Integer pageNumber = getParaToInt("pageNumber");
        Integer pageSize = 5;
        Page<Record> page = this.productService.getProductListByKeyword(pageNumber, pageSize, keyword).getData();
        renderJson(page);
    }
    
    @ActionKey("/product/ajaxSkuList")
    public void getProductSkuList() {
        Integer spuId = getParaToInt("spuId");
        ServerResponse serverResponse = this.productService.getSkuListBySpuId(spuId);
        if (serverResponse.isSuccess()) {
            List<ProductSku> productSkuList = (List<ProductSku>) serverResponse.getData();
            renderJson(productSkuList);
        } else {
            // TODO: ERROR 值
            renderJson();
        }
    }
}
