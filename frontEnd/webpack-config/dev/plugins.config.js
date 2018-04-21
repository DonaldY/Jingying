var webpack = require('webpack');

const pluginsConfig = require('../inherit/plugins.config.js');

pluginsConfig.push(
    /* ��ȡ������ͨ�õĲ��� */
    new webpack.optimize.CommonsChunkPlugin({
	name: 'commons/common',      // ��Ҫע����ǣ�chunk��name������ͬ������
	filename: 'js/[name].js',
	minChunks: 4
    })
);

pluginsConfig.push(new webpack.HotModuleReplacementPlugin());

pluginsConfig.push(new webpack.DefinePlugin({
    IS_PRODUCTION: false
}));


module.exports = pluginsConfig;
