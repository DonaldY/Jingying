'use strict';

var page = {
  init: function() {
    this.bindEvent();
  },

  bindEvent: function() {
    $('#p-imgs-info').click(function() {
      $('#p-detail-info').removeClass('active_bottom');
      $('#p-imgs-info').addClass('active_bottom');
      $('.p_tab_spec').removeClass('active');
      $('.p_tab_imgs').addClass('active');
    });

    $('#p-detail-info').click(function() {
      $('#p-imgs-info').removeClass('active_bottom');
      $('#p-detail-info').addClass('active_bottom');
      $('.p_tab_imgs').removeClass('active');
      $('.p_tab_spec').addClass('active');
    });
  },
};

$(function() {
  page.init();
});
