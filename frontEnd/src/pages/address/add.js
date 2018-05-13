'use strict';

require('./add.css');
require('../../public-resource/iconfont/font-style.css');
require('../../public-resource/components/common/default.css');

// const util = require('../../public-resource/utils/default.js');

var page = {

  init: function() {
    this.bindEvent();
  },

  bindEvent: function() {
    $('#Js-set-default').click(function() {
      var type = $(this).attr('curtype');
      if (type === '1') {
        $(this).removeClass('switch-checked');
        $(this).attr('curtype', 0);
      } else if (type === '0') {
        $(this).addClass('switch-checked');
        $(this).attr('curtype', 1);
      }
    });

    // 提交表单
    $('#Js-save-address').click(function() {
      $('form').submit();
    });
  },
};

$(function() {
  page.init();
});
