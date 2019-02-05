$(document).ready(function(){
	$('#richiestaTable').DataTable( {
        "order": [[ 0, "desc" ]],
        "language": {
			    "sEmptyTable":     "Nessuna Richiesta Presente",
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
	
	$(document).on('click', '#buttonAggiungiRichiesta', function(e){
		
		if($("#formAggiungiRichiesta").css("display") == "block"){
			$("#formAggiungiRichiesta").html("");
			$("#formAggiungiRichiesta").css("display", "none");
		}
		else{
			$("#loader").show();
			
			$.ajax({
				url: absolutePath+"/GetFormAggiungiRichiesta",
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
						$("#formAggiungiRichiesta").html(msg.contenuto);
						$("#formAggiungiRichiesta").css("display", "block");
					}
				},
				error: function(msg){
					showAlert(1, "Impossibile Recuperare i dati.");
				}
			});
			
			$("#loader").hide();			
		}
	});	
	
	
	$(document).on('click', '.aggiungiRichiesta', function(e){		
		var id_richiesta = $(this).data("id_richiesta");
		
		if(id_richiesta != undefined && id_richiesta > 0){		
			window.location.href = 'file_richiesta.jsp?id_richiesta='+id_richiesta;
		}
		else{			
			showAlert(1, "Errore Parametri.");
		}		
	});		
	
	
	$(document).on('click', '#confirmAggiungiRichiesta', function(e){		
		var titolo = $("#titolo").val();
		var pagine = $("#pagine").val();
		var universita = $("#universita").val();
		var nome_materia = $("#nome_materia").val();
		var descrizione = $("#descrizione").val();
		var tipo = $("#tipo").val();
		
		
		if(titolo != undefined && titolo.length > 5 && pagine != undefined && pagine > 0 && universita != undefined && universita.length > 3 && nome_materia != undefined && nome_materia.length > 1 && descrizione != undefined && descrizione.length > 5 && tipo != undefined && tipo > 0 ){		
			$("#loader").show();			
			$.ajax({
				url: absolutePath+"/AggiungiRichiesta",
				type: "POST",
				dataType: 'JSON',
				async: false,
				data: {
					"titolo": titolo,
					"pagine": pagine,
					"universita": universita,
					"nome_materia": nome_materia,
					"descrizione": descrizione,
					"tipo": tipo,
				},
				success:function(msg){
					if(!msg.risultato){
						showAlert(1, msg.errore);
					}
					else{
						showAlert(0, msg.contenuto);
						$("#formAggiungiRichiesta").html("");
						$("#formAggiungiRichiesta").css("display", "none");
						getRichiesta();
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
		
	
	$(document).on('click', '.chiudiModalDettaglioOrdine', function(e){
		$("#modalDettaglioOrdine").css("display", "none");
	});			
});



function getRichiesta(){ 
	$("#loader").show();
	$.ajax({
		url: absolutePath+"/GetRichiesta",
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
					$("#bodyRichiesta").html(msg.contenuto);
				}											
				else{
					$("#bodyRichiesta").html("<tr><td colspan='10'>Nessuna Richiesta Presente</td></tr>");
				}
				
			}
		},
		error: function(msg){
			showAlert(1, "Impossibile Recuperare i dati.");
		}
	});
	
	$("#loader").hide();	
	
}