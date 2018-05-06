'use strict';

require('./footer-nav-cart.css');
require('../../../../iconfont/font-style.css');

require('../../../../../../node_modules/css-ripple-effect/dist/ripple.min.css');

const util = require('../../../../utils/default.js');

var page = {
  init: function() {
    this.bindEvent();
  },

  bindEvent: function() {
    $('#btn-contact').click(function() {
    });

    $('#btn-collect').click(function() {});

    $('#btn-add-cart').click(function() {
      var skuId = parseInt($('.curr_sku').attr('data-skuId'));
      var count = parseInt($('#product-num').val());
      if (isNaN(skuId) || skuId === null || skuId === 0) {
        util.formatTips('请选择规格');
      } else if (isNaN(count) || count === null || count === 0) {
        util.formatTips('请选择数量');
      } else {
        window.location.href = '/Jingying/cart/add?skuId=' + skuId + '&count=' + count;
      }
    });

    $('#btn-buy').click(function() {});
  },
};

$(function() {
  page.init();
});
