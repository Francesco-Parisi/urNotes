$(document).ready(function(){
		
	
	$(document).on('submit', '#formImpostazioniAdmin', function(e){
		var id_utente = $("#id_utente").val();
		var nome = $("#nome").val();
		var cognome = $("#cognome").val();
		var username = $("#username").val();
		var password = $("#password").val();
		var ripetiPasswordUtente = $("#ripetiPasswordUtente").val();

		var continua = 1;
		if(nome == undefined || nome == "" || nome.length < 1){			
			showAlert(1, "Inserire un nome");
			continua *= 0;
		}
		if(cognome == undefined || cognome == "" || cognome.length < 1){			
			showAlert(1, "Inserire un cognome");
			continua *= 0;
		}
		if(username == undefined || username == "" || username.length < 1){			
			showAlert(1, "Inserire un username");
			continua *= 0;
		}
		
		if(id_utente == undefined || id_utente <= 0){			
			showAlert(1, "Errore Parametri");
			continua *= 0;
		}

		var tipo_utente = 2;
		if(password != undefined && password.length > 1){ //Cambio anche la pwd
			if(ripetiPasswordUtente != undefined && ripetiPasswordUtente.length > 1 && password == ripetiPasswordUtente){
				tipo_utente = 1
				continua *= 1;
			}
			else{
				showAlert(1, "Controllare che le due password coincidano.");
				continua *= 0;
			}
		}		
		
		if(continua){
			$("#loader").show();
			$.ajax({
				url: absolutePath+"/SalvaProfiloAdmin",
				type: "POST",
				dataType: 'JSON',
				async: false,
				data: {
					"nome": nome,
					"cognome": cognome,
					"username": username,
					"id_utente": id_utente,
					"tipo_utente": tipo_utente
				},
				success:function(msg){
					if(!msg.risultato){
						showAlert(1, msg.errore);
					}
					else{								
						showAlert(0, msg.contenuto);	
						setTimeout(function(){ location.reload(); }, 1000);
					}
				},
				error: function(msg){							
					showAlert(1, "Impossibile Recuperare i dati.");
				}
			});			
			$("#loader").hide();					
			
		}
		
		
		return false;					
	});	
	
	
	
	
	
});
