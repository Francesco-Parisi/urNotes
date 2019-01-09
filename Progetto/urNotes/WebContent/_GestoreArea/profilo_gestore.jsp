<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="model.CheckSession, javax.servlet.http.HttpSession, model.ConnessioneDB, java.sql.*" %>
<!DOCTYPE html>
<html lang = "it">
	<head>
		<% 	
			CheckSession ck = new CheckSession(1, request.getSession());	
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
<title>urNotes | Gestore</title>
</head>
<body>
<%@ include file="/partials/header.jsp" %>

<section class="contacts">

<a class="contact" href="<%=request.getContextPath()%>/_GestoreArea/infoGestore.jsp">
<img src="https://images.unsplash.com/photo-1479920252409-6e3d8e8d4866?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=7335ddddbdb2e401ce4f50507524d900&auto=format&fit=crop&w=750&q=80" class="contact-img" alt="email">
<div class="contact-text">
<h3>Gestore</h3>
<p>Visualizza le tue info</p>
</div>
</a>


<a class="contact" href="<%=request.getContextPath()%>/_GestoreArea/richieste.jsp">
<img src="https://images.unsplash.com/photo-1510070112810-d4e9a46d9e91?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=750&q=80" class="contact-img" alt="email">
<div class="contact-text">
<h3>Richieste</h3>
<p>Visualizza le Richieste</p>
</div>
</a>


<a class="contact" href="<%=request.getContextPath()%>/_GestoreArea/ordiniGestore.jsp">
<img src="https://images.unsplash.com/photo-1449247666642-264389f5f5b1?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=749&q=80" class="contact-img" alt="email">
<div class="contact-text">
<h3>Ordini</h3>
<p>Visualizza gli ordini</p>
</div>
</a>

</section>

<%@ include file="/partials/footer.jsp" %>	
</body>
</html>