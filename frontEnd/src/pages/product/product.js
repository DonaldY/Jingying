'use strict';

require('../../public-resource/components/common/default.css');

require('../../public-resource/components/header/nav/main/head-nav-main.js');

require('./product.css');
require('../../public-resource/iconfont/font-style.css');

require('../../public-resource/components/info-wrap/product/nav/product-nav-info.js');
require('../../public-resource/components/info-wrap/product/select/product-select-info.js');
require('../../public-resource/components/info-wrap/product/detail/product-detail-info.js');

require('../../public-resource/components/footer/nav/cart/footer-nav-cart.js');

var page = {
  init: function() {
    this.bindEvent();
  },

  bindEvent: function() {
    $('btn-add-cart').click(function() {
      if (isSelect()) {

      } else {

      }
    });

    $('btn-buy').click(function() {
      if (isSelect()) {

      } else {

      }
    });
  },
};

function isSelect() {

}

$(function() {
  page.init();
});
