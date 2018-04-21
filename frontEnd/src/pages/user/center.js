'use strict';

require('./center.css');
require('../../public-resource/iconfont/font-style.css');
require('../../public-resource/components/common/default.css');

require('../../public-resource/components/top-nav/user/login/user-login-info.css');

var page = {
  init: function() {
    this.bindEvent();
  },

  bindEvent: function() {
    $('#user-info-set').click(function() {
      window.location.href = 'user/setting';
    });

    $('.wechat_login_btn').click(function() {
      window.location.href = 'wechatOAuth';
    });
  },

};

$(function() {
  page.init();
});
