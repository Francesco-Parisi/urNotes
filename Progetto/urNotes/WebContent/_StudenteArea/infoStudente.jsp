<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="model.CheckSession, javax.servlet.http.HttpSession, connection.ConnessioneDB, java.sql.*" %>

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
		<script src="<%=request.getContextPath()%>/js/scripts_impostazioni_profilo_user.js"></script>				
		<title>urNotes | Info Personali</title>		
	</head>
	<body>
		<%@ include file="/partials/header.jsp" %>				
		<div id="content">
			<div id="content-content">

					<%	
						int id_utente = 0;
						if(request.getSession().getAttribute("id_utente") != null){
							id_utente = (int)request.getSession().getAttribute("id_utente");
						}
						
						String output = "";
						String email = "";
						String nome = "";
						String cognome = "";
						String username = "";						
						if(id_utente > 0){				        	
				        	ConnessioneDB connDB = new ConnessioneDB();
							if(connDB.getConn() != null) {
								try {
									String sql = "";
									
									sql = "SELECT email, nome, cognome "
											+ "FROM utenti "
											+ "WHERE attivo = 1 AND id_utente = "+id_utente+";";
										Statement stmt0 = connDB.getConn().createStatement();
										ResultSet result0 = stmt0.executeQuery(sql);								
										if(!result0.wasNull()) {	
											while(result0.next()) {
												email = result0.getString("email");
												nome = result0.getString("nome");
												cognome = result0.getString("cognome");
										}
									}									
									
									Statement stmt = connDB.getConn().createStatement();
									sql = ""
										+ "SELECT i.nome, i.cognome, i.id_indirizzo, i.indirizzo, i.cap , i.citta, i.provincia, i.telefono, i.cellulare "
										+ "FROM indirizzi AS i "
										+ "WHERE i.attivo = 1 AND i.id_utente = "+id_utente+" "
										+ "ORDER BY i.id_indirizzo DESC;";										
									ResultSet result = stmt.executeQuery(sql);								
									if(!result.wasNull()) {										
										int rowCount = result.last() ? result.getRow() : 0;
										if(rowCount > 0) {											
											result.beforeFirst();
											
											while(result.next()) {					
												output += "<form action='#' method='POST' class='formIndirizzo'>";
													output += "<i class='fas fa-times eliminaIndirizzo' data-id_indirizzo='"+result.getString("id_indirizzo")+"'></i>";
													output += "<p class='nomeCognome'>";
													output += result.getString("nome");
													output += " ";
													output += result.getString("cognome");
													output += "</p>";
													output += "<p class='indirizzo'>";
													output += result.getString("indirizzo");
													output += "</p>";
													output += "<p class='capCittaProvincia'>";
													output += result.getString("cap")+" - "+result.getString("citta")+" ("+result.getString("provincia")+")";
													output += "</p>";
													output += "<p class='telefono'><strong>Tel</strong>: ";											
													output += result.getString("telefono")+"";
													output += "</p>";												
													output += "<p class='cellulare'><strong>Cell:</strong> ";											
													output += result.getString("cellulare")+"";
												output += "</form>";
											}											
										}
									}									
									connDB.getConn().close();
								}
								catch(Exception e) {
									output = e.getMessage();
								}	
							}
							else {
								output = connDB.getError();
							}				        				        				        
						}
						else{
							output = "Impossibile recuperare i dettagli dell'utenza. Errore Parametri";							
						}
					%>			


				<p class="admin_settings">Ciao <%=request.getSession().getAttribute("nome") %> <%=request.getSession().getAttribute("cognome") %>, qui puoi cambiare le tue credenziali. <br/></p>
					<form action='#' method='POST' id='formImpostazioniUser'>											
						<fieldset>
							<legend>Email</legend>
							<input type='text' class='campoForm' value='<%=email %>' readonly />
						</fieldset>
						<fieldset>
							<legend>Nome</legend>
							<input type='text' id='nome' name='nome' class='campoForm' value='<%=nome %>' />
						</fieldset>
						<fieldset>
							<legend>Cognome</legend>
							<input type='text' id='cognome' name='cognome' class='campoForm' value='<%=cognome %>' />
						</fieldset>
						<fieldset>
							<legend>Password</legend>
							<input type='password' id='password' name='password' class='campoForm' value='' />
						</fieldset>

						<fieldset>
							<legend>Ripeti Password</legend>
							<input type='password' id='ripetiPasswordUtente' name='ripetiPasswordUtente' class='campoForm' value='' />
						</fieldset>
						<input type='hidden' id='id_utente' value='<%=id_utente %>' />
						<input type='submit' id='submitForm' name='submitForm' class='submitForm' value='Salva' />
					</form>	
						
					<p class="admin_settings">Aggiungi o elimina il tuo indirizzo. <br/></p>						
						
					<div id="formAggiungiIndirizzo" class="userFormAggiungi" style="display: none;">
						
					</div>
					
					<div class="container_indirizzo">
					<form action="#" method="POST" class="formIndirizzo addIndirizzo">
						<i id="buttonAggiungiIndirizzo" class="fas fa-plus aggiungiIndirizzo" data-idindirizzo="5"></i>
					</form>	
					</div>									
					<%= output %>																        				      
			</div>
		</div>
		<%@ include file="/partials/footer.jsp" %>	
	</body>
</html>