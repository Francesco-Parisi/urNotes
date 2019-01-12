<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="model.ConnessioneDB,java.sql.*, model.SystemInformation, model.CheckSession, model.Documento"%>
<!DOCTYPE html>
<html lang = "it">
	<head>
		<% 	
			CheckSession ck = new CheckSession(0, request.getSession());	
			if(ck.getRedirect()){
				String path = request.getContextPath()+ck.getUrlRedirect();
				%>
					<script>
						window.location.href = '<%=path%>';
					</script>
				<%	
			} 
		%>	
		<%@ include file="/partials/head.jsp" %>			
		<script src="<%=request.getContextPath()%>/js/scripts_checkout.js"></script>
		<title>Checkout</title>		
	</head>
	<body>
		<%@ include file="/partials/header.jsp" %>		
		<div id="content">
			<div id="content-content">

			<%
				String output = "";				
				String fatturazione = "";
				String spedizione = "";
				String vettore = "";
				String metodoPagamento = "";
				String body = "";
		    	if(request.getSession() != null){
		    		Integer idUtente = (Integer) request.getSession().getAttribute("id_utente");    		
		    		Carrello cart = (Carrello) request.getSession().getAttribute("carrello");
		    		if(idUtente != null && cart != null){
		    			String sql;
		    			Statement stmt;
		    			ResultSet result;
		    			Integer Codice;
		    			Integer quantitaDocumento;
		    			Float prz;
		    			
		    	        ConnessioneDB connDB = new ConnessioneDB();
		    			if(connDB.getConn() != null) {
			    			for(Documento documento: cart.getDocumenti()) { //Per ogni prodotto del carrello
			    				try {
				    				sql = "";
				    				stmt = null;
				    				result = null;		    				
				    				Codice = documento.getCodice();
				    				quantitaDocumento = documento.getQuantita();
			    					stmt = connDB.getConn().createStatement();
				    				sql = ""
											+ "SELECT d.titolo, d.prezzo, "
											+ "FROM documenti  AS d "
											+ "WHERE d.flag = 1 AND d.codice = "+Codice+"; ";
			    					//System.out.println(sql);
			    					result = stmt.executeQuery(sql);				
			    					if(!result.wasNull()) {
			    						while(result.next()) {
			    							String filename;
			    							if(result.getString("filename") != null){
			    								filename = new SystemInformation().getPathImmaginiDocumentoHTML()+Codice+"/"+result.getString("filename");												
			    							}
			    							else{
			    								filename = new SystemInformation().getPathImmaginiDocumentoDefault();												
			    							}	    
			    							
			    							body += "<tr>";							
			    							body += "<td>"+Codice+"</td>";							
			    							body += "<td><img class='showImmagineProdotto' src='"+filename+"' alt='"+filename+"' /></td>";
			    							body += "<td>"+result.getString("titolo")+"</td>";							
			    							body += "<td>"+result.getFloat("prezzo")+"</td>";							
			    							body += "</tr>";
			    						}					
			    					}				 	    						    		
			    				}
			    				catch(Exception e) {
			    					body += "Errore esecuzione Query."+e.getMessage();
			    				}	    				    				
			    			}	    	
			    			    						    						    		
			    			String indirizzi = "";
		    				try {
			    				sql = "";
			    				stmt = null;
			    				result = null;			    				
		    					stmt = connDB.getConn().createStatement();		    					
		    					sql = ""
										+ "SELECT i.id_indirizzo, i.nome, i.cognome, i.indirizzo, i.note,i.cap,i.citta,i.provincia, i.telefono, i.cellulare "
										+ "FROM indirizzi AS i "
										+ "WHERE i.attivo = 1 AND i.id_utente = "+idUtente+" "
										+ "ORDER BY i.id_indirizzo DESC;";										
		    					result = stmt.executeQuery(sql);				
		    					if(!result.wasNull()) {
		    						int rowCount = result.last() ? result.getRow() : 0;
		    						if(rowCount > 0) {
		    							indirizzi += "<option value='0'>Selezionare un Indirizzo</option>";
		    							result.beforeFirst();
		    							while(result.next()) {
		    								indirizzi += "<option value='"+result.getInt("id_indirizzo")+"'>"+result.getString("nome")+" "+result.getString("cognome")+" - "+result.getString("indirizzo")+" - "+result.getString("cap")+" "+result.getString("citta")+" ("+result.getString("provincia")+")</option>";
		    							}												
		    						}
		    						else {
		    							indirizzi += "<option value='0'>Nessun Indirizzo Esistente</option>";
		    						}
		    					}					    					
		    				}
		    				catch(Exception e) {
		    					indirizzi = "Errore esecuzione Query";
		    				}

			    			String vettori = "";
		    				try {
			    				sql = "";
			    				stmt = null;
			    				result = null;			    				
		    					stmt = connDB.getConn().createStatement();		    					
		    					sql = ""
		    							+ "SELECT id_vettore, nome, descrizione, costo, contrassegno "
		    							+ "FROM ordini_vettori "
		    							+ "WHERE attivo = 1; ";
		    					result = stmt.executeQuery(sql);				
		    					if(!result.wasNull()) {
		    						int rowCount = result.last() ? result.getRow() : 0;
		    						if(rowCount > 0) {
		    							vettori += "<option value='0'>Selezionare un Vettore</option>";
		    							result.beforeFirst();
		    							while(result.next()) {
		    								Integer contrassegno = result.getInt("contrassegno");
		    								vettori += "<option value='"+result.getInt("id_vettore")+"'>"+result.getString("nome")+" - Costo: &euro;"+new SystemInformation().truncateDecimal(result.getFloat("costo"), 2)+" (Contrassegno: "+((contrassegno == 1) ? "Si" : "No")+")</option>";
		    							}												
		    						}
		    						else {
		    							vettori += "<option value='0'>Nessun Vettore Esistente</option>";
		    						}
		    					}			
		    				}
		    				catch(Exception e) {
		    					vettori = "Errore esecuzione Query";
		    				}		
		    				
			    			String metodiPagamento = "";
		    				try {
			    				sql = "";
			    				stmt = null;
			    				result = null;			    				
		    					stmt = connDB.getConn().createStatement();		    					
		    					sql = ""
		    							+ "SELECT id_metodo, nome, descrizione "
		    							+ "FROM ordini_metodi_pagamento "
		    							+ "WHERE attivo = 1; ";
		    					result = stmt.executeQuery(sql);				
		    					if(!result.wasNull()) {
		    						int rowCount = result.last() ? result.getRow() : 0;
		    						if(rowCount > 0) {
		    							metodiPagamento += "<option value='0'>Selezionare un Metodo di Pagamento</option>";
		    							result.beforeFirst();
		    							while(result.next()) {
		    								metodiPagamento += "<option value='"+result.getInt("id_metodo")+"'>"+result.getString("nome")+"</option>";
		    							}												
		    						}
		    						else {
		    							metodiPagamento += "<option value='0'>Nessun Metodo di Pagamento Esistente</option>";
		    						}
		    					}					    					
		    				}
		    				catch(Exception e) {
		    					metodiPagamento = "Errore esecuzione Query"+e.getMessage();
		    				}					    				
		    				
			    	        fatturazione += "<select id='fatturazioneCheckout' class='fatturazione checkoutFormField' name='fatturazione'>";
			    	        fatturazione += indirizzi;
			    	        fatturazione += "</select>";	        	        
			    			
			    	        spedizione += "<select id='spedizioneCheckout' class='spedizione checkoutFormField' name='spedizione'>";
			    	        spedizione += indirizzi;
			    	        spedizione += "</select>";	       
		    				
			    	        vettore += "<select id='vettoreCheckout' class='vettore checkoutFormField' name='vettore'>";
			    	        vettore += vettori;
			    	        vettore += "</select>";	        	        
			    			
			    	        metodoPagamento += "<select id='metodoPagamentoCheckout' class='metodoPagamento checkoutFormField' name='metodoPagamento'>";
			    	        metodoPagamento += metodiPagamento;
			    	        metodoPagamento += "</select>";	       
			    	        
			    	        
			    			try {
								connDB.getConn().close();
							} catch (SQLException e) {
								body = connDB.getError();
							}						    	        
			    			
		    			}
			    		else {
			    			body = connDB.getError();
			    		}	    	

				    	%>
					    	<div id="checkoutPage">
								<p class='adminTitoloPagina'>Concludi Ordine</p>
								
			        			<table id='checkoutTable'>
			       					<thead class='adminHeadDataTable'>
			      						<tr>
			     							<th>ID</th>
			     							<th>Foto</th>
			     							<th>Nome</th>
											<th>Quantit&agrave;</th>
			        						<th>Prezzo</th>
			        					</tr>	
									</thead>
									<tbody id="bodyCarrello" class='adminBodyDataTable'>
										<%=body %>
									</tbody>
								</table>
								
								<div class="left">
									<p>Indirizzo di Fatturazione</p>
									<%=fatturazione %>
								</div>
								<div class="right">
									<p>Indirizzo di Spedizione</p>
									<%=spedizione %>
								</div>
								
								<div class="left">
									<p>Vettore</p>
									<%=vettore %>
								</div>
								<div class="right">
									<p>Metodo di Pagamento</p>
									<%=metodoPagamento %>
								</div>

								<button id='userButtonConcludiOrdine' data-href="<%=request.getContextPath()%>/_userArea/conferma_ordine.jsp" class='userButtonCheckout'>Avanti</button>			
					    	</div>
				    	<%	    					    		
		    		}
		    		else{
		    			output = "Errore Parametri";
		    		}		    			    
		    	}
				else{
					output = "Errore Parametri";
				}	    
			%>
			<%=output %>
			</div>
		</div>
		<%@ include file="/partials/footer.jsp" %>	
	</body>
</html>