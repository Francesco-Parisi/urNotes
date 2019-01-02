<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang = "it">

<head>
<%@ include file="/partials/head.jsp" %>
<title>urNotes | Registrati</title>
</head>

<body>

<div class="loginbox-reg">
<img src="images/menu_logo3.png" class="avatar">

<form action="/Libro.it/regServlet" method="post"> 
<fieldset class="box">

                <p>Nome</p>
	            <input id="nome" type="text" name="nome" placeholder="Inserisci Nome..." required> 
	            <br>   	
			    <br>
			    <p>Cognome</p>
				<input id="cognome" type="text" name="cognome" placeholder="Inserisci Cognome..." required> 
				<br>
				<br>				
                <p>Email</p>
			    <input id="mail" type="text" name="mail" placeholder="Inserisci E-mail..." required> 
				<br>
				<br>	
                <p>Username</p>
				<input id="username" type="text" name="username" placeholder="Inserisci Username..." required> 
				<br>
				<br>
                <p>Password</p>
			    <input id="password" type="password" name="password" placeholder="Inserisci Password..." required> 
			    <br>
			    <br>
			    
			        <% String error=(String)request.getAttribute("error");
			      	if(error!=null &&! error.equals("")){
			      %>
			      	<p id="errorReg"><%=error %></p>	
			      <% 
			      	}
			      
			      %>
			      
<input  type="submit" value="Registrami"/>
<input type="reset" value="Reset"/>
     
<br><br>
     
<a href="index.jsp">Torna alla Home</a> 
</fieldset>
</form> 
  
</div>


</body>
</html>