'use strict';

require('./head-nav-main.css');

// const _util = require('../../../../utils/default.js');

var page = {
  init: function() {
    this.bindEvent();
  },

  bindEvent: function() {

  },
};

$(function() {
  $(window).scroll(function() {
    if ($(window).scrollTop() >= 1) {
      $('#headNav').addClass('head_nav_fixed');
    } else {
      $('#headNav').removeClass('head_nav_fixed');
    };
  });

  page.init();
});
