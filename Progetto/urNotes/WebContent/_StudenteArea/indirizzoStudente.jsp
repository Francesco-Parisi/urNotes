<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="model.CheckSession, javax.servlet.http.HttpSession, model.ConnessioneDB, java.sql.*" %>
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
<title>urNotes | Indirizzo</title>
</head>
<body>
<%@ include file="/partials/header.jsp" %>	
<div class="loginbox-reg">
<div class="box">
		<div id="content">
			<div id="content-content">
				<% String username = "";
					if(request.getSession().getAttribute("nome_utente") != null){
						username = (String)request.getSession().getAttribute("nome_utente");
					}
					
					String output = "";
					
					ConnessioneDB connDB = new ConnessioneDB();
					if(connDB.getConn() != null) {
						try {
							String sql = "";
							
							sql = "SELECT id_utente "
								+ "FROM utenti "
								+ "WHERE username LIKE '"+username+"';";
							Statement stmt = connDB.getConn().createStatement();
							ResultSet result = stmt.executeQuery(sql);								
														
							if(!result.wasNull()) {										
								int rowCount = result.last() ? result.getRow() : 0;
								if(rowCount > 0) {											
									result.beforeFirst();
									
									while(result.next()) {	
										
											output += "<input type = 'hidden' id = 'idUtente' value = "+result.getInt("id_utente")+" />";
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
				
			
			%>
			<form action="#" method="POST" id="formIndirizzo addIndirizzo">					                
              		 <fieldset>					        
				        <p>Nome</p>
				        <input required type="text" id="nome" name="nome" class="campoForm" />
				     </fieldset>					

				    <fieldset>
				        <p>Cognome</p>
				        <input required type="text" id="cognome" name="cognome" class="campoForm" />
				    </fieldset>					

				    <fieldset>
				        <p>indirizzo</p>
				        <input required type="text" id="indirizzo" name="indirizzo" class="campoForm" />
				    </fieldset>	
                    <fieldset>
				        <p>CAP</p>
				        <input required type="text" id="cap" name="cap" class="campoForm" />
				    </fieldset>					

				    <fieldset>
				        <p>Citt&agrave;</p>
				        <input required type="text" id="citta" name="citta" class="campoForm" />
				    </fieldset>					

				  <fieldset>
				        <p>Provincia</p>
				        <input required type="text" id="provincia" name="provincia" class="campoForm" />
				  </fieldset>
				  <fieldset>
				        <p>Telefono</p>
				        <input required type="text" id="telefono" name="telefono" class="campoForm" />
				  </fieldset>
				  <fieldset>
				        <p>Cellulare</p>
				        <input required type="text" id="cellulare" name="cellulare" class="campoForm" />
				  </fieldset>
				 <%=output %>
				  					

				    			    			    				
					<input type="submit" id="buttonAggiungiIndirizzo" name="buttonAggiungiIndirizzo" class="campoForm submitForm" value="Aggiungi Indirizzo" />				
				    <input type="reset" value="Reset"/>
				
				    <br><br><br>
     
                    <a href="index.jsp">Torna alla Home</a> 
				</form>
			</div>
		</div>
	</div>
	</div>
	</body>
<%@ include file="/partials/footer.jsp" %>	
</html>