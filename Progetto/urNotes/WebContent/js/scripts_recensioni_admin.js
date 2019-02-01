$(document).ready(function(){
	$('#recensioniTable').DataTable( {
        "order": [[ 0, "desc" ]],
        "language": {
			    "sEmptyTable":     "Nessuna Recensione Presente",
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
	
	$(document).on('click', '.visualizzaRecensione', function(e){		
		var id_recensione = $(this).data("id_recensione");
		
		if(id_recensione != undefined && id_recensione > 0){					
			$("#loader").show();			
			$.ajax({
				url: absolutePath+"/GetRecensioneAdmin",
				type: "POST",
				dataType: 'JSON',
				async: false,
				data: {
					"id_recensione": id_recensione
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
	
	
	
	$(document).on('click', '.eliminaRecensione', function(e){		
		var id_recensione = $(this).data("id_recensione");
		
		if(id_recensione != undefined && id_recensione > 0){		
			if(confirm("Conferma la cancellazione della recensione n. "+id_recensione+"?")){
				$("#loader").show();			
				$.ajax({
					url: absolutePath+"/EliminaRecensione",
					type: "POST",
					dataType: 'JSON',
					async: false,
					data: {
						"id_recensione": id_recensione
					},
					success:function(msg){
						if(!msg.risultato){
							showAlert(1, msg.errore);
						}
						else{
							showAlert(0, msg.contenuto);
							getRecensioneAdmin();
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

function getRecensioneAdmin(){ 
	$("#loader").show();

	$.ajax({
		url: absolutePath+"/GetRecensioneAdmin",
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
					$("#bodyRecensioni").html(msg.contenuto);
				}											
				else{
					$("#bodyRecensioni").html("<tr><td colspan='9'>Nessuna Recensione Presente</td></tr>");
				}
				
			}
		},
		error: function(msg){
			showAlert(1, "Impossibile Recuperare i dati.");
		}
	});
	
	$("#loader").hide();	
	
}
