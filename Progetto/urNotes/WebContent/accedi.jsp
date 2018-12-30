<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="model.ConnessioneDB, java.sql.*"%>
<!DOCTYPE html>
<html lang = "it">
	<head>
<%@ include file="/partials/head-accesso.jsp" %>			
<title>urNotes | Accedi</title>
</head>

<body>

<div class="loginbox">


<form action="#" method="POST" id="formAccedi">

<img src="images/menu_logo3.png" class="avatar">
 
<fieldset>

     <p>Username</p>
     <input id="Username" required type="text" name="Username" placeholder="Inserisci Username.." class="campoForm"> 
     <br><br>
     <p>Password</p>
     <input id="Password" required type="password" name="Password" placeholder="Inserisci Password.." class="campoForm"> 
     <br><br>
	<input type="submit" id="submitForm" name="submitForm" class="campoForm submitForm" value="Accedi" />				
     <br><br>
     <a href="index.jsp">Torna alla Home</a> 
     
				      
</fieldset>
</form>
 
</div>

</body>
</html>
