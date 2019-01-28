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
		<script src="<%=request.getContextPath()%>/js/scripts_conferma_ordine.js"></script>
		<title>urNotes | Conferma Ordine</title>		
	</head>
	<body>
		<%@ include file="/partials/header.jsp" %>		
		<div id="content">
			<div id="content-content">

			<%
				String output = "";				
				String spedizione = "";
				String vettore = "";
				String metodoPagamento = "";
				String totali = "";
				String body = "";
		    	if(request.getSession() != null){
		    		Integer serial_id = (Integer) request.getSession().getAttribute("serial_id");		    		
		    		if(serial_id != null){
		    			String sql;
		    			Statement stmt;
		    			Statement stmt1;
		    			ResultSet result;
		    			ResultSet result1;
		    			Float prz;		    			
		    	        ConnessioneDB connDB = new ConnessioneDB();
		    			if(connDB.getConn() != null) {
			    				try {
				    				sql = "";
				    				stmt = null;
				    				result = null;		    				

			    					stmt = connDB.getConn().createStatement();
				    				sql = ""
											+ "SELECT * "
											+ "FROM ordini AS o "
											+ "WHERE attivo = 0 AND serial_id = "+serial_id+"; ";
			    					//System.out.println(sql);
			    					result = stmt.executeQuery(sql);				
			    					if(!result.wasNull()) {
			    						while(result.next()) {

						    				sql = "";
						    				stmt1 = null;
						    				result1 = null;	
						    				stmt1 = connDB.getConn().createStatement();
											sql = "SELECT i.nome, i.cognome, i.indirizzo, i.cap, i.citta, i.provincia, i.telefono, i.cellulare "
												+ "FROM indirizzi AS i "
												+ "WHERE i.id_indirizzo = "+result.getInt("id_indirizzo")+"; ";
											result1 = stmt1.executeQuery(sql);
					    					if(!result1.wasNull()) {
					    						while(result1.next()){
					    							spedizione = result1.getString("nome")+" "+result1.getString("cognome")+" <br/> "+result1.getString("indirizzo")+" <br/> "+result1.getInt("cap")+" "+result1.getString("citta")+" ("+result1.getString("provincia")+")";
					    						}
					    					}	
					    					
					    					
						    				sql = "";
						    				stmt1 = null;
						    				result1 = null;	
						    				stmt1 = connDB.getConn().createStatement();
											sql = "SELECT nome, descrizione, costo "
												+ "FROM ordini_vettori "
												+ "WHERE id_vettore = "+result.getInt("id_vettore")+"; ";
											result1 = stmt1.executeQuery(sql);
					    					if(!result1.wasNull()) {
					    						while(result1.next()){
					    							vettore = result1.getString("nome")+" (&euro;"+new SystemInformation().truncateDecimal(result1.getFloat("costo"), 2)+")<br/>"+result1.getString("descrizione")+"";
					    						}
					    					}						    					

					    					
						    				sql = "";
						    				stmt1 = null;
						    				result1 = null;	
						    				stmt1 = connDB.getConn().createStatement();
											sql = "SELECT nome, descrizione "
												+ "FROM ordini_metodi_pagamento "
												+ "WHERE id_metodo = "+result.getInt("id_metodo")+"; ";
											result1 = stmt1.executeQuery(sql);
					    					if(!result1.wasNull()) {
					    						while(result1.next()){
					    							metodoPagamento = result1.getString("nome")+"<br/>"+result1.getString("descrizione")+"";
					    						}
					    					}						    					
					    					
					    					totali += "<div class='info_totale'>";
					    					totali += "<p><b>Totale Documenti:</b> &euro;"+new SystemInformation().truncateDecimal(result.getFloat("totale_documenti"),2)+"</p>";
					    					totali += "<p><b>Totale Spedizione:</b> &euro;"+new SystemInformation().truncateDecimal(result.getFloat("totale_spedizione"), 2)+"</p>";
					    					totali += "<p><b>Totale Ordine:</b> &euro;"+new SystemInformation().truncateDecimal(result.getFloat("totale_ordine"), 2)+"</p>";
					    					totali += "</div>";

			    						}					
			    					}				 	    						

				    				sql = "";
				    				stmt = null;
				    				result = null;					    				
				    				stmt = connDB.getConn().createStatement();
				    				sql = ""
				    						+ "SELECT *, " 
											+ "(SELECT filename FROM documenti_immagini WHERE codice = od.codice AND is_default = 1 AND attivo = 1) AS filename "											
											+ "FROM ordini_documenti AS od "
											+ "WHERE od.attivo = 1 AND od.serial_id = "+serial_id+"; ";
			    					//System.out.println(sql);
			    					result = stmt.executeQuery(sql);				
			    					if(!result.wasNull()) {
			    						while(result.next()){
			    							String filename;
			    							if(result.getString("filename") != null){
			    								filename = new SystemInformation().getPathImmaginiDocumentoHTML()+result.getInt("codice")+"/"+result.getString("filename");												
			    							}
			    							else{
			    								filename = new SystemInformation().getPathImmaginiDocumentoDefault();												
			    							}	    
			    							
			    							body += "<tr>";							
				    							body += "<td><img class='showImmagineDocumento' src='"+filename+"' alt='"+filename+"' /></td>";
				    							body += "<td>"+result.getString("titolo")+"</td>";							
				    							body += "<td>"+result.getString("nome_materia")+"</td>";							
				    							body += "<td>"+result.getInt("quantita")+"</td>";
				    							body += "<td>";	    					
				    							body += "&euro; "+ new SystemInformation().truncateDecimal(result.getFloat("prezzo_totale"), 2);													
												body += "</td>";    								    							
											body += "</tr>";			    							
			    						}
			    					}
			    					
			    					connDB.getConn().close();
			    				}
			    				catch(Exception e) {
			    					body += "Errore esecuzione Query."+e.getMessage();
			    				}	    				    							    			 					    	        			    		
		    			}
			    		else {
			    			body = connDB.getError();
			    		}	    	

				    	%>
	                        <div id="container_appunti_admin">	
	                        <div id="content">
			                  <div id="content-content">
								<p class='adminTitoloPagina'>Concludi Ordine</p>
								
			        			<table id='documentiTable'>
			       					<thead class='adminHeadDataTable'>
			      						<tr>
			     							<th>Foto</th>
			     							<th>Titolo</th>
			     							<th>Materia</th>
											<th>Quantit&agrave;</th>
			        						<th>Prezzo</th>
			        					</tr>	
									</thead>
									<tbody id="bodyCarrello" class='adminBodyDataTable'>
										<%=body %>
									</tbody>
								</table>
								
								
								<div class="container_pagamento">
								<p class='adminTitoloPagina'>Info Ordine</p>
								
								<div class="info_pagamento">
									<p><b>Indirizzo di Spedizione</b></p>
									<p><%=spedizione %></p>									
								</div>
								
								<div class="info_pagamento">
									<p><b>Vettore</b></p>
									<p><%=vettore %></p>		
								</div>
								<div class="info_pagamento">
									<p><b>Metodo di Pagamento</b></p>
									<p><%=metodoPagamento %></p>		
								</div>
								
								<div class="info_pagamento">
								<%=totali %>								
								</div>
							</div>
								<button id='userButtonTerminaOrdine' data-href="<%=request.getContextPath()%>/_StudenteArea/ordiniStudente.jsp" class='userButtonCheckout'>Termina Ordine</button>			
					    	
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
			</div>
		</div>
		<%@ include file="/partials/footer.jsp" %>	
	</body>
</html>