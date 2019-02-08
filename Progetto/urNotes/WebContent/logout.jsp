<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% request.getSession().setAttribute("id_utente", 0); %>
<% request.getSession().invalidate(); %>

<!DOCTYPE html>
<html lang = "it">
	<head>
		<%@ include file="/partials/head.jsp" %>			
		<title>urNotes | Logout</title>		
	</head>

<body>
<%@ include file="/partials/header.jsp" %>		
	
	
<section class="container_logout">

<div class="logout">
<img src="/urNotes/images/exit.png" class="logout-img" alt="email">
<div class="logout_text">
<h3>Logout Effettuato con successo</h3>
</div>
</div>


</section>

<%@ include file="/partials/footer.jsp" %>	

</body>
</html>