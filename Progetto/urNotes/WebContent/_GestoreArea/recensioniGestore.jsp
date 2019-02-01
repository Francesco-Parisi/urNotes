<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="model.CheckSession, javax.servlet.http.HttpSession, connection.ConnessioneDB, java.sql.*" %>
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
		<script src="<%=request.getContextPath()%>/js/scripts_recensioni_admin.js"></script>					
		<title>urNotes | Recensioni</title>
</head>

<body onLoad="getRecensioneAdmin()">
		<%@ include file="/partials/header.jsp" %>	
		
	<div id="container_appunti_admin">	
		<div id="content">
			<div id="content-content">
			<p class="adminTitoloPagina">Recensioni</p> 
				<table id="prodottiTable">
					<thead class="adminHeadDataTable">
						<tr>
							<th>Username</th>
							<th>Descrizione</th>
							<th>Documento</th>
							<th>Azioni</th>
						</tr>	
					</thead>
					<tbody id="bodyRecensioni" class="adminBodyDataTable">
						
					</tbody>
				</table>
				
				
				
				
				
			</div>
		</div>
		</div>
	</body>
<%@ include file="/partials/footer.jsp" %>	
</html>