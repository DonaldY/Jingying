'use strict';

require('./product-index-info.css');
const popMenuPage = require('../../../side-menu/pop/pop-menu.js');

require('../../../../utils/jq/jquery.lazyload.min.js');

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

    $('img.lazy').lazyload({
      threshold: 200,
    });
  },
};

$(function() {
  page.init();
});
