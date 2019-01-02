$(document).ready(function(){
	
	
	
	
	
	$(document).on('submit', '#formRegistrati', function(e){
		var username = $("#username").val();
		var nome = $("#nome").val();
		var cognome = $("#cognome").val();
		var email = $("#email").val();
		var password = $("#password").val();
		var confermaPassword = $("#confermaPassword").val();
		var tipoUtente = $("#tipo_utente").val();

		
		var continua = 1;
		if(username == undefined || username == "" || username.length < 1){			
			showAlert(1, "Inserire un username");
			continua *= 0;
		}
		if(nome == undefined || nome == "" || nome.length < 1){			
			showAlert(1, "Inserire un nome");
			continua *= 0;
		}
		if(cognome == undefined || cognome == "" || cognome.length < 1){			
			showAlert(1, "Inserire un cognome");
			continua *= 0;
		}
		if(email == undefined || email == "" || !checkEmail(email)){			
			showAlert(1, "Inserire un'email valida");
			continua *= 0;
		}
		if(password == undefined || password == "" || password.length < 6){			
			showAlert(1, "Inserire una password valida di almeno 6 caratteri");
			continua *= 0;
		}
		if(confermaPassword == undefined || password != confermaPassword){			
			showAlert(1, "Controllare che le due password coincidano");
			continua *= 0;
		}
		if(tipoUtente == undefined || tipoUtente > 2 || tipoUtente < 1){			
			showAlert(1, "Inserire un Tipo Utente valido");
			continua *= 0;
		}
	
		if(continua){
			$("#loader").show();

			$.ajax({
				url: absolutePath+"/SalvaUtente",
				type: "POST",
				dataType: 'JSON',
				async: false,
				data: {
					"username": username,
					"nome": nome,
					"cognome": cognome,
					"email": email,
					"password": password,
					"tipoUtente": tipoUtente
				},
				success:function(msg){
					if(!msg.risultato){
						showAlert(1, msg.errore);
					}
					else{
						showAlert(0, msg.contenuto);
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
		return false;
	});	
	
	
	
	
	
});
