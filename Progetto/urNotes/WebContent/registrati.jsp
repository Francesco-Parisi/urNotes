<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="model.ConnessioneDB, java.sql.*"%>
<!DOCTYPE html>
<html lang = "it">
	<head>
		<%@ include file="/partials/head.jsp" %>	
		<script src="<%=request.getContextPath()%>/js/scripts_registrati.js"></script>	
<title>urNotes | Registrati</title>
    </head>

<body>
<div id="content">
			<div id="content-content">
				<%
					String output = "";
					String titolo = "";
					int tipoUtente = 0;
					if(request.getSession().getAttribute("tipo_utente") != null){
						tipoUtente = (int)request.getSession().getAttribute("tipo_utente");
					}
					
					if(tipoUtente == 1){
						titolo = "Compila i campi seguenti per registrare un nuovo Admin";
						output = "<input type='hidden' id='tipoUtente' name='tipoUtente' class='campoForm' value='1' />";							
					}
					else{
						titolo = "Compila i campi seguenti per registrarti al nostro e-commerce";
						output = "<input type='hidden' id='tipoUtente' name='tipoUtente' class='campoForm' value='2' />";							
					}					
				%>
			
<div class="loginbox-reg">
<img src="images/menu_logo3.png" class="avatar">

<form action="#" method="POST" id="formRegistrati">					
                    
<div class="box">
              		 <fieldset>					        
				        <p>Username</p>
				        <input type="text" id="username" name="username" class="campoForm" />
				     </fieldset>					

				    <fieldset>
				        <p>Nome</p>
				        <input type="text" id="nome" name="nome" class="campoForm" />
				    </fieldset>					

				    <fieldset>
				        <p>Cognome</p>
				        <input type="text" id="cognome" name="cognome" class="campoForm" />
				    </fieldset>	
                    <fieldset>
				        <p>Email</p>
				        <input type="text" id="email" name="email" class="campoForm" />
				    </fieldset>					

				    <fieldset>
				        <p>Password</p>
				        <input type="password" id="password" name="password" class="campoForm" />
				    </fieldset>					

				  <fieldset>
				        <p>Conferma Password</p>
				        <input type="password" id="confermaPassword" name="confermaPassword" class="campoForm" />
				  </fieldset>					

				    <%=output %>	

				    			    			    				
					<input type="submit" id="submitForm" name="submitForm" class="campoForm submitForm" value="Registrati" />				
				    <input type="reset" value="Reset"/>
				
				<br><br><br>
     
<a href="index.jsp">Torna alla Home</a> 
</div>
				</form>
			</div>
		</div>
	</div>
	</body>
</html>