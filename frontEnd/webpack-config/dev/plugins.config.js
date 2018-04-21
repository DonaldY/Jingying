var webpack = require('webpack');

const pluginsConfig = require('../inherit/plugins.config.js');

pluginsConfig.push(
    /* 抽取出所有通用的部分 */
    new webpack.optimize.CommonsChunkPlugin({
	name: 'commons/common',      // 需要注意的是，chunk的name不能相同！！！
	filename: 'js/[name].js',
	minChunks: 4
    })
);

pluginsConfig.push(new webpack.HotModuleReplacementPlugin());

pluginsConfig.push(new webpack.DefinePlugin({
    IS_PRODUCTION: false
}));


module.exports = pluginsConfig;
