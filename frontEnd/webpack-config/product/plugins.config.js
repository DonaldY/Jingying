var webpack = require('webpack');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var path = require('path');
var dirVars = require('../base/dir-vars.config.js');
var PurifyCssPlugin = require('purifycss-webpack');
var glob = require('glob-all');

const pluginsConfig = require('../inherit/plugins.config.js');

pluginsConfig.push(
    /* 抽取出所有通用的部分 */
    new webpack.optimize.CommonsChunkPlugin({
	name: 'commons/common',      // 需要注意的是，chunk的name不能相同！！！
	filename: 'js/[name].[hash:6].js',
	minChunks: 4
    })
);

pluginsConfig.push(
    /* 抽取出chunk的css */
    new ExtractTextPlugin({
	filename: '/css/[name].[hash:6].css',
	allChunks: true
    })
);

// pluginsConfig.push(
//     new PurifyCssPlugin({
// 	paths: glob.sync([
// 	    path.join(dirVars.pagesDir, '/**/*.html')
// 	]),
// 	/* 查看压缩信息 */
// 	purifyOptions: {
//             info: true,
//             minify: false
// 	}
//     })
// );

pluginsConfig.push(new webpack.DefinePlugin({
    IS_PRODUCTION: true
}));

pluginsConfig.push(
    new webpack.optimize.UglifyJsPlugin()
);

module.exports = pluginsConfig;
