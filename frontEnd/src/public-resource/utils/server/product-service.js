'use strict';

const util = require('../default.js');

var _product = {
   
    getProductList : function(listParam, resolve, reject){
        util.request({
            url     : util.getServerUrl('/product/list'),
            data    : listParam,
            success : resolve,
            error   : reject
        });
    },
   
    getProductSpu : function(productId, resolve, reject){
        util.request({
            url     : util.getServerUrl('/product/detail'),
            data    : {
                productId : productId
            },
            success : resolve,
            error   : reject
        });
    }
}
module.exports = _product;
