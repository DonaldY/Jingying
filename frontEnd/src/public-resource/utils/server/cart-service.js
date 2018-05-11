'use strict';

const util = require('../default.js');

var cartServer = {
    getCartCount: function(resolve, reject) {
	util.request({
	    url: util.getServerUrl(''),
	    success: resolve,
	    error: reject
	});
    },

    addToCart: function(resolve, reject) {
	util.request({
	    url: util.getServerUrl(''),
	    success: resolve,
	    error: reject
	});
    },

    getCartList: function(resolve, reject) {
	util.request({
	    url: util.getServerUrl(''),
	    success: resolve,
	    error: reject
	});
    },

    selectAllProduct: function(resolve, reject) {
	util.request({
	    url: util.getServerUrl(''),
	    success: resolve,
	    error: reject
	});
    },

    unselectAllProduct: function(resolve, reject) {
	util.request({
	    url: util.getServerUrl(''),
	    success: resolve,
	    error: reject
	});
    },

    updateProductCount: function(resolve, reject) {
	util.request({
	    url: util.getServerUrl(''),
	    success: resolve,
	    error: reject
	});
    }
};

module.exports = cartServer;