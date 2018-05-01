'use strict';

require('./product-nav-info.css');
require('../../../../../../node_modules/swiper/dist/css/swiper.min.css');
const Swiper = require('../../../../../../node_modules/swiper/dist/js/swiper.min.js');

$(function() {
  var mySwiper = new Swiper('.swiper-container', {

  });
  console.log(mySwiper);
});
