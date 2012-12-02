var locale = (navigator.language) ? navigator.language : navigator.userLanguage; 

var mess={
    confirmMessage:{
        'en-US':'Are you sure?',
        'cs':'Jste si opravdu jistí?'
    }
}




$(document).ready(function() {
	

	$("input").click(function() {
		$(this).val('');
	});
	
	
	$("a[wicket\\:id=deleteLink]").click(function() {
		if (confirm(mess['confirmMessage'][locale])) {
			return true;
		} else {
			return false;
		}
	});

});

