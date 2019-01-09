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

function getMaterie(){ 
	$("#loader").show();

	$.ajax({
		url: absolutePath+"/GetMaterie",
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