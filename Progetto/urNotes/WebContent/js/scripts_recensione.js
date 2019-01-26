$(document).ready(function(){
	$('#recensioneTable').DataTable( {
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
	
	
$(document).on('click', '#buttonAggiungiRecensione', function(e){
		
		if($("#formAggiungiRecensione").css("display") == "block"){
			$("#formAggiungiRecensione").html("");
			$("#formAggiungiRecensione").css("display", "none");
		}
		else{
			$("#loader").show();
			
			$.ajax({
				url: absolutePath+"/GetFormAggiungiRecensione",
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
						$("#formAggiungiRecensione").html(msg.contenuto);
						$("#formAggiungiRecensione").css("display", "block");
					}
				},
				error: function(msg){
					showAlert(1, "Impossibile Recuperare i dati.");
				}
			});
			
			$("#loader").hide();			
		}
	});	
	
	$(document).on('click', '#confirmAggiungiRecensione', function(e){		
		var descrizione = $("#descrizione").val();
	
		
		
		if(descrizione != undefined && descrizione.length > 1 ){		
			$("#loader").show();			
			$.ajax({
				url: absolutePath+"/AggiungiRecensione",
				type: "POST",
				dataType: 'JSON',
				async: false,
				data: {
					"descrizione": descrizione
				
				},
				success:function(msg){
					if(!msg.risultato){
						showAlert(1, msg.errore);
					}
					else{
						showAlert(0, msg.contenuto);
						$("#formAggiungiRecensione").html("");
						$("#formAggiungiRecensione").css("display", "none");
						getRecensione();
					}
				},
				error: function(msg){
					showAlert(1, "Impossibile Recuperare i idati.");
				}
			});
			
			$("#loader").hide();
		}
		else{			
			showAlert(1, "Controllare di aver compilato correttamente tutti i campi.");
		}		
	});	
		
});


function getRecensione(){ 
	$("#loader").show();
	$.ajax({
		url: absolutePath+"/GetRecensione",
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
					$("#bodyRecensione").html(msg.contenuto);
				}											
				else{
					$("#bodyRecensione").html("<tr><td colspan='10'>Nessuna Recensione Presente</td></tr>");
				}
				
			}
		},
		error: function(msg){
			showAlert(1, "Impossibile Recuperare i dati.");
		}
	});
	
	$("#loader").hide();	
	
}