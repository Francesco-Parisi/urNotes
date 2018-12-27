<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>
<%@ include file="/partials/head-accesso.jsp" %>			
<title>urNotes | Accedi</title>
</head>

<body>

<div class="loginbox">


<form action="#" method="POST">

<img src="images/menu_logo3.png" class="avatar">
 
<fieldset>

     <p>Username</p>
     <input id="username" required type="text" name="username" placeholder="Inserisci Username.." autocomplete="off"> 
     <br><br>
     <p>Password</p>
     <input id="password" required type="password" name="password" placeholder="Inserisci Password.." autocomplete="off"> 
     <br><br>
     <input type="submit" value="Accedi"/>
     <br><br>
     <a href="index.jsp">Torna alla Home</a> 
     
</fieldset>
</form>
 
</div>

</body>
</html>
