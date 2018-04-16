'use strict';

const conf = {
  serverHost: '',
};

var _common = {
  request: function(param) {
    var _this = this;
    $.ajax({
      type: param.method || 'get',
      url: param.url || '',
      dataType: param.type || 'json',
      data: param.data || '',
      success: function(res) {
        if (res.status === 0) {
          typeof param.success === 'function' && param.success(res.data, res.msg);
        } else if (res.status === 10) {
        } else if (res.status === 1) {
          typeof param.error === 'function' && param.error(res.msg);
        }
      },
      error: function(err) {
        typeof param.error === 'function' && param.error(err.statusText);
      },
    });
  },

  successTips: function(msg) {
    alert(msg || '');
  },

  errorTips: function(msg) {
    alert(msg || '');
  },

  validate: function(value, type) {
    value = $.trim(value);

    if (type === 'require') {
      return !!value;
    }

    if (type === 'phone') {
      return /^1\d{10}$/.test(value);
    }

    if (type === 'email') {
      return /^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/.test(value);
    }
    return null;
  },

  doLogin: function() {
    window.location.href = './user-login.html?redirect=' + encodeURIComponent(window.location.href);
  },
  goHome: function() {
    window.location.href = './index.html';
  },
};

module.exports = _common;
