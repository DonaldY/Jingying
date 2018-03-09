package cn.jingying.config;

import cn.jingying.HelloController;
import cn.jingying.LoginController;
import cn.jingying._MappingKit;
import com.jfinal.config.*;
import com.jfinal.json.MixedJsonFactory;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.qyweixin.sdk.api.ApiConfig;
import com.jfinal.qyweixin.sdk.api.ApiConfigKit;
import com.jfinal.template.Engine;
import org.beetl.core.GroupTemplate;
import org.beetl.ext.jfinal3.JFinal3BeetlRenderFactory;


public class JyConfig extends JFinalConfig{

	public ApiConfig getApiConfig() {
		ApiConfig ac = new ApiConfig();

		// 配置微信 API 相关常量
		ac.setToken(PropKit.get("token"));
		ac.setCorpId(PropKit.get("corpId"));
		ac.setCorpSecret(PropKit.get("secret"));
		ac.setAgentId(PropKit.get("agentId"));

		/**
		 *  是否对消息进行加密，对应于微信平台的消息加解密方式：
		 *  1：true进行加密且必须配置 encodingAesKey
		 *  2：false采用明文模式，同时也支持混合模式
		 *
		 *  目前企业号只支持加密且必须配置
		 */
		ac.setEncryptMessage(PropKit.getBoolean("encryptMessage", true));
		ac.setEncodingAesKey(PropKit.get("encodingAesKey", "setting it in config file"));
		return ac;
	}

	public void configConstant(Constants me) {
		PropKit.use("config.properties");
		me.setDevMode(true);
		me.setEncoding("utf-8");
		JFinal3BeetlRenderFactory rf = new JFinal3BeetlRenderFactory();
		rf.config();
		me.setRenderFactory(rf);
		GroupTemplate gt = rf.groupTemplate;
		// ApiConfigKit 设为开发模式可以在开发阶段输出请求交互的 xml 与 json 数据
		ApiConfigKit.setDevMode(me.getDevMode());

		me.setJsonFactory(new MixedJsonFactory());
	}

	public void configRoute(Routes me) {
		me.add("/", HelloController.class, "/");
		me.add("/wechatOAuth", LoginController.class, "/");
	}

	public void configPlugin(Plugins me) {
		DruidPlugin druidPlugin = new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), 
			PropKit.get("password").trim());
		me.add(druidPlugin);

		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		// 显示SQL语句
		arp.setShowSql(true);
		// 所有映射在 MappingKit 中自动化搞定
		_MappingKit.mapping(arp);
		me.add(arp);

		// EhCachePlugin ecp = new EhCachePlugin();
		// me.add(ecp);

		// 使用redis分布accessToken
		// RedisPlugin redisPlugin = new RedisPlugin("qyweixin", "127.0.0.1");
		// redisPlugin.setSerializer(JdkSerializer.me); // 需要使用fst高性能序列化的用户请删除这一行（Fst jar依赖请查看WIKI）
		// me.add(redisPlugin);
	}

	public void configInterceptor(Interceptors me) {

	}

	public void configHandler(Handlers me) {

	}

	@Override
	public void afterJFinalStart() {
		//单个应用可以直接在启动之后添加
		ApiConfigKit.putApiConfig(getApiConfig());
	}


	@Override
	public void configEngine(Engine me) {

	}


}
