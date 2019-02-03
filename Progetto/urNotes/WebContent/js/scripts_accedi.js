$(document).ready(function(){	
	$(document).on('submit', '#formAccedi', function(e){
		var username = $("#username").val();
		var password = $("#password").val();
		
		var continua = 1;
		
		if(username.length<3 || username.length>20){
			   if(username == null || username == "") {
			showAlert(1, "Inserisci Username Correttamente");
			continua *= 0;
			}
			showAlert(1, "Errore Lunghezza Username");
			continua *= 0;
		}
		
		if(password == undefined || password == "" || password.length < 6){			
			showAlert(1, "Inserire una Password valida");
			continua *= 0;
		}

		if(continua){
			$("#loader").show();

			$.ajax({
				url: absolutePath+"/Accedi",
				type: "POST",
				dataType: 'JSON',
				async: false,
				data: {
					"username": username,
					"password": password
				},
				success:function(msg){
					if(!msg.risultato){
						showAlert(1, msg.errore);
					}
					else{
						window.location.href = msg.redirect;
					}
				},
				error: function(msg){
					showAlert(1, "Impossibile Recuperare i dati.");
				}
			});
			
			$("#loader").hide();
			return false;
		}
		else{			
			return false;
		}				
	});	
	
	
	
	
	
});
