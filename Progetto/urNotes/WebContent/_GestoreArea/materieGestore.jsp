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
		<script src="<%=request.getContextPath()%>/js/scripts_materie_admin.js"></script>					
		<title>urNotes | Materie</title>
</head>

<body onLoad="getMaterieAdmin()">
		<%@ include file="/partials/header.jsp" %>	
		
	<div id="container_appunti_admin">	
		<div id="content">
			<div id="content-content">
			<p class="adminTitoloPagina">Materie</p> 
				<table id="prodottiTable">
					<thead class="adminHeadDataTable">
						<tr>
							<th>Nome</th>
							<th>Quantità Appunti</th>
							<th>Quantità Dispense</th>
							<th>Azioni</th>
							
						</tr>	
					</thead>
					<tbody id="bodyMateria" class="adminBodyDataTable">
						
					</tbody>
				</table>
				
				<div id="aggiungiMateria" class="adminAggiungi">
					<button id="buttonAggiungiMateria" class="adminButtonAggiungi"><i class="fas fa-plus"></i></button>
				</div>
				
				<div id="formAggiungiMateria" class="adminFormAggiungi" style="display: none;">
					
				</div>
				
			</div>
		</div>
		</div>
	</body>
<%@ include file="/partials/footer.jsp" %>	
</html>