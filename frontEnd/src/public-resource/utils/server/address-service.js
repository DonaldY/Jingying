'use strict';

const util = require('../default.js');

var addressServer = {

  loadList: function(param, resolve, reject) {
    util.request({
      url: util.getServerUrl('/address/list'),
      data: param,
      success: resolve,
      error: reject,
    });
  },

  updateChecked: function(param, resolve, reject) {
    util.request({
      url: util.getServerUrl('/address/updateChecked'),
      data: param,
      success: resolve,
      error: reject,
    });
  },

};

module.exports = addressServer;
