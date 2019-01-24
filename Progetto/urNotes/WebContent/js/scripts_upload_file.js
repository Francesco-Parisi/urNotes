$(document).ready(function(){	
	$('#fileTable').DataTable( {
        "order": [[ 0, "desc" ]],
        "language": {
			    "sEmptyTable":     "Nessun File Presente",
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
	
	$(document).on('click', '#buttonAggiungiFile', function(e){
		
		if($("#formAggiungiFile").css("display") == "block"){
			$("#formAggiungiFile").html("");
			$("#formAggiungiFile").css("display", "none");
		}
		else{
			$("#loader").show();
			
			$.ajax({
				url: absolutePath+"/GetFormAggiungiFile",
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
						$("#formAggiungiFile").html(msg.contenuto);
						$("#formAggiungiFile").css("display", "block");
					}
				},
				error: function(msg){
					showAlert(1, "Impossibile Recuperare i dati.");
				}
			});
			
			$("#loader").hide();			
		}
	});	
	
	$(document).on('click', '#confirmAggiungiFile', function(e){		
		var filenameFileRichiesta = $("#filenameFileRichiesta").val();
		var id_richiesta = $("#idFileRichiesta").val();
		if(id_richiesta != undefined && id_richiesta > 0){
			if(filenameFileRichiesta != undefined && filenameFileRichiesta.length > 5){
				
				$("#loader").show();			
				$.ajax({
					url: absolutePath+"/AggiungiFile",
					type: "POST",
					dataType: 'JSON',
					async: false,
					data: {
						"filenameFileRichiesta": filenameFileRichiesta,
						"id_richiesta": id_richiesta		
					},
					success:function(msg){
						if(!msg.risultato){
							showAlert(1, msg.errore);
						}
						else{
							showAlert(0, msg.contenuto);
							$("#formAggiungiFile").html("");
							$("#formAggiungiFile").css("display", "none");
							getFile();
						}
					},
					error: function(msg){
						showAlert(1, "Impossibile Recuperare i dati.");
					}
				});
				
				$("#loader").hide();
			}
			else{
				showAlert(1, "Controllare di aver compilato correttamente tutti i campi.",filenameFileRichiesta.length);
			}					
		}
		else{
			showAlert(1, "Errore Parametri.");
		}
	});	
		
	
	$(document).on('click', '.eliminaFile', function(e){		
		var idFile = $(this).data("id_file");
		
		if(idFile != undefined && idFile > 0){		
			if(confirm("Conferma la cancellazione del file selezionato?")){
				$("#loader").show();			
				$.ajax({
					url: absolutePath+"/EliminaFile",
					type: "POST",
					dataType: 'JSON',
					async: false,
					data: {
						"idFile": idFile
					},
					success:function(msg){
						if(!msg.risultato){
							showAlert(1, msg.errore);
						}
						else{
							showAlert(0, msg.contenuto);
							getFile();
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

function getFile(){
	var id_richiesta = $("#idFileRichiesta").val();
	if(id_richiesta != undefined && id_richiesta > 0){
		$("#loader").show();
		
		$.ajax({
			url: absolutePath+"/GetFile",
			type: "POST",
			dataType: 'JSON',
			async: false,
			data: {
				"id_richiesta": id_richiesta,
				"richiesta": 1
			},
			success:function(msg){
				if(!msg.risultato){
					showAlert(1, msg.errore);
				}
				else{				
					if(msg.contenuto.length > 0){
						$("#bodyFile").html(msg.contenuto);
					}											
					else{
						$("#bodyFile").html("<tr><td colspan='3'>Nessun File Presente</td></tr>");
					}
					
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
	
}


function createDropzone(){	
	var id_richiesta = $("#idFileRichiesta").val();
	if(id_richiesta != undefined && id_richiesta > 0){
		$(".dropzoneFileRichiesta").dropzone({
			  maxFiles: 1,
			  acceptedFiles: ".docx,.pdf",
			  accept: function(file, done){					    
			    done();
			  },
			  init: function() {		
			      this.on("maxfilesexceeded", function(file){
				  	  showAlert(1, "Allegare al massimo un file");		    	  
			      });
			    					    
				  this.on("success", function(file, response) {
					  var msg = jQuery.parseJSON(response);
				  	  if(msg.risultato){
				  		alert(id_richiesta);
				  		  $("#filenameFileRichiesta").val(msg.contenuto);
				  		  
				  	  }    
				  	  else{			  		  			  		 
				  		showAlert(1, msg.errore);
				  	  }	            		            	
				  });	        
				  
				  this.on("sending", function(file, xhr, formData) {
			        formData.append("id_richiesta", id_richiesta);
			      });				  
			  }		  						
		});		
	}
	else{
		showAlert(1, "Errore Parametri.");
	}		
}