$(document).ready(function(){
	$('#richiesteTable').DataTable( {
        "order": [[ 0, "desc" ]],
        "language": {
			    "sEmptyTable":     "Nessun Contatto Presente",
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
	
	$(document).on('click', '.chiudiModalContatti', function(e){	
		$("#modalContatti").css("display", "none");
		getContatti();
		
	});			
	
	$(document).on('click', '.visualizzaRichiesta', function(e){		
		var id_richiesta = $(this).data("id_richiesta");
		
		if(id_richiesta != undefined && id_richiesta > 0){					
			$("#loader").show();			
			$.ajax({
				url: absolutePath+"/GetRichieste",
				type: "POST",
				dataType: 'JSON',
				async: false,
				data: {
					"id_richiesta": id_richiesta
				},
				success:function(msg){
					if(!msg.risultato){
						showAlert(1, msg.errore);
					}
					else{
						$("#modalContattiBody").html(msg.contenuto);
						$("#modalContatti").css("display", "block");
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
	
	
	
	$(document).on('click', '.eliminaRichiesta', function(e){		
		var id_richiesta = $(this).data("id_richiesta");
		
		if(id_richiesta != undefined && id_richiesta > 0){		
			if(confirm("Conferma la cancellazione della richiesta n. "+id_richiesta+"?")){
				$("#loader").show();			
				$.ajax({
					url: absolutePath+"/EliminaRichiesta",
					type: "POST",
					dataType: 'JSON',
					async: false,
					data: {
						"id_richiesta": id_richiesta
					},
					success:function(msg){
						if(!msg.risultato){
							showAlert(1, msg.errore);
						}
						else{
							showAlert(0, msg.contenuto);
							getRichieste();
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

function getRichieste(){ 
	$("#loader").show();

	$.ajax({
		url: absolutePath+"/GetRichieste",
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
					$("#bodyRichieste").html(msg.contenuto);
				}											
				else{
					$("#bodyRichieste").html("<tr><td colspan='9'>Nessuna Richiesta Presente</td></tr>");
				}
				
			}
		},
		error: function(msg){
			showAlert(1, "Impossibile Recuperare i dati.");
		}
	});
	
	$("#loader").hide();	
	
}
