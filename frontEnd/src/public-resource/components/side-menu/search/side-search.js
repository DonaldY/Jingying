'use strict';

require('./side-search.css');

var sideSearchPage = {
    init: function() {
	this.bindEvent();
    },

    bindEvent: function() {

	$('#mask-area').click(this.hideEvent);
	
	$('#input-goods-search').keyup(this.cleanInputEvent);
    },

    showEvent: function() {
	$('#search-panel').show();

	$('#search-panel').css("opacity", "1");
	$('#search-panel').css("transition", "opacity 0.4s linear 0.01s");

	setTimeout(function() {
	    $('.show_panel').css("left", "0");
	}, 500);

	setTimeout(function() {
	    $('#search-panel').css("transition", "");
	}, 1000);
    },

    hideEvent: function() {
	$('.show_panel').css("left", "-100%");
	
	setTimeout(function() {
	    $('#search-panel').css("opacity", "0");
	    $('#search-panel').css("transition", "opacity 0.4s linear 0.01s");
	}, 500);
	
	setTimeout(function() {
	    $('#search-panel').css("transition", "");
	    $('#search-panel').hide();
	}, 1300);
    },

    cleanInputEvent: function() {
	var text = $.trim($('#input-goods-search').val());
	if (text) {
	    $('#search-clear').show();
	} else {
	    $('#search-clear').hide();
	}
    }
};

module.exports = sideSearchPage;
