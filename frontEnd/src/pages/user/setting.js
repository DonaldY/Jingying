'use strict';

require('./setting.css');
require('../../public-resource/iconfont/font-style.css');
require('../../public-resource/components/common/default.css');

require('../../../node_modules/css-ripple-effect/dist/ripple.min.css');
require('../../public-resource/components/top-nav/user/setting/user-setting.js');

const userServer = require('../../public-resource/utils/server/user-service.js');

var page = {
  init: function() {
    this.bindEvent();
    this.loadUserInfo();
  },

  bindEvent: function() {
    $('#btn-quit').click(function() {
      userServer.quit(function(res) {
        alert('登出成功~');
        window.location.href = 'user';
      }, function(errMs) {
        alert('服务器开了小差~');
      });
    });
  },

  loadUserInfo: function() {
    userServer.getUserInfo(function(res) {
      var headimgurl = res.headimgurl;
      $('.pre_img').attr('src', headimgurl);
      var nickname = res.nickname;
      $('#nickname-txt').text(nickname);
      var phone = res.phone;
      $('#tel-num').text(phone);
    }, function(errMsg) {
      alert('网络貌似开了点小差~');
    });
  },

};

$(function() {
  page.init();
});
