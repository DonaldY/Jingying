'use strict';

const _util = require('../default.js');

var userServer = {

  login: function(userInfo, resolve, reject) {
    _util.request({
      url: _util.getServerUrl('/user/login'),
      data: userInfo,
      method: 'POST',
      success: resolve,
      error: reject,
    });
  },

  getUserInfo: function(userInfo, resolve, reject) {
    _util.request({
      url: _util.getServerUrl('/user/getInfo'),
      data: userInfo,
      method: 'POST',
      success: resolve,
      error: reject,
    });
  },

  updateUserInfo: function(userInfo, resolve, reject) {
    _util.request({
      url: _util.getServerUrl('/user/update'),
      data: userInfo,
      method: 'POST',
      success: resolve,
      error: reject,
    });
  },

  quit: function(resolve, reject) {
    _util.request({
      url: _util.getServerUrl('user/quit'),
      method: 'POST',
      success: resolve,
      error: reject,
    });
  },

};

module.exports = userServer;
