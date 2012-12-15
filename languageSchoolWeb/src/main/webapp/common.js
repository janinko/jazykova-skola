var locale = (navigator.language) ? navigator.language : navigator.userLanguage; 

var mess={
    confirmMessage:{
        'en-US':'Are you sure?',
        'cs':'Jste si opravdu jistí?'
    }
}




$(document).ready(function() {
	

	$('input[type="text"]').click(function() {
		$(this).val('');
	});
	
	
	$("a[wicket\\:id=deleteLink]").click(function() {
		if (confirm(mess['confirmMessage'][locale])) {
			return true;
		} else {
			return false;
		}
	});
	
	$('#expand-457').click(function() {
		$('.lesson-box').fadeOut('slow', function() {
			$('.mini-list').removeClass('mini-list');
			$('.courseName').css('font-size', '16px');
			$(this).fadeIn('fast');
		});
		
		
	});
	
	
	$('.mini-list .courseName').each(function(index) {
		if ($(this).text().length > 49) {
			$(this).css('font-size', '14px');
		}
	});

});

