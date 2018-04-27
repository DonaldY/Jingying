'use strict';

require('./pop-menu.css');
require('../../../iconfont/font-style.css');
require('../../../../../node_modules/css-ripple-effect/dist/ripple.min.css');

var popMenuPage = {
  init: function() {
    this.bindEvent();
  },

  bindEvent: function() {
    $('#pop-menu-close').click(this.hideEvent);

    $('.pop_select_li').click(function() {
      $(this).addClass('curr_sku').siblings().removeClass('curr_sku');
    });
  },

  showEvent: function() {
    $('#pop-menu').show();

    $('#pop-menu').css('opacity', '1');
    $('#pop-menu').css('transition', 'opacity 0.4s ease 0.01s');

    setTimeout(function() {
      $('#show-pop-menu').css('bottom', '0');
    }, 500);

    setTimeout(function() {
      $('#pop-menu').css('transition', '');
    }, 1000);
  },

  hideEvent: function() {
    $('#show-pop-menu').css('bottom', '-100%');

    setTimeout(function() {
      $('#pop-menu').css('opacity', '0');
      $('#pop-menu').css('transition', 'opacity 0.4s ease 0.01s');
    }, 500);

    setTimeout(function() {
      $('#pop-menu').css('transition', '');
      $('#pop-menu').hide();
    }, 1300);
  },

  loadSpec: function() {

  },

  confirm: function() {

  },
};

module.exports = popMenuPage;
