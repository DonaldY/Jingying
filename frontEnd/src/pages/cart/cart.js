'use strict';

require('./cart.css');
require('../../public-resource/iconfont/font-style.css');
require('../../public-resource/components/common/default.css');

const util = require('../../public-resource/utils/default.js');
const cartServer = require('../../public-resource/utils/server/cart-service.js');

var page = {
  data: {
    listParam: {
      cartId: 0,
      status: 1,
    },
  },
  init: function() {
    this.loadMore();
    this.bindEvent();
  },

  bindEvent: function() {
    var _this = this;

    var listParam = this.data.listParam;
    // 单个选择
    $(document).on('click', '.item_select', function() {
      var $this = $(this);
      var cartId = $this.data('cartid');
      listParam.cartId = cartId;

      if ($this.hasClass('selected')) {
        listParam.status = 0;
        $this.removeClass('selected');

        _this.unselectAll();

        // 结算
        _this.calculate();

        cartServer.updateChecked(listParam, function(res) {
          console.log('修改成功');
        }, function(err) {
          console.log(err);
        });
      } else {
        listParam.status = 1;
        $this.addClass('selected');

        _this.selectAll();

        // 结算
        _this.calculate();

        cartServer.updateChecked(listParam, function(res) {
          console.log('修改成功');
        }, function(err) {
          console.log(err);
        });
      }
    });

    // 全选
    $('.select_all').click(function() {
      var $this = $(this);
      if ($this.hasClass('selected')) {
        $('.select_all').each(function() {
          $(this).removeClass('selected');
        });
        $('.item_select').each(function() {
          $(this).removeClass('selected');
        });
      } else {
        $('.select_all').each(function() {
          $(this).addClass('selected');
        });
        $('.item_select').each(function() {
          if (!$(this).hasClass('selected')) {
            $(this).addClass('selected');
          }
        });
      }
      // 结算
      _this.calculate();
    });

    // 去结算
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
        _this.calculate();
      },
      error: function() {
        util.errorTips('格格，网呢？');
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
      liStr += "' data-cartid='" + list[i].id + "' data-price='" + list[i].price + "' data-num='" + list[i].quantity + "' >";
      liStr += "<span class='round icon-check'>&nbsp;</span></mark>";
      liStr += "<a class='item_img'><img src='";
      liStr += util.getFtpHost() + list[i].main_image + "' class='item-img' style='opacity: 1; transition: opacity 0.3s;width:80px;'></a>";
      liStr += "<section class='item_info'><section class='item_info_wrap'><section class='info_title'>";
      liStr += list[i].name + "</section><section class='info_num'></section>";
      liStr += "<p class='info_spec'><span>" + list[i].spec_name + '</span></p>';
      liStr += "<p class='info_status'><span class='info_status_label baoyou'>包邮</span></p>";
      liStr += "<p class='info_price_wrap'><span class='info_price'><i>￥";
      liStr += list[i].price + '</i></span></p></section>';
      liStr += "<span class='item_count'>×" + list[i].quantity + '</span>';
      liStr += '</section></section></li>';
      liList += liStr;
    }
    return liList;
  },

  isSelectAll: function() {
    var itemArr = $('.item_select');
    for (var i = 0; i < itemArr.length; ++i) {
      if (!$(itemArr[i]).hasClass('selected')) {
        return false;
      }
    }
    return true;
  },

  selectAll: function() {
    var _this = this;
    if (_this.isSelectAll()) {
      $('.select_all').each(function() {
        if (!$(this).hasClass('selected')) {
          $(this).addClass('selected');
        }
      });
    }
  },

  unselectAll: function() {
    var _this = this;
    if (!_this.isSelectAll()) {
      $('.select_all').each(function() {
        if ($(this).hasClass('selected')) {
          $(this).removeClass('selected');
        }
      });
    }
  },

  calculate: function() {
    var totalPrice = 0;
    var cnt = 0;
    $('.C_item_wrap .selected').each(function() {
      cnt++;
      totalPrice += parseFloat($(this).data('price')) * parseInt($(this).data('num'));
    });
    $('#total-price').text(totalPrice.toFixed(2));
    if (cnt > 0) {
      if ($('.buy_btn').hasClass('disabled')) {
        $('.buy_btn').removeClass('disabled');
      }
      $('.buy_btn').click(function() {
        window.location.href = util.getServerUrl('/order/confirm');
      });
    } else {
      cnt = 0;
      if (!$('.buy_btn').hasClass('disabled')) {
        $('.buy_btn').addClass('disabled');
      }
      $('.buy_btn').unbind();
    }
    $('.buy_btn').text('去结算(' + cnt + ')');
  },
};

$(function() {
  page.init();
});
