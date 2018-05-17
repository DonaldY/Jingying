'use strict';

require('./pop-address.css');
require('../../../iconfont/font-style.css');
require('../../common/default.css');

// const addressServer = require('../../../utils/server/address-service.js');
const util = require('../../../utils/default.js');

var popAddressPage = {

  data: {
    listParam: {
      pageNumber: util.getUrlParam('pageNumber') || 1,
    },
  },

  init: function() {
    this.bindEvent();
    this.hideEvent();
    this.onload();
  },

  bindEvent: function() {
    // var _this = this;
    $('#addre-footer').click(function() {
      var from = util.getRelativeUrl();
      console.log('from:' + from);
      window.location.href = util.getServerUrl('/address');
    });

    // 选择
    $(document).on('click', '.l_addre_li', function() {
      var _this = this;
      $(_this).addClass('l_li_current').siblings().removeClass('l_li_current');
      var _addressId = $(_this).data('id');

      $.ajax({
        url: '/Jingying/address/updateChecked',
        data: {addressId: _addressId},
        dataType: 'json',
        success: function(result) {
          // set addressid
          $('#addre-data').data('addressid', _addressId);

          var rece_default = $(_this).data('rece_default');
          var rece_name = $(_this).data('name');
          var rece_phone = $(_this).data('phone');
          var rece_address = $(_this).data('address');
          $('.addre_ul li').remove();

          var liStr = "<li class='addre_li' id='addre-data' data-addressid='" + _addressId + "'>";
          liStr += "<span class='addre_icon_desc icon-location-address'></span>";
          liStr += "<span id='addre-name'>" + rece_name + '<span>';
          liStr += "<span id='addre-phone'>" + rece_phone + '<span></li>';
          liStr += "<li class='addre_li' style='padding-top:5px;'>";
          if (rece_default === true) {
            liStr += '<em>[默认]</em>';
          }
          liStr += rece_address + '</li>';

          $('.addre_ul').append(liStr);
        },
        error: function() {
          util.errorTips('手滑了下~');
        },
      });

      // 关闭
      $('#address-list').removeClass('animate_top');
    });
  },

  showEvent: function() {
    $('#address-list').addClass('animate_top');
  },

  hideEvent: function() {
    $('#address-list-close').click(function() {
      $('#address-list').removeClass('animate_top');
    });
  },

  onload: function() {
    var _this = this;
    var listParam = _this.data.listParam;
    $.ajax({
      url: '/Jingying/address/list',
      data: listParam,
      dataType: 'json',
      success: function(res) {
        var addresslist = res;
        if (addresslist == null) {
          window.location.href = util.getServerUrl('/address');
        }
        var liStr = _this.jointLi(addresslist);
        $('.l_addre_ul').append(liStr);
        listParam.pageNumber++;
      },
      error: function() {
        util.errorTips('error');
      },
    });
  },

  jointLi: function(list) {
    var liList = '';
    for (var i = 0; i < list.length; ++i) {
      var liStr = "<li class='l_addre_li ";
      if (list[i].checked === true) {
        liStr += ' l_li_current';
      }
      liStr += "' data-id='" + list[i].id + "' data-name='" + list[i].rece_name + "' data-phone='" + list[i].rece_phone;
      liStr += "' data-address='" + list[i].rece_address + "' data-rece_default='" + list[i].rece_default + "' >";
      liStr += "<div class='l_li_div'><mark class='l_li_mark icon-check'>&nbsp;</mark><p>";
      liStr += "<span class='l_li_name'>" + list[i].rece_name + '</span>';
      liStr += "<span class='l_li_tele'>" + list[i].rece_phone + '</span></p>';
      liStr += '<p>';
      if (list[i].rece_default === true) {
        liStr += '<em>[默认]</em>';
      }
      liStr += "<span class='l_li_address'>" + list[i].rece_address + '</span></p></div>';
      liStr += " <a class='l_li_edit' href='/Jingying/address/edit?id=" + list[i].id + "'>编辑</a></li>";
      liList += liStr;
    }
    return liList;
  },
};

$(function() {
  popAddressPage.init();
});

module.exports = popAddressPage;
