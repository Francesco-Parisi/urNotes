$(document).ready(function(){
	
	$(document).on('click', '#buttonAggiungiMateria', function(e){
		
		if($("#formAggiungiMateria").css("display") == "block"){
			$("#formAggiungiMateria").html("");
			$("#formAggiungiMateria").css("display", "none");
		}
		else{
			$("#loader").show();
			
			$.ajax({
				url: absolutePath+"/GetFormAggiungiMateria",
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
						$("#formAggiungiMateria").html(msg.contenuto);
						$("#formAggiungiMateria").css("display", "block");
					}
				},
				error: function(msg){
					showAlert(1, "Impossibile Recuperare i dati.");
				}
			});
			
			$("#loader").hide();			
		}
	});	
	
	$(document).on('click', '#confirmAggiungiMateria', function(e){		
		var nome = $("#nome").val();		
		
		if(nome != undefined && nome.length > 1){		
			$("#loader").show();			
			$.ajax({
				url: absolutePath+"/AggiungiMateria",
				type: "POST",
				dataType: 'JSON',
				async: false,
				data: {
					"nome": nome,
				},
				success:function(msg){
					if(!msg.risultato){
						showAlert(1, msg.errore);
					}
					else{
						showAlert(0, msg.contenuto);
						$("#formAggiungiMateria").html("");
						$("#formAggiungiMateria").css("display", "none");
						getMaterie();
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
	});	
		
	
	$(document).on('click', '.eliminaMateria', function(e){		
		var nome = $(this).data("nome");
		
		if(nome != undefined && nome.length > 1){		
			if(confirm("Conferma la cancellazione della materia con Nome"+nome+"?")){
				$("#loader").show();			
				$.ajax({
					url: absolutePath+"/EliminaMateria",
					type: "POST",
					dataType: 'JSON',
					async: false,
					data: {
						"nome": nome
					},
					success:function(msg){
						if(!msg.risultato){
							showAlert(1, msg.errore);
						}
						else{
							showAlert(0, msg.contenuto);
							getMaterie();
						}
					},
					error: function(msg){
						showAlert(1, "Impossibile Recuperare i dati.");
					}
				});
				
				$("#loader").hide();
			}
		}
		else{			
			showAlert(1, "Errore Parametri.");
		}		
	});					
	
			
});
$(document).on('click', '#idDispensa', function(e){
	$(document).prev('table').prop('materieTable', 'prodottiTable');
});

$(document).on('click', '#idDispensa', function(e){
	var ids = [];
	var values = [];
	$(this).each(function( index ) {
		if($(this).data("id") != undefined && $(this).data("id") > 0 && $(this).val() != undefined){			
			ids.push($(this).data("id"));
			values.push($(this).val());
		}
	});		
	
	if(ids.length > 0 && ids.length == values.length){
		var continua = 1;
		var cont = "";
		for(var i = 0; i < ids.length && continua == 1; i++){
			if(ids[i] > 0){
				$("#loader").show();
				$.ajax({
					url: absolutePath+"/GetDispense",
					type: "POST",
					dataType: 'JSON',
					async: false,
					data: {
						"richiesta": 1,
						"id": ids[i],
						"value": values[i],			
					},
					success:function(msg){
						if(!msg.risultato){
							showAlert(1, msg.errore);
							continua *= 0;
						}
						else{	
							continua *= 1;
							if(msg.contenuto.length > 0){
								$("th").text("Dispense");
								$("#bodyMaterie").html(msg.contenuto+"" +
										"<br><tr><td>" +
										"<input type='button' onclick='location.reload()' value='Torna alle Materie' />" +
										"</td></tr>");
							}											
							else{
								$("th").text("Dispense");
								$("#bodyMaterie").html("<tr><td colspan='10'>Nessuna Materia Presente</td></tr>+" +
										"<br><tr><td>" +
										"<input type='button' onclick='location.reload()' value='Torna alle Materie' />" +
										"</td></tr>");
							}
							
						}
					},
					/*success:function(msg){
						if(!msg.risultato){
							showAlert(1, msg.errore);
							continua *= 0;
						}
						else{								
							continua *= 1;
							cont = msg.contenuto;
						}
					},*/
					error: function(msg){
						showAlert(1, "Impossibile Recuperare i dati.");
					}
					/*error: function(msg){
						continua *= 0;
						showAlert(1, "Impossibile Recuperare i dati.");
					}*/
				});			
				$("#loader").hide();					
			}
		}
		if(continua == 1){
			showAlert(0, cont);
			//location.reload();
		}
		return false;						
	}
	else{
		showAlert(1, "Errore prelevamento valori, compilare tutti i campi correttamente.");
	}

	return false;			
});

function getMaterieDispense(){ 
	$("#loader").show();

	$.ajax({
		url: absolutePath+"/GetMaterieDispense",
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
				if(msg.contenuto.length > 0){
					$("#bodyMaterie").html(msg.contenuto);
				}											
				else{
					$("#bodyMaterie").html("<tr><td colspan='10'>Nessuna Materia Presente</td></tr>");
				}
				
			}
		},
		error: function(msg){
			showAlert(1, "Impossibile Recuperare i dati.");
		}
	});
	
	$("#loader").hide();	
	
}