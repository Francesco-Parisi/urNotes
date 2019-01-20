<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="model.CheckSession, javax.servlet.http.HttpSession, model.ConnessioneDB, java.sql.*" %>
<!DOCTYPE html>
<html lang = "it">
	<head>
		<% 	
			CheckSession ck = new CheckSession(1, request.getSession());	
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
		<script src="<%=request.getContextPath()%>/js/scripts_impostazioni_profilo_admin.js"></script>				
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
							
							sql = "SELECT email, nome, cognome, username "
								+ "FROM utenti "
								+ "WHERE attivo = 1 AND id_utente = "+id_utente+";";
							Statement stmt0 = connDB.getConn().createStatement();
							ResultSet result0 = stmt0.executeQuery(sql);								
							if(!result0.wasNull()) {	
								while(result0.next()) {
									email = result0.getString("email");
									nome = result0.getString("nome");
									cognome = result0.getString("cognome");
									username = result0.getString("username");
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
					<form action='#' method='POST' id='formImpostazioniAdmin'>											
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
							<legend>Username</legend>
							<input type='text' id='username' name='username' class='campoForm' value='<%=username %>' />
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
					<%=output %>							
			</div>
		</div>
		<%@ include file="/partials/footer.jsp" %>	
	</body>
</html>