'use strict';

require('./side-search.css');
require('../../../iconfont/font-style.css');

var sideSearchPage = {

  init: function() {
    this.bindEvent();
  },

  bindEvent: function() {
    $('#mask-area').click(this.hideEvent);

    $('.classify_list_wrap ul li ul li').click(function() {
      var keyword = $(this).text();
      $('#input-goods-search').val(keyword);
    });

    $('#btn-goods-search').click(function() {
      var keyword = $('#input-goods-search').val();
      if (keyword) {
        window.location.href = './product/search?keyword=' + keyword;
      } else {
        alert('没有值 不跳转');
      }
    });
  },

  showEvent: function() {
    $('#search-panel').show();

    $('#search-panel').css('opacity', '1');
    $('#search-panel').css('transition', 'opacity 0.4s linear 0.01s');

    setTimeout(function() {
      $('.show_panel').css('left', '0');
    }, 500);

    setTimeout(function() {
      $('#search-panel').css('transition', '');
    }, 1000);
  },

  hideEvent: function() {
    $('.show_panel').css('left', '-100%');

    setTimeout(function() {
      $('#search-panel').css('opacity', '0');
      $('#search-panel').css('transition', 'opacity 0.4s linear 0.01s');
    }, 500);

    setTimeout(function() {
      $('#search-panel').css('transition', '');
      $('#search-panel').hide();
    }, 1300);
  },

};

module.exports = sideSearchPage;
