<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="model.CheckSession, javax.servlet.http.HttpSession" %>
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
		<script src="<%=request.getContextPath()%>/js/scripts_clienti.js"></script>					
		<title>urNotes | Clienti</title>		
	</head>
	<body onLoad="getClienti()">
		<%@ include file="/partials/header.jsp" %>				
	<div id="container_appunti_admin">				
		<div id="content">
			<div id="content-content">
				<p class="adminTitoloPagina">Gestione Clienti</p> 
				<table id="prodottiTable">
					<thead class="adminHeadDataTable">
						<tr>
							<th>ID</th>
							<th>Email</th>
							<th>Username</th>
							<th>Nome</th>
							<th>Cognome</th>
							<th>Azione</th>
						</tr>	
					</thead>
					<tbody id="bodyClienti" class="adminBodyDataTable">
						
					</tbody>
				</table>				
			</div>
		</div>
		</div>
		<%@ include file="/partials/footer.jsp" %>	
	</body>
</html>