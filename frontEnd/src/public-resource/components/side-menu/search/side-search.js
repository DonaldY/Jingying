'use strict';

require('./side-search.css');

var page = {
    init: function() {
	this.bindEvent();
    },

    bindEvent: function() {

	$('#mask-area').click(function() {
	    $('.show_panel').css("width", "0");
	   
	});
	
	$('#input-goods-search').keyup(function() {
	    var text = $.trim($('#input-goods-search').val());
	    if (text) {
		$('#search-clear').show();
	    } else {
		$('#search-clear').hide();
	    }
	});
    },

    open: function() {

    },

    close: function() {

    }
};

$(function() {
    page.init();
});
