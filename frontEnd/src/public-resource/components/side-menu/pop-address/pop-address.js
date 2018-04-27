'use strict';

require('./pop-address.css');
require('../../../iconfont/font-style.css');
require('../../common/default.css');

var popAddressPage = {
  data: {

  },
  init: function() {
    this.bindEvent();
  },

  bindEvent: function() {

  },

  showEvent: function() {
    $('#address-list').addClass('animate_top');
  },

  hideEvent: function() {
    $('#address-list').removeClass('animate_top');
  },
};

module.exports = popAddressPage;
