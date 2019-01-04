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
<title>urNotes | Il mio Account</title>
</head>
<body>
<%@ include file="/partials/header.jsp" %>


<section class="contacts">

<a class="contact" href="<%=request.getContextPath()%>/_StudenteArea/infoStudente.jsp">
<img src="https://images.unsplash.com/photo-1479920252409-6e3d8e8d4866?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=7335ddddbdb2e401ce4f50507524d900&auto=format&fit=crop&w=750&q=80" class="contact-img" alt="email">
<div class="contact-text">
<h3>Studente</h3>
<p>Visualizza le tue info</p>
</div>
</a>


<a class="contact" href="<%=request.getContextPath()%>/_StudenteArea/indirizzoStudente.jsp">
<img src="https://images.unsplash.com/photo-1493952113579-dfa2cfd5398b?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=750&q=80" class="contact-img" alt="email">
<div class="contact-text">
<h3>Indirizzo</h3>
<p>Aggiungi o modifica l'indirizzo</p>
</div>
</a>


<a class="contact" href="<%=request.getContextPath()%>/_StudenteArea/ordiniStudente.jsp">
<img src="https://images.unsplash.com/photo-1512418490979-92798cec1380?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=750&q=80" class="contact-img" alt="email">
<div class="contact-text">
<h3>Ordini</h3>
<p>Visualizza i tuoi ordini</p>
</div>
</a>

</section>


<%@ include file="/partials/footer.jsp" %>	
</body>
</html>