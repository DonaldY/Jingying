'use strict';

require('./pop-menu.css');
require('../../../iconfont/font-style.css');
require('../../../../../node_modules/css-ripple-effect/dist/ripple.min.css');

var popMenuPage = {

  bindEvent: function() {
    $('#pop-menu-close').click(this.hideEvent);
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
      error: function(XMLHttpRequest, textStatus, errorThrown) {
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

  },
};

module.exports = popMenuPage;
