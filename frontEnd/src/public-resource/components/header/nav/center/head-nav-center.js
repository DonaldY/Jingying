require('./head-nav-center.css');
require('../../../common/default.css');

var page = {
  init: function() {
    this.bindEvent();
  },

  bindEvent: function() {
    // $('#user-info-set').click(function() {
    //   window.location.href = 'user/setting';
    // });

    $('.wechat_login_btn').click(function() {
      window.location.href = 'wechatOAuth';
    });
  },

};

$(function() {
  page.init();
});
