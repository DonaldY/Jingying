'use strict';

require('../../public-resource/components/common/default.css');

require('../../public-resource/components/header/nav/main/head-nav-main.js');

require('./product.css');
require('../../public-resource/iconfont/font-style.css');

require('../../public-resource/components/footer/nav/cart/footer-nav-cart.js');
var popMenuPage = require('../../public-resource/components/side-menu/pop/pop-menu.js');

var page = {
  init: function() {
    this.bindEvent();
    popMenuPage.init();
  },

  bindEvent: function() {
    $('#spec-select').click(function() {
      popMenuPage.showEvent();
    });
  },
};

$(function() {
  page.init();
});
