'use strict';

require('./product-select-info.css');
var popMenuPage = require('../../../../components/side-menu/pop/pop-menu.js');

var page = {
  init: function() {
    this.bindEvent();
  },

  bindEvent: function() {
    $('#spec-select').click(function() {
      var spuId = $(this).attr('data-spuId');
      var mainImage = $(this).attr('data-mainImage');
      popMenuPage.showEvent(spuId, mainImage);
    });
  },

};

$(function() {
  page.init();
});
