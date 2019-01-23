$(document).ready(function(){	
	
	 $(document).on('click', '#confirmAggiungiIndirizzo', function(e){        
	        var nome = $("#nome").val();
	        var cognome = $("#cognome").val();
	        var indirizzo = $("#indirizzo").val();
	        var cap = $("#cap").val();
	        var citta = $("#citta").val();
	        var provincia = $("#provincia").val();
	        var telefono = $("#telefono").val();
	        var cellulare = $("#cellulare").val();
	        var id_utente = $("#id_utente").val();
	        
	        if(id_utente != undefined && id_utente > 0){
	            if(nome != undefined && cognome != undefined && indirizzo != undefined && cap != undefined && citta != undefined && provincia != undefined && telefono != undefined && cellulare != undefined
	               && nome.length > 1 && cognome.length > 1 && indirizzo.length > 1 && cap > 0 && citta.length > 1 && provincia.length > 1){        
	                $("#loader").show();            
	                $.ajax({
	                    url: absolutePath+"/AggiungiIndirizzo",
	                    type: "POST",
	                    dataType: 'JSON',
	                    async: false,
	                    data: {
	                        "nome": nome,
	                        "cognome": cognome,
	                        "indirizzo": indirizzo,
	                        "cap": cap,
	                        "citta": citta,
	                        "provincia": provincia,
	                        "telefono": telefono,
	                        "cellulare": cellulare
	                    },
	                    success:function(msg){
	                        if(!msg.risultato){
	                            showAlert(1, msg.errore);
	                        }
	                        else{
	                            showAlert(0, msg.contenuto);
	                            $("#formAggiungiIndirizzo").html("");
	                            $("#formAggiungiIndirizzo").css("display", "none");
	                            setTimeout(function(){ location.reload(); }, 1000);                            
	                        }
	                    },
	                    error: function(msg){
	                        showAlert(1, "Impossibile Recuperare i dati.");
	                    }
	                });
	                
	                $("#loader").hide();
	            }
	            else{            
	                showAlert(1, "Controllare di aver compilato correttamente tutti i campi.");
	            }            
	        }
	        else{
	            showAlert(1, "Errore Parametri.");
	        }        
	    });    
	    
	    $(document).on('click', '#buttonAggiungiIndirizzo', function(e){
	        
	        if($("#formAggiungiIndirizzo").css("display") == "block"){
	            $("#formAggiungiIndirizzo").html("");
	            $("#formAggiungiIndirizzo").css("display", "none");
	        }
	        else{        
	            $("#loader").show();
	            
	            $.ajax({
	                url: absolutePath+"/GetFormAggiungiIndirizzo",
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
	                        $("#formAggiungiIndirizzo").html(msg.contenuto);
	                        $("#formAggiungiIndirizzo").css("display", "block");
	                    }
	                },
	                error: function(msg){
	                    showAlert(1, "Impossibile Recuperare i dati.");
	                }
	            });
	            
	            $("#loader").hide();
	        }
	    });        
	    
	    $(document).on('click', '.eliminaIndirizzo', function(e){
	        var id_indirizzo = $(this).data("id_indirizzo");
	        var form = $(this).closest("form");
	        if(id_indirizzo != undefined && id_indirizzo > 0){
	            if(confirm("Conferma la cancellazione dell'indirizzo selezionato?")){                
	                $("#loader").show();
	                $.ajax({
	                    url: absolutePath+"/EliminaIndirizzo",
	                    type: "POST",
	                    dataType: 'JSON',
	                    async: false,
	                    data: {
	                        "id_indirizzo": id_indirizzo    
	                    },
	                    success:function(msg){
	                        if(!msg.risultato){
	                            showAlert(1, msg.errore);
	                        }
	                        else{                                
	                            showAlert(0, msg.contenuto);    
	                            form.remove();
	                        }
	                    },
	                    error: function(msg){                            
	                        showAlert(1, "Impossibile Recuperare i dati.");
	                    }
	                });            
	                $("#loader").hide();
	            }
	            return false;
	        }
	        else{
	            showAlert(1, "Errore Parametri.");
	        }
	    });    
		
	
	$(document).on('submit', '#formImpostazioniUser', function(e){
		var id_utente = $("#id_utente").val();
		var nome = $("#nome").val();
		var cognome = $("#cognome").val();
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
				url: absolutePath+"/SalvaProfiloUser",
				type: "POST",
				dataType: 'JSON',
				async: false,
				data: {
					"nome": nome,
					"cognome": cognome,
					"id_utente": id_utente,
					"tipo_utente": tipo_utente,
					"password": password
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
