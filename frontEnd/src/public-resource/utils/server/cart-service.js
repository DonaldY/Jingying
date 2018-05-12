'use strict';

const util = require('../default.js');

var cartServer = {
  getCartCount: function(resolve, reject) {
    util.request({
      url: util.getServerUrl(''),
      success: resolve,
      error: reject,
    });
  },

  addToCart: function(param, resolve, reject) {
    util.request({
      url: util.getServerUrl('/cart/ajaxAdd'),
      data: param,
      success: resolve,
      error: reject,
    });
  },

  getCartList: function(resolve, reject) {
    util.request({
      url: util.getServerUrl(''),
      success: resolve,
      error: reject,
    });
  },

  selectAllProduct: function(resolve, reject) {
    util.request({
      url: util.getServerUrl(''),
      success: resolve,
      error: reject,
    });
  },

  unselectAllProduct: function(resolve, reject) {
    util.request({
      url: util.getServerUrl(''),
      success: resolve,
      error: reject,
    });
  },

  updateProductCount: function(resolve, reject) {
    util.request({
      url: util.getServerUrl(''),
      success: resolve,
      error: reject,
    });
  },

  updateChecked: function(param, resolve, reject) {
    util.request({
      url: util.getServerUrl('/cart/updateChecked'),
      data: param,
      success: resolve,
      error: reject,
    });
  },
};

module.exports = cartServer;
