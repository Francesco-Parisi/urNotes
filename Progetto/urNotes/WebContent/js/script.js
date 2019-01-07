/*MENU*/
$(document).ready(function(){
			$(".header_icon").click(function(e){
				$(".header_menu").toggleClass('is-open');
				e.preventDefault();
			});
			
			
			resizeContent();
			$( window ).resize(function() {
				resizeContent();
			});	
	 });

$(document).ready(function(){

	$(".header_icon").click(function(e){

		$(".header_menu").toggleClass('is-open');
		e.preventDefault();

	});
});
//checkEmail('prova@email.it');
function checkEmail(email){
	var $email = email;
	var re = /[A-Z0-9._%+-]+@[A-Z0-9.-]+.[A-Z]{2,4}/igm;
	if ($email == '' || !re.test($email)){
		return false;
	}
	else{
		return true;
	}
}



function resizeContent(){
	$("#content").css("min-height", ($(window).height() - $("#header").height() - $("#footer").height())+"px");	
}



function showAlert(flag, descrizione){
	toastr.options = {
	  "closeButton": true,
	  "debug": false,
	  "newestOnTop": false,
	  "progressBar": false,
	  "positionClass": "toast-bottom-right",
	  "preventDuplicates": false,
	  "onclick": null,
	  "showDuration": "300",
	  "hideDuration": "1000",
	  "timeOut": "5000",
	  "extendedTimeOut": "1000",
	  "showEasing": "swing",
	  "hideEasing": "linear",
	  "showMethod": "fadeIn",
	  "hideMethod": "fadeOut"
	}
	
	if(flag == 0){ //Tutto OK
		toastr.success(descrizione, "Operazione Effettuata");
	}
	else if(flag == 1){ //Errore		
		toastr.error(descrizione, "Operazione Fallita");
	}	
}