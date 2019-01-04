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
		var categoriaAppunto = $("#categoriaAppunto").val();
		var nomeAppunto = $("#nomeAppunto").val();
		var descrizioneAppunto = $("#descrizioneAppunto").val();
		var descrizioneAbbreviataAppunto = $("#descrizioneAbbreviataAppunto").val();
		var quantitaAppunto = $("#quantitaAppunto").val();
		var unitaAppunto = $("#unitaAppunto").val();
		var prezzoAppunto = $("#prezzoAppunto").val();
		var aliquotaAppunto = $("#aliquotaAppunto").val();
		
		
		if(categoriaAppunto != undefined && categoriaAppunto > 0 && nomeAppunto != undefined && nomeAppunto.length > 1 && descrizioneAppunto != undefined && descrizioneAppunto.length > 1 && descrizioneAbbreviataAppunto != undefined && quantitaAppunto != undefined && quantitaAppunto >= 0 && unitaAppunto != undefined && unitaAppunto > 0 && prezzoAppunto != undefined && prezzoAppunto > 0 && aliquotaAppunto != undefined && aliquotaAppunto > 0){		
			$("#loader").show();			
			$.ajax({
				url: absolutePath+"/AggiungiAppunto",
				type: "POST",
				dataType: 'JSON',
				async: false,
				data: {
					"categoriaAppunto": categoriaAppunto,
					"nomeAppunto": nomeProdotto,
					"descrizioneAppunto": descrizioneProdotto,
					"descrizioneAbbreviataAppunto": descrizioneAbbreviataAppunto,
					"quantitaAppunto": quantitaAppunto,
					"unitaAppunto": unitaAppunto,
					"prezzoAppunto": prezzoAppunto,
					"aliquotaAppunto": aliquotaAppunto
				},
				success:function(msg){
					if(!msg.risultato){
						showAlert(1, msg.errore);
					}
					else{
						showAlert(0, msg.contenuto);
						$("#formAggiungiAppunto").html("");
						$("#formAggiungiAppunto").css("display", "none");
						getProdotti();
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
							getProdotti();
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
					$("#bodyAppunti").html(msg.contenuto);
				}											
				else{
					$("#bodyAppunti").html("<tr><td colspan='10'>Nessun Appunto Presente</td></tr>");
				}
				
			}
		},
		error: function(msg){
			showAlert(1, "Impossibile Recuperare i dati.");
		}
	});
	
	$("#loader").hide();	
	
}
