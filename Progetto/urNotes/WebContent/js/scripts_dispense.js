$(document).ready(function(){
	$('#prodottiTable').DataTable( {
        "order": [[ 0, "desc" ]],
        "language": {
			    "sEmptyTable":     "Nessun Prodotto Presente",
			    "sInfo":           "Vista da _START_ a _END_ di _TOTAL_ elementi",
			    "sInfoEmpty":      "Vista da 0 a 0 di 0 elementi",
			    "sInfoFiltered":   "(filtrati da _MAX_ elementi totali)",
			    "sInfoPostFix":    "",
			    "sInfoThousands":  ".",
			    "sLengthMenu":     "Visualizza _MENU_ elementi",
			    "sLoadingRecords": "Caricamento...",
			    "sProcessing":     "Elaborazione...",
			    "sSearch":         "Cerca:",
			    "sZeroRecords":    "La ricerca non ha portato alcun risultato.",
			    "oPaginate": {
			        "sFirst":      "Inizio",
			        "sPrevious":   "Precedente",
			        "sNext":       "Successivo",
			        "sLast":       "Fine"
			    },
			    "oAria": {
			        "sSortAscending":  ": attiva per ordinare la colonna in ordine crescente",
			        "sSortDescending": ": attiva per ordinare la colonna in ordine decrescente"
			    }
        }        
    } );
	
	$(document).on('click', '#buttonAggiungiDispensa', function(e){
		
		if($("#formAggiungiDispensa").css("display") == "block"){
			$("#formAggiungiDispensa").html("");
			$("#formAggiungiDispensa").css("display", "none");
		}
		else{
			$("#loader").show();
			
			$.ajax({
				url: absolutePath+"/GetFormAggiungiDispensa",
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
						$("#formAggiungiDispensa").html(msg.contenuto);
						$("#formAggiungiDispensa").css("display", "block");
					}
				},
				error: function(msg){
					showAlert(1, "Impossibile Recuperare i dati.");
				}
			});
			
			$("#loader").hide();			
		}
	});	
	
	$(document).on('click', '#confirmAggiungiDispensa', function(e){		
		var titolo = $("#titolo").val();
		var pagine = $("#pagine").val();
		var universita = $("#universita").val();
		var nome_materia = $("#nome_materia").val();
		var descrizione = $("#descrizione").val();
		var prezzoDispensa = $("#prezzoDispensa").val();
		var tipo = $("#tipo").val();
		
		
		if(titolo != undefined && titolo.length > 1 && pagine != undefined && pagine > 0 && universita != undefined && universita.length > 1 && universita != undefined && universita.length > 1 && nome_materia != undefined && nome_materia .length > 1 && descrizione != undefined && descrizione.length > 1 && prezzoDispensa != undefined && prezzoDispensa > 0 && tipo != undefined && tipo.length > 1 ){		
			$("#loader").show();			
			$.ajax({
				url: absolutePath+"/AggiungiDispensa",
				type: "POST",
				dataType: 'JSON',
				async: false,
				data: {
					"titolo": titolo,
					"pagine": pagine,
					"universita": universita,
					"nome_materia": nome_materia,
					"descrizione": descrizione,
					"prezzoDispensa": prezzoDispensa,
					"tipo": tipo
				},
				success:function(msg){
					if(!msg.risultato){
						showAlert(1, msg.errore);
					}
					else{
						showAlert(0, msg.contenuto);
						$("#formAggiungiDispensa").html("");
						$("#formAggiungiDispensa").css("display", "none");
						getDispense();
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
		
	
	$(document).on('click', '.eliminaDispensa', function(e){		
		var idDispensa = $(this).data("idDispensa");
		
		if(idDispensa != undefined && idDispensa > 0){		
			if(confirm("Conferma la cancellazione della dispensa con ID "+idDispensa+"?")){
				$("#loader").show();			
				$.ajax({
					url: absolutePath+"/EliminaDispensa",
					type: "POST",
					dataType: 'JSON',
					async: false,
					data: {
						"idDispensa": idDispensa
					},
					success:function(msg){
						if(!msg.risultato){
							showAlert(1, msg.errore);
						}
						else{
							showAlert(0, msg.contenuto);
							getDispense();
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
	
	$(document).on('click', '.fotoDispensa', function(e){		
		var idDispensa = $(this).data("idDispensa");
		
		if(idDispensa != undefined && idDispensa > 0){		
			window.location.href = 'prodotti_immagini.jsp?idDispensa='+idDispensa;
		}
		else{			
			showAlert(1, "Errore Parametri.");
		}		
	});			
	
	
	$(document).on('click', '.modificaQuantita', function(e){		
		var idDispensa = $(this).data("idDispensa");
		
		if(idDispensa != undefined && idDispensa > 0){		
				$("#loader").show();			
				$.ajax({
					url: absolutePath+"/GetFormModificaQuantitaDispensaAdmin",
					type: "POST",
					dataType: 'JSON',
					async: false,
					data: {
						"idDispensa": idDispensa
					},
					success:function(msg){
						if(!msg.risultato){
							showAlert(1, msg.errore);
						}
						else{														
							$("#modalDettaglioOrdineBody").html(msg.contenuto);
							$("#modalDettaglioOrdine").css("display", "block");
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
	});		

	$(document).on('click', '#confirmAggiungiQuantitaDispensa', function(e){		
		var idDispensa = $("#idDispensa").val();
		var quantitaDispensa = $("#quantitaDispensa").val();				
		if(idDispensa != undefined && idDispensa > 0){
			if(quantitaDispensa != undefined && quantitaDispensa >= 0){
				$("#loader").show();			
				$.ajax({
					url: absolutePath+"/AggiornaQuantitaDispensaAdmin",
					type: "POST",
					dataType: 'JSON',
					async: false,
					data: {
						"idDispensa": idDispensa,
						"quantitaDispensa": quantitaDispensa
					},
					success:function(msg){
						if(!msg.risultato){
							showAlert(1, msg.errore);
						}
						else{														
							$("#modalDettaglioOrdine").css("display", "none");
							getDispense();
							showAlert(0, msg.contenuto);
						}
					},
					error: function(msg){
						showAlert(1, "Impossibile Recuperare i dati.");
					}
				});
				
				$("#loader").hide();				
			}	
			else{
				showAlert(1, "Inserire una quantit&agrave; valida.");
			}
		}
		else{			
			showAlert(1, "Errore Parametri.");
		}		
	});		
	
	
	$(document).on('click', '.modificaPrezzo', function(e){		
		var idDispensa = $(this).data("idDispensa");
		
		if(idDispensa != undefined && idDispensa > 0){		
				$("#loader").show();			
				$.ajax({
					url: absolutePath+"/GetFormModificaPrezzoDispensaAdmin",
					type: "POST",
					dataType: 'JSON',
					async: false,
					data: {
						"idDispensa": idDispensa
					},
					success:function(msg){
						if(!msg.risultato){
							showAlert(1, msg.errore);
						}
						else{														
							$("#modalDettaglioOrdineBody").html(msg.contenuto);
							$("#modalDettaglioOrdine").css("display", "block");
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
	});		

	$(document).on('click', '#confirmAggiungiPrezzoDispensa', function(e){		
		var idDispensa = $("#idDispensa").val();
		var prezzoDispensa = $("#prezzoDispensa").val();				
		if(idDispensa != undefined && idDispensa > 0){
			if(prezzoDispensa != undefined && prezzoDispensa > 0){
				
				$("#loader").show();			
				$.ajax({
					url: absolutePath+"/AggiornaPrezzoDispensaAdmin",
					type: "POST",
					dataType: 'JSON',
					async: false,
					data: {
						"idDispensa": idDispensa,
						"prezzoDispensa": prezzoDispensa
					},
					success:function(msg){
						if(!msg.risultato){
							showAlert(1, msg.errore);
						}
						else{														
							$("#modalDettaglioOrdine").css("display", "none");
							getDispense();
							showAlert(0, msg.contenuto);
						}
					},
					error: function(msg){
						showAlert(1, "Impossibile Recuperare i dati.");
					}
				});
				
				$("#loader").hide();

			}	
			else{
				showAlert(1, "Inserire un prezzo maggiore di 0.");
			}		
		}
		else{			
			showAlert(1, "Errore Parametri.");
		}		
	});		
	
	
	$(document).on('click', '.chiudiModalDettaglioOrdine', function(e){
		$("#modalDettaglioOrdine").css("display", "none");
	});			
});

$(document).on('click', '#idDispensaDett', function(e){	
	var ids = [];
	var values = [];
	$(this).each(function( index ) {
		if($(this).data("id") != undefined && $(this).data("id") > 0 && $(this).val() != undefined){			
			ids.push($(this).data("id"));
			values.push($(this).data("id"));
		}
	});		
	
	if(ids.length > 0 && ids.length == values.length){
		var continua = 1;
		var cont = "";
		for(var i = 0; i < ids.length && continua == 1; i++){
			if(ids[i] > 0){
				$("#loader").show();
				$.ajax({
					url: absolutePath+"/GetDispenseDett",
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
				}
			else{
				return false;
			}
		}
		if(continua == 1){
			showAlert(0, cont);
			location.href(absolutePath +"/prodotto_dettaglio.jsp?codice="+cont);
		}
		return false;						
	}
	else{
		showAlert(1, "Errore prelevamento valori, compilare tutti i campi correttamente.");
	}

	return false;			
});


function getDispense(){ 
	$("#loader").show();
	$.ajax({
		url: absolutePath+"/GetDispense",
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
					$("#bodyMaterie").html("<tr><td colspan='10'>Nessuna Dispensa Presente</td></tr>");
				}
				
			}
		},
		error: function(msg){
			showAlert(1, "Impossibile Recuperare i dati.");
		}
	});
	
	$("#loader").hide();	
	
}