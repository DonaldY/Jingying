'use strict';

require('../../public-resource/components/common/default.css');
require('./submit.css');
require('../../public-resource/iconfont/font-style.css');

require('../../public-resource/components/info-wrap/order/submit/order-submit-info.js');
require('../../public-resource/components/info-wrap/order/total-section/order-total-info.js');
require('../../public-resource/components/footer/nav/submit/footer-nav-submit.js');
const popAddressMenu = require('../../public-resource/components/side-menu/pop-address/pop-address.js');

var page = {
  init: function() {
    this.bindEvent();
    popAddressMenu.init();
  },

  bindEvent: function() {
    $('#address-request').click(function() {
      popAddressMenu.showEvent();
    });
  },
};

$(function() {
  page.init();
});
