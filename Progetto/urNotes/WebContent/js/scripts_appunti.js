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
	
	$(document).on('click', '#buttonAggiungiAppunto', function(e){
		
		if($("#formAggiungiAppunto").css("display") == "block"){
			$("#formAggiungiAppunto").html("");
			$("#formAggiungiAppunto").css("display", "none");
		}
		else{
			$("#loader").show();
			
			$.ajax({
				url: absolutePath+"/GetFormAggiungiAppunto",
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
						$("#formAggiungiAppunto").html(msg.contenuto);
						$("#formAggiungiAppunto").css("display", "block");
					}
				},
				error: function(msg){
					showAlert(1, "Impossibile Recuperare i dati.");
				}
			});
			
			$("#loader").hide();			
		}
	});	
	
	$(document).on('click', '#confirmAggiungiAppunto', function(e){		
		var titolo = $("#titolo").val();
		var pagine = $("#pagine").val();
		var universita = $("#universita").val();
		var nome_materia = $("#nome_materia").val();
		var descrizione = $("#descrizione").val();
		var prezzoAppunto = $("#prezzoAppunto").val();
		var tipo = $("#tipo").val();
		
		
		if(titolo != undefined && titolo.length > 1 && pagine != undefined && pagine > 0 && universita != undefined && universita.length > 1 && universita != undefined && universita.length > 1 && nome_materia != undefined && nome_materia .length > 1 && descrizione != undefined && descrizione.length > 1 && prezzoAppunto != undefined && prezzoAppunto > 0 && tipo != undefined && tipo.length > 1 ){		
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
						$("#formAggiungiAppunto").html("");
						$("#formAggiungiAppunto").css("display", "none");
						getAppuinti();
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
		
	
	$(document).on('click', '.eliminaAppunto', function(e){		
		var idAppunto = $(this).data("idAppunto");
		
		if(idAppunto != undefined && idAppunto > 0){		
			if(confirm("Conferma la cancellazione dell'appunto con ID "+idAppunto+"?")){
				$("#loader").show();			
				$.ajax({
					url: absolutePath+"/EliminaAppunto",
					type: "POST",
					dataType: 'JSON',
					async: false,
					data: {
						"idAppunto": idAppunto
					},
					success:function(msg){
						if(!msg.risultato){
							showAlert(1, msg.errore);
						}
						else{
							showAlert(0, msg.contenuto);
							getAppunti();
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
	
	$(document).on('click', '.fotoAppunto', function(e){		
		var idAppunto = $(this).data("idAppunto");
		
		if(idAppunto != undefined && idAppunto > 0){		
			window.location.href = 'prodotti_immagini.jsp?idAppunto='+idAppunto;
		}
		else{			
			showAlert(1, "Errore Parametri.");
		}		
	});			
	
	
	$(document).on('click', '.modificaQuantita', function(e){		
		var idAppunto = $(this).data("idAppunto");
		
		if(idAppunto != undefined && idAppunto > 0){		
				$("#loader").show();			
				$.ajax({
					url: absolutePath+"/GetFormModificaQuantitaAppuntoAdmin",
					type: "POST",
					dataType: 'JSON',
					async: false,
					data: {
						"idAppunto": idAppunto
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

	$(document).on('click', '#confirmAggiungiQuantitaAppunto', function(e){		
		var idAppunto = $("#idAppunto").val();
		var quantitaAppunto = $("#quantitaAppunto").val();				
		if(idAppunto != undefined && idAppunto > 0){
			if(quantitaAppunto != undefined && quantitaAppunto >= 0){
				$("#loader").show();			
				$.ajax({
					url: absolutePath+"/AggiornaQuantitaAppuntoAdmin",
					type: "POST",
					dataType: 'JSON',
					async: false,
					data: {
						"idAppunto": idAppunto,
						"quantitaAppunto": quantitaAppunto
					},
					success:function(msg){
						if(!msg.risultato){
							showAlert(1, msg.errore);
						}
						else{														
							$("#modalDettaglioOrdine").css("display", "none");
							getAppunti();
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
		var idAppunto = $(this).data("idAppunto");
		
		if(idAppunto != undefined && idAppunto > 0){		
				$("#loader").show();			
				$.ajax({
					url: absolutePath+"/GetFormModificaPrezzoAppuntoAdmin",
					type: "POST",
					dataType: 'JSON',
					async: false,
					data: {
						"idAppunto": idAppunto
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

	$(document).on('click', '#confirmAggiungiPrezzoAppunto', function(e){		
		var idAppunto = $("#idAppunto").val();
		var prezzoAppunto = $("#prezzoAppunto").val();				
		if(idAppunto != undefined && idAppunto > 0){
			if(prezzoAppunto != undefined && prezzoAppunto > 0){
				
				$("#loader").show();			
				$.ajax({
					url: absolutePath+"/AggiornaPrezzoAppuntoAdmin",
					type: "POST",
					dataType: 'JSON',
					async: false,
					data: {
						"idAppunto": idAppunto,
						"prezzoAppunto": prezzoAppunto
					},
					success:function(msg){
						if(!msg.risultato){
							showAlert(1, msg.errore);
						}
						else{														
							$("#modalDettaglioOrdine").css("display", "none");
							getAppunti();
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

$(document).on('click', '#idAppuntoDett', function(e){	
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
					url: absolutePath+"/GetAppuntiDett",
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
								$(location).href('prodotto_dettaglio.jsp');
								$("th").text("Appunti");
								$("#bodyMaterie").html(msg.contenuto+"" +
										"<br><tr><td>" +
										"<input type='button' id='prova' onclick='location.reload()' value='Torna alle Materie' />" +
										"</td></tr><br>");
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


var cod;
function setAppuntiDett(codice){
	cod = codice;
	return cod;
}
function getAppuntiDett(){
	return cod;
}

function getAppunti(){ 
	$("#loader").show();
	$.ajax({
		url: absolutePath+"/GetAppunti",
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
					$("#bodyMaterie").html("<tr><td colspan='10'>Nessun Appunto Presente</td></tr>");
				}
				
			}
		},
		error: function(msg){
			showAlert(1, "Impossibile Recuperare i dati.");
		}
	});
	
	$("#loader").hide();	
	
}