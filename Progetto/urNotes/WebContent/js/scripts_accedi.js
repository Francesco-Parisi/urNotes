$(document).ready(function(){	
	$(document).on('submit', '#formAccedi', function(e){
		var Username = $("#Username").val();
		var Password = $("#Password").val();
		
		var continua = 1;
		if(Username == undefined || Username == "" || !checkEmail(Username)){			
			showAlert(1, "Inserire un Nome Utente valido");
			continua *= 0;
		}
		if(Password == undefined || Password == "" || Password.length < 3){			
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
					"Username": Username,
					"Password": Password
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
