'use strict';

require('./cart.css');
require('../../public-resource/iconfont/font-style.css');
require('../../public-resource/components/common/default.css');

const util = require('../../public-resource/utils/default.js');

var page = {
  init: function() {
    this.loadMore();
    this.bindEvent();
  },

  bindEvent: function() {
  },

  loadMore: function() {
    var _this = this;

    $.ajax({
      url: '/Jingying/cart/list',
      type: 'get',
      dataType: 'json',
      beforeSend: function() {
        console.log('before');
      },
      success: function(result) {
	    var list = result;
        var liStr = _this.jointLi(list);
        $('.C_goods_list').append(liStr);
      },
      error: function(err) {

      },
      complete: function() {
        console.log('complete');
      },
    });
  },

  jointLi: function(list) {
    var liList = '';
      for (var i = 0; i < list.length; ++i) {
	  var liStr = "<li class='C_item'><section class='C_item_wrap'><mark class='mask item_select ";
	  if (list[i].checked) {
              liStr += 'selected';
      }
      liStr += "'><span class='round icon-check'>&nbsp;</span></mark>";
      liStr += "<a class='item_img'><img src='";
      liStr += util.getFtpHost() + list[i].main_image + "' class='item-img' style='opacity: 1; transition: opacity 0.3s;'></a>";
      liStr += "<section class='item_info'><section class='item_info_wrap'><section class='info_title'>";
      liStr += list[i].name + "</section><section class='info_num'></section>";
      liStr += "<p class='info_spec'><span>" + list[i].spec_name + '</span></p>';
      liStr += "<p class='info_status'><span class='info_status_label baoyou'>包邮</span></p>";
      liStr += "<p class='info_price_wrap'><span class='info_price'><i>¥";
      liStr += list[i].price + '</i></span></p></section>';
      liStr += "<span class='item_count'>×" + list[i].quantity + '</span>';
      liStr += '</section></section></li>';
      liList += liStr;
    }
    return liList;
  },
};

$(function() {
  page.init();
});
