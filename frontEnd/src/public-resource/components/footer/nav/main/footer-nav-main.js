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
    $('#btn-go-home').click(function() {
      alert('btn-go-home');
    });

    $('#btn-go-category').click(function() {

    });
  },
};

$(function() {
  page.init();
});
