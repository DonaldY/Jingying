'use strict';

require('./pop-menu.css');
require('../../../iconfont/font-style.css');
require('../../../../../node_modules/css-ripple-effect/dist/ripple.min.css');

var popMenuPage = {

  init: function() {
    this.bindEvent();
  },

  bindEvent: function() {
    $('#pop-menu-close').click(this.hideEvent);

    $('.pop_select_li').click(function() {
      $(this).addClass('curr_sku').siblings().removeClass('curr_sku');

      var price = parseInt($(this).attr('data-price'));
      $('#pop-info-price').text(price);

      var stock = parseInt($(this).attr('data-stock'));
      $('#control-stock').text(stock);
    });

    var _this = this;
    $('#control-num-add').click(function() {
      if (!_this.isSelectSku()) {
        alert('请选择规格');
        return;
      }

      var num = parseInt($('#product-num').val());
      var maxNum = parseInt($('.curr_sku').attr('data-stock'));
      if (num + 1 > maxNum) {
        $('#product-num').val(maxNum);
        alert('超出库存数量' + maxNum);
      } else {
        num++;
        $('#product-num').val(num);
      }
    });

    $('#control-num-sub').click(function() {
      if (!_this.isSelectSku()) {
        alert('请选择规格');
        return;
      }

      var num = parseInt($('#product-num').val());
      if (num - 1 <= 0) {
        alert('客官至少买一件嘛');
      } else {
        num--;
        $('#product-num').val(num);
      }
    });

    $('#product-num').bind('propertychange change', function() {
      var num = parseInt($(this).val());
      var maxNum = parseInt($('.curr_sku').attr('data-stock'));
      if (!_this.isSelectSku()) {
        alert('请选择规格');
      } else if (isNaN(num)) {
        alert('数量必须为数字');
        $(this).val(1);
      } else if (num >= maxNum) {
        alert('超出库存数量');
        $(this).val(maxNum);
      } else if (num <= 1) {
        alert('客官至少买一件嘛');
        $(this).val(1);
      }
    });

    $('#control-btn-confirm').click(function() {
      var skuName = $('.curr_sku .pop_span').text();
      $('#spec-title').text('已选择 ' + skuName);
      _this.hideEvent();
    });
  },

  showEvent: function() {
    $('#pop-menu').show();
    $('#pop-menu').css('opacity', '1');
    $('#pop-menu').css('transition', 'opacity 0.2s ease 0.01s');
    setTimeout(function() {
      $('#show-pop-menu').css('bottom', '0');
    }, 500);
    setTimeout(function() {
      $('#pop-menu').css('transition', '');
    }, 1000);
  },

  hideEvent: function() {
    $('#show-pop-menu').css('bottom', '-100%');
    setTimeout(function() {
      $('#pop-menu').css('opacity', '0');
      $('#pop-menu').css('transition', 'opacity 0.4s ease 0.01s');
    }, 500);
    setTimeout(function() {
      $('#pop-menu').css('transition', '');
      $('#pop-menu').hide();
    }, 1100);
  },

  addNum: function() {
    var _this = this;
    if (!_this.isSelectSku()) {
      alert('请选择');
    }
  },

  inputNum: function() {

  },

  isSelectSku: function() {
    var flag = false;
    $('#pop-select-ul li').each(function() {
      if ($(this).hasClass('curr_sku')) {
        flag = true;
      }
    });
    return flag;
  },

  isNum: function() {
    var num = $('product-num').val();
    var reg = /^\+?[1-9][0-9]*$/;
    if (reg.exec(num) == null || num === '') {
      return false;
    }
    return true;
  },

};

module.exports = popMenuPage;
