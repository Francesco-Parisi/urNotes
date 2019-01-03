<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="model.ConnessioneDB, java.sql.*"%>
<!DOCTYPE html>
<html lang = "it">
	<head>
		<%@ include file="/partials/head.jsp" %>	
		<script src="<%=request.getContextPath()%>/js/scripts_registrati.js"></script>	
<title>urNotes | Registrati</title>
    </head>

<body>

			
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

				    			    			    				
					<input type="submit" id="submitForm" name="submitForm" class="campoForm submitForm" value="Registrati" />				
				    <input type="reset" value="Reset"/>
				
				    <br><br><br>
     
                    <a href="index.jsp">Torna alla Home</a> 
                    
                    </div>
				</form>
			</div>
	  </body>
</html>