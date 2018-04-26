'use strict';

const util = require('../default.js');

var _product = {

  getProductListByKeyword: function(listParam, resolve, reject) {
    util.request({
      url: '/Jingying/product/ajaxProductList',
      data: listParam,
      success: resolve,
      error: reject,
    });
  },
};
module.exports = _product;
