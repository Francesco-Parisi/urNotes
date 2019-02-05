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

//checkEmail('prova@email.it');
function checkEmail(email){
	var $email = email;
	var re = /\b[A-Z0-9._%+-]+@(?:[A-Z0-9-]+\.)+[A-Z]{2,6}\b/i;
	if ($email == '' || !re.test($email)){
		return false;
	}
	else{
		return true;
	}
}


//Scrollup
$(document).ready(function () {

    $(window).scroll(function () {
        if ($(this).scrollTop() > 800) {
            $('.scrollup').fadeIn();
        } else {
            $('.scrollup').fadeOut();
        }
    });

    $('.scrollup').click(function () {
        $("html, body").animate({
            scrollTop: 0
        }, 600);
        return false;
    });

});

//Show FAQ
$(document).ready(function(){
	  $("#faq_button").click(function(){
	    $(".faq_content").toggle();
	  });
	});

$(document).ready(function(){
	  $("#faq_button1").click(function(){
	    $(".faq_content1").toggle();
	  });
	});

$(document).ready(function(){
	  $("#faq_button2").click(function(){
	    $(".faq_content2").toggle();
	  });
	});

$(document).ready(function(){
	  $("#faq_button3").click(function(){
	    $(".faq_content3").toggle();
	  });
	});

$(document).ready(function(){
	  $("#faq_button4").click(function(){
	    $(".faq_content4").toggle();
	  });
	});

$(document).ready(function(){
	  $("#faq_button5").click(function(){
	    $(".faq_content5").toggle();
	  });
	});


function resizeContent(){
	$("#content").css("min-height", ($(window).height() - $("#header").height() - $("#footer").height())+"px");	
}


$(document).on('click', '.product-title, .product-image', function() { //Click sul nome del prodotto vai al dettaglio
	var codice = $(this).data("codice");
	if(codice != undefined && codice > 0){
		window.location.href = absolutePath+"/prodotto_dettaglio.jsp?codice="+codice;
	}
	else{
		showAlert(1, "Errore Parametri.");
	}
});

$(document).on('click', '.product-button', function() { //Click su bottone giallo aggiungi al carrello
	var codice = $(this).data("codice");
	if(codice != undefined && codice > 0){
		aggiungiAlCarrello(codice, 1);
	}
	else{
		showAlert(1, "Errore Parametri.");
	}
	return false;
});			


$(document).on('click', '.showImmagineDocumento', function(e){		//Per vedere un'immagine del prodotto nel modal
	var src = $(this).attr("src");
	if(src != undefined){		
		$("#modalImmaginiBody").html('<img src="'+src+'" alt="'+src+'" />');
		$("#modalImmagini").css("display", "block");
		
		return false;
	}
	else{			
		showAlert(1, "Errore Parametri.");
	}		
	return false;
});	

$(document).on('click', '.chiudiModalImmagini', function(e){	//Per chiudere il modal quando viene mostrata una foto
	$("#modalImmagini").css("display", "none");
});	

function getCarrelloSmall(){ 
	$("#loader").show();	
	$.ajax({
		url: absolutePath+"/GetCarrelloSmall",
		type: "POST",
		dataType: 'JSON',
		async: false,
		data: {
			"richiesta": 1
		},
		success:function(msg){
			if(!msg.risultato){
				showAlert(1, msg.errore);
			}
			else{
				$(".numeroDocumentiCarrello").html(msg.contenuto);
			}
		},
		error: function(msg){
			showAlert(1, "Impossibile Recuperare i dati.");
		}
	});	
	$("#loader").hide();		
}

function aggiungiAlCarrello(codice, quantita){
	if(codice > 0 && quantita > 0){
		$("#loader").show();
		
		$.ajax({
			url: absolutePath+"/AggiungiAlCarrello",
			type: "POST",
			dataType: 'JSON',
			async: false,
			data: {
				"codice": codice,
				"quantita": quantita
			},
			success:function(msg){
				if(!msg.risultato){
					showAlert(1, msg.errore);
				}
				else{
					if(msg.redirect){
						window.location.href = absolutePath+msg.urlRedirect;
					}
					else{
						getCarrelloSmall();
						showAlert(0, msg.contenuto);
					}					
				}
			},
			error: function(msg){
				showAlert(1, "Impossibile Recuperare i dati.");
			}
		});
		
		$("#loader").hide();		
	}
	else{
		showAlert(1, "Errore Parametri.");
	}	
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
