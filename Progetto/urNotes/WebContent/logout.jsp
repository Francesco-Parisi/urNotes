<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% request.getSession().setAttribute("id_utente", 0); %>
<% request.getSession().invalidate(); %>

<!DOCTYPE html>
<html lang = "it">
	<head>
		<%@ include file="/partials/head.jsp" %>			
		<title>Logout</title>		
	</head>
	<body>
	<%@ include file="/partials/header.jsp" %>		
	
		<div id="content">
			<div id="content-content">
				<p id="logout">Logout Effettuato con successo</p>
			<input type="button" class="logout_button" onclick="location.href='/urNotes/index.jsp'" value='Torna alla Home'>
			</div>
		</div>
		<%@ include file="/partials/footer.jsp" %>	
	</body>
</html>