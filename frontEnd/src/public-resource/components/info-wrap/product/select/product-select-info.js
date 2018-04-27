'use strict';

require('./product-select-info.css');
var popMenuPage = require('../../../../components/side-menu/pop/pop-menu.js');
var page = {
  init: function() {
    this.bindEvent();
    popMenuPage.init();
  },

  bindEvent: function() {
    $('#spec-select').click(function() {
      popMenuPage.showEvent();
    });
  },

};

$(function() {
  page.init();
});
