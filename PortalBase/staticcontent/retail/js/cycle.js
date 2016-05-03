$(function() {
	$('#slideshow').cycle({
		fx : 'scrollHorz',
		timeout : 9000,
		prev : '#prev',
		next : '#next',
		pager : '#nav',
		pagerAnchorBuilder : pagerFactory
	});

	function pagerFactory(idx, slide) {
		var s = idx > 5 ? 'style="display:none;"' : '';
		return '<li>' + s + '<a href="#">' + (idx + 1) + '</a></li>';
	}
	;

	$('#pause').click(function() {
		$('#slideshow').cycle('pause');
		return false;
	});
	$('#play').click(function() {
		$('#slideshow').cycle('resume');
		return false;
	});

//	$('#demos').hover(function() {
//		$('#controls').fadeIn();
//	}, function() {
//		$('#controls').fadeOut();
//	});

});