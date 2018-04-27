'use strict';

require('./search.css');
require('../../public-resource/components/common/default.css');

require('../../public-resource/components/header/search/head-search.js');

// var productServer = require('../../public-resource/utils/server/product-service.js');
const util = require('../../public-resource/utils/default.js');

var page = {
  data: {
    listParam: {
      keyword: util.getUrlParam('keyword') || '',
      pageNumber: util.getUrlParam('pageNumber') || 1,
    },
  },
  init: function() {
    this.bindEvent();
    this.loadList();
  },

  bindEvent: function() {

  },
  loadList: function() {
    // var listHtml = '';
    var listParam = this.data.listParam;
    var _this = this;

    $.ajax({
      url: '/Jingying/product/ajaxProductList',
      data: listParam,
      dataType: 'json',
      success: function(result) {
        var spuList = result.list;
        var liStr = _this.jointLi(spuList);
        $('.list_wrap').append(liStr);
        listParam.pageNumber++;
      },
      error: function(XMLHttpRequest, textStatus, errorThrown) {
        alert(XMLHttpRequest.status);
        alert(XMLHttpRequest.readyState);
        alert(textStatus);
      },
    });

    // productServer.getProductListByKeyword(listParam, function(res) {
    //   alert('hahah');
    // }, function(errMsg) {
    //   alert('没拿到数据');
    // });
  },
  jointLi: function(list) {
    var liList = '';
    for (var i = 0; i < list.length; ++i) {
      var liStr = "<li class='list_item'><a class='list_item_link' href='";
      liStr += util.getServerUrl('/Jingying/product/detail?id=' + list[i].id) + "'>";
      liStr += "<div class='img_wrap'><img src='";
      liStr += util.getFtpHost() + list[i].main_image + "' style='opacity:1;'></div>";
      liStr += "<div class='item_content'><p class='item_name'>" + list[i].name + '</p>';
      liStr += "<div class='item_pri_wrap'><p class='cur_pri_wrap'><em class='money'>￥</em>";
      liStr += "<em class='int_price'>" + parseInt(list[i].min_price) + '.</em>';
      liStr += "<em class='decimal_price'>" + (list[i].min_price + '').split('.')[1] + '</em>';
      liStr += '</p></div></div>';
      liList += liStr;
    }
    return liList;
  },
};

$(function() {
  page.init();
});
