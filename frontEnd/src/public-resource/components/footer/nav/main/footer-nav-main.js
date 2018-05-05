'use strict';

require('./footer-nav-main.css');
require('../../../../../../node_modules/css-ripple-effect/dist/ripple.min.css');
require('../../../../iconfont/font-style.css');

// const _util = require('../../../../utils/default.js');

var page = {
  init: function() {
    this.bindEvent();
  },

  bindEvent: function() {
    $('#btn-go-contact').click(function() {
      window.location.href = 'contact';
    });

    $('#btn-go-cart').click(function() {
      window.location.href = 'cart';
    });

    $('#btn-go-me').click(function() {
      window.location.href = 'user';
    });
  },
};

$(function() {
  page.init();
});
