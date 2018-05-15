'use strict';

require('../../public-resource/components/common/default.css');
require('./submit.css');
require('../../public-resource/iconfont/font-style.css');

require('../../public-resource/components/info-wrap/order/submit/order-submit-info.js');
require('../../public-resource/components/info-wrap/order/total-section/order-total-info.js');
require('../../public-resource/components/footer/nav/submit/footer-nav-submit.js');
const popAddressMenu = require('../../public-resource/components/side-menu/pop-address/pop-address.js');

const util = require('../../public-resource/utils/default.js');

var page = {
  init: function() {
    this.bindEvent();
  },

  bindEvent: function() {
    $('#address-request').click(function() {
      popAddressMenu.showEvent();
    });

    //
    $('#order-submit').click(function() {
      var addressId = $('#addre-data').data('addressid');
      window.location.href = util.getServerUrl('/order/create?addressId=' + addressId);
    });
  },
};

$(function() {
  page.init();
});
