var dirVars = require('../base/dir-vars.config.js');
var eslintFormatter = require('eslint-friendly-formatter');

module.exports = {
    rules: [
	{
	    test: /\.js$/,
	    enforce: 'pre',
	    loader: 'eslint-loader',
	    include: dirVars.srcRootDir,
	    exclude: /bootstrap/,
	    options: {
		formatter: eslintFormatter,
		fix: true
	    }
	},
	
	{
	    test: /\.js$/,
	    include: dirVars.srcRootDir,
	    loader: 'babel-loader',
	    options: {
		presets: ['env'],
		cacheDirectory: true,
		plugins: ['transform-runtime']
	    }
	},
	
	{
	    test: /\.(png|jpg|jpeg|gif|webp)$/,
	    use: [
		{
		    loader: 'url-loader',
		    options: {
			name: '[name].[ext]',
			limit: 8192,
			publicPath: '../../../imgs/',
			outputPath: 'imgs/'
		    }
		},
		{
		    // ѹ��
		    loader: 'img-loader',
		    options: {
			pngquant: {
			    quality: 80
			}
		    }
		}
	
	    ]
	},

	{
	    // iconfont
	    test:/\.(eot|woff2|woff|ttf|svg)$/,
	    use: [
		{
		    loader: 'url-loader',
		    options: {
			name: '[name].[ext]',
			limit: 8192,
			publicPath: '../../../fonts/',
			outputPath: 'fonts/',
			useRelativePath: true
		    }
		}
	    ]
	}
	
    ]
    
};
