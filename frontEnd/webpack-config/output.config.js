var dirVars = require('./base/dir-vars.config.js');

module.exports = {
    path: dirVars.buildDir,
    publicPath: dirVars.buildDir,
    filename: 'js/[name].js',    // [name]��ʾentryÿһ���е�key����������ָ�����ɺ��ļ�������
    chunkFilename: '[id].[chunkhash].bundle.js'
};
