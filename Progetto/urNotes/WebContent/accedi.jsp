<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon"  type="image/png" href="/urNotes/images/logo.png">
<link href="accedi.css" rel="stylesheet" type="text/css">
<title>urNotes | Accedi</title>
</head>

<body>

<div class="loginbox">


<form action="/urNotes/loginServlet" method="post">

<img src="images/menu_logo3.png" class="avatar">
 
<fieldset>

     <p>Username</p>
     <input id="username" required type="text" name="username" placeholder="Inserisci Username.." autocomplete="off"> 
     <br>   
     <br>
     <p>Password</p>
     <input id="password" required type="password" name="password" placeholder="Inserisci Password.." autocomplete="off"> 
     <br>   
     <br>
     <input type="submit" value="Accedi"/>
     <br>
     <br>
     <a href="index.jsp">Torna alla Home</a> 
     
</fieldset>
</form>
 
</div>


</body>
</html>
