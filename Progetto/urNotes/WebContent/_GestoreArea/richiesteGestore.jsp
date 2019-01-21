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
		<script src="<%=request.getContextPath()%>/js/scripts_richieste_admin.js"></script>					
		<title>urNotes | Richieste</title>		
	</head>
	<body onLoad="getRichieste()">
		<%@ include file="/partials/header.jsp" %>				
	<div id="container_richieste_admin">	
		<div id="content">
			<div id="content-content">
				<p class="adminTitoloPagina">Gestione Richieste</p> 
				<table id="prodottiTable">
					<thead class="adminHeadDataTable">
						<tr>
							<th>Data</th>
							<th>Studente</th>
							<th>Titolo</th>
							<th>Pagine</th>
							<th>Materia</th>
							<th>Universita</th>
							<th>Descrizione</th>
							<th>Tipo</th>
							<th>Azioni</th>
						</tr>	
					</thead>
					<tbody id="bodyContatti" class="adminBodyDataTable">
						
					</tbody>
				</table>							
			</div>
		</div>
		</div>
		<%@ include file="/partials/footer.jsp" %>	
	</body>
</html>