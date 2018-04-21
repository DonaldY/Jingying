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

    $('#p-imgs-info').click(function() {
      $('#p-detail-info').removeClass('active_bottom');
      $('#p-imgs-info').addClass('active_bottom');
      $('.p_tab_spec').removeClass('active');
      $('.p_tab_imgs').addClass('active');
    });

    $('#p-detail-info').click(function() {
      $('#p-imgs-info').removeClass('active_bottom');
      $('#p-detail-info').addClass('active_bottom');
      $('.p_tab_imgs').removeClass('active');
      $('.p_tab_spec').addClass('active');
    });

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
