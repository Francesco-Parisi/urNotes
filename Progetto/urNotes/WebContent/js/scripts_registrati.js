$(document).ready(function(){
	$(document).on('submit', '#formRegistrati', function(e){
		var username = $("#username").val();
		var nome = $("#nome").val();
		var cognome = $("#cognome").val();
		var email = $("#email").val();
		var password = $("#password").val();
		var confermaPassword = $("#confermaPassword").val();

		
		var continua = 1;
	
		if(username.length<3 || username.length>20){
			   if(username == null || username == "") {
			showAlert(1, "Inserisci Username Correttamente");
			continua *= 0;
			}
			showAlert(1, "Errore Lunghezza Username");
			continua *= 0;
		}
		
		
		
		if(nome.length<3 || nome.length>50){	
			   if(username == null || username == "") {
			showAlert(1, "Inserisci Nome Correttamente");
			continua *= 0;
			}
			showAlert(1, "Errore Lunghezza Nome");
			continua *= 0;
		}
		
		if(cognome.length<3 || cognome.length>20){	
			   if(cognome == null || cognome == "") {
			showAlert(1, "Inserisci Cognome Correttamente");
			continua *= 0;
			}
			showAlert(1, "Errore Lunghezza Cognome");
			continua *= 0;
		}
		
		if(email == undefined || email == "" || !checkEmail(email)){			
			showAlert(1, "Inserire un'Email valida");
			continua *= 0;
		}
		if(password == undefined || password == "" || password.length < 6){			
			showAlert(1, "Inserire una Password valida di almeno 6 caratteri");
			continua *= 0;
		}
		if(confermaPassword == undefined || password != confermaPassword){			
			showAlert(1, "Controllare che le due password coincidano");
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
					"password": password
				},
				success:function(msg){
					if(!msg.risultato){
						showAlert(1, msg.errore);
					}
					else{
						
						setTimeout(function() {
							  window.location.href = "/urNotes/accedi.jsp";
							}, 3000);

					}
					showAlert(0, msg.contenuto);
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
