'use strict';

require('../../public-resource/components/common/default.css');

require('../../public-resource/components/header/nav/main/head-nav-main.js');

require('./index.css');
require('../../public-resource/iconfont/font-style.css');

const sideSearch = require('../../public-resource/components/side-menu/search/side-search.js');

require('../../public-resource/components/footer/nav/main/footer-nav-main.js');

require('../../public-resource/components/info-wrap/product/index/product-index-info.js');

// const _util = require('../../public-resource/utils/default.js');

var page = {
  init: function() {
    this.bindEvent();

    sideSearch.init();
  },

  bindEvent: function() {
    $('#btn-go-menu').click(function() {
      sideSearch.showEvent();
    });
  },
};

$(function() {
  page.init();
});
