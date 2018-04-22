package cn.jingying.utils;

import cn.jingying.cart.model.Cart;
import cn.jingying.category.model.Category;
import cn.jingying.product.model.ProductSku;
import cn.jingying.product.model.ProductSpu;
import cn.jingying.user.model.User;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {
	
	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("j_user", "id", User.class);
		arp.addMapping("j_category", "id", Category.class);
		arp.addMapping("j_cart", "id", Cart.class);
		arp.addMapping("j_product_spu", "id", ProductSpu.class);
		arp.addMapping("j_product_sku", "id", ProductSku.class);
	}
}

