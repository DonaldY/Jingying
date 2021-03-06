'use strict';

require('./layer-mobile/layer.css');
const layer = require('./layer-mobile/layer.js');

const config = {
  serverHost: '/Jingying',
  FtpHost: 'http://image.jingying.com',
};

var _common = {
  request: function(param) {
    // var _this = this;
    $.ajax({
      url: param.url || '',
      dataType: param.type || 'json',
      data: param.data || '',
      beforeSend: function() {

      },
      success: function(res) {

      },
      error: function(err) {
        typeof param.error === 'function' && param.error(err.statusText);
      },
    });
  },

  successTips: function(msg) {
    console.log(msg || '');
  },

  errorTips: function(msg) {
    var _msg = msg || '救命啊~';
    layer.open({
      content: _msg,
      skin: 'msg',
      time: 2,
    });
  },

  formatTips: function(msg) {
    var _msg = msg || '啊哦~ 貌似哪不对。。。';
    layer.open({
      content: _msg,
      time: 1.5,
    });
  },

  validate: function(value, type) {
    value = $.trim(value);

    if (type === 'require') {
      return !!value;
    }

    if (type === 'phone') {
      return /^1\d{10}$/.test(value);
    }

    if (type === 'email') {
      return /^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/.test(value);
    }
    return null;
  },

  getServerUrl: function(path) {
    return config.serverHost + path;
  },

  getUrlParam: function(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)');
    var result = window.location.search.substr(1).match(reg);
    return result ? decodeURIComponent(result[2]) : null;
  },

  getFtpHost: function() {
    return config.FtpHost;
  },

  getRelativeUrl: function() {
    var url = document.location.toString();
    var arrUrl = url.split('//');
    var start = arrUrl[1].indexOf('/');
    var relUrl = arrUrl[1].substring(start);// stop省略，截取从start开始到结尾的所有字符
    return relUrl;
  },
};

module.exports = _common;
