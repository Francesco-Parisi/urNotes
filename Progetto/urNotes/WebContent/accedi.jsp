<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="connection.ConnessioneDB, java.sql.*"%>
<!DOCTYPE html>
<html lang = "it">
<head>
<%@ include file="/partials/head.jsp" %>		
<script src="<%=request.getContextPath()%>/js/scripts_accedi.js"></script>			
<title>urNotes | Accedi</title>
</head>

<body>
<div class="loginbox">


<form action="#" method="POST" id="formAccedi">
<img src="images/menu_logo3.png" class="avatar">
<fieldset>
     <p>Username</p>
     <input required type="text" id="username"  name="username" placeholder="Inserisci Username.." class="campoForm" /> 
     <br><br>
     <p>Password</p>
     <input required type="password" id="password" name="password" placeholder="Inserisci Password.." class="campoForm" /> 
     <br><br>
	 <input type="submit" id="submitForm" name="submitForm" class="campoForm submitForm" value="Accedi" />				
     <br><br>
     <a  class="link" href="index.jsp">Torna alla Home</a> 				      
</fieldset>
</form>
 
</div>

</body>
</html>
