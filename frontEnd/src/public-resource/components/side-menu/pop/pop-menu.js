'use strict';

require('./pop-menu.css');
require('../../../iconfont/font-style.css');
require('../../../../../node_modules/css-ripple-effect/dist/ripple.min.css');

const util = require('../../../utils/default.js');
const cartServer = require('../../../utils/server/cart-service.js');

var popMenuPage = {

  bindEvent: function() {
    $('#pop-menu-close').click(this.hideEvent);

    $('.pop_select_li').click(function() {
      $(this).addClass('curr_sku').siblings().removeClass('curr_sku');

      var price = $(this).attr('data-price');
      $('#pop-info-price').text(price);

      var stock = parseInt($(this).attr('data-stock'));
      $('#control-stock').text(stock);
    });

    var _this = this;
    $('#control-num-add').click(function() {
      if (!_this.isSelectSku()) {
        util.formatTips('请选择规格');
        return;
      }

      var num = parseInt($('#product-num').val());
      var maxNum = parseInt($('.curr_sku').attr('data-stock'));
      if (num + 1 > maxNum) {
        util.formatTips('超出库存数量');
        $('#product-num').val(maxNum);
      } else {
        num++;
        $('#product-num').val(num);
      }
    });

    $('#control-num-sub').click(function() {
      if (!_this.isSelectSku()) {
        util.formatTips('请选择规格');
        return;
      }

      var num = parseInt($('#product-num').val());
      if (num - 1 <= 0) {
        util.formatTips('客官至少买一件嘛');
      } else {
        num--;
        $('#product-num').val(num);
      }
    });

    $('#product-num').bind('propertychange change', function() {
      var num = parseInt($(this).val());
      var maxNum = parseInt($('.curr_sku').attr('data-stock'));
      if (!_this.isSelectSku()) {
        util.formatTips('请选择规格');
      } else if (isNaN(num)) {
        util.formatTips('数量必须为数字');
        $(this).val(1);
      } else if (num >= maxNum) {
        util.formatTips('超出库存数量');
        $(this).val(maxNum);
      } else if (num <= 1) {
        util.formatTips('客官至少买一件嘛');
        $(this).val(1);
      }
    });

    // 添加购物车
    $('#control-btn-confirm').click(_this.confirm);
  },

  showEvent: function(spuId, mainImage) {
    $('.pop_info_img').attr('src', 'http://image.jingying.com/' + mainImage);
    this.loadSpec(spuId);
    this.bindEvent();

    $('#pop-menu').show();
    $('#pop-menu').css('opacity', '1');
    $('#pop-menu').css('transition', 'opacity 0.4s ease 0.01s');
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

  loadSpec: function(_spuId) {
    var _this = this;
    $.ajax({
      url: '/Jingying/product/ajaxSkuList',
      data: {spuId: _spuId},
      dataType: 'json',
      success: function(result) {
        $('#pop-select-ul li').remove();
        var liStr = _this.jointLi(result);
        $('#pop-select-ul').append(liStr);

        $('.pop_select_li').click(function() {
          $(this).addClass('curr_sku').siblings().removeClass('curr_sku');

          var price = $(this).attr('data-price');
          $('#pop-info-price').text(price);

          var stock = $(this).attr('data-stock');
          $('#control-stock').text(stock);
        });
      },
      error: function() {
        // alert(XMLHttpRequest.status);
        // alert(XMLHttpRequest.readyState);
        // alert(textStatus);
      },
    });
  },

  jointLi: function(list) {
    var liList = '';
    for (var i = 0; i < list.length; ++i) {
      var liStr = "<li class='pop_select_li' data-skuId='" + list[i].id + "'";
      liStr += " data-price='" + list[i].price + "'";
      liStr += " data-stock='" + list[i].stock + "'>";
      liStr += "<span class='pop_span'>" + list[i].spec_name + '</span>';
      liStr += '</li>';
      liList += liStr;
    }
    return liList;
  },

  confirm: function() {
    var skuId = parseInt($('.curr_sku').attr('data-skuId'));
    var count = parseInt($('#product-num').val());
    if (isNaN(skuId) || skuId === null || skuId === 0) {
      util.formatTips('请选择规格');
    } else if (isNaN(count) || count === null || count === 0) {
      util.formatTips('请选择数量');
    } else {
      var listParam = {
        'skuId': 0,
        'count': 1,
      };
      listParam.skuId = skuId;
      listParam.count = count;
      cartServer.addToCart(listParam, function(res) {
        var msg = res;
        if (msg === 1) {
          util.successTips('添加成功');
        } else if (msg === 0) {
          util.errorTips('电池无法到达');
        }
      }, function() {
        util.errorTips('你还没登录吧~');
        setTimeout(function() {
          window.location.href = util.getServerUrl('/user');
        }, 1500);
      });
      $('#pop-menu').hide();
    }
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
};

module.exports = popMenuPage;
