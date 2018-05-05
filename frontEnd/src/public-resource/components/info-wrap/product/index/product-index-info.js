'use strict';

require('./product-index-info.css');
const popMenuPage = require('../../../side-menu/pop/pop-menu.js');

var page = {
  init: function() {
    this.bindEvent();
  },

  bindEvent: function() {
    $('#add-cart-sku').click(function(event) {
      event.stopPropagation();
      event.preventDefault();
      var spuId = $(this).attr('data-spuId');
      var image = $(this).attr('data-mainImage');
      popMenuPage.showEvent(spuId, image);
      return false;
    });
  },
};

$(function() {
  page.init();
});
