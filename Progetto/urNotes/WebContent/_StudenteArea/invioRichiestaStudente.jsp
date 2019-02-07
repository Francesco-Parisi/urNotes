<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="model.CheckSession, javax.servlet.http.HttpSession, connection.ConnessioneDB, java.sql.*" %>
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
		<script src="<%=request.getContextPath()%>/js/scripts_richiesta.js"></script>					
		<title>urNotes | Invio Richiesta</title>
</head>

<body onLoad="getRichiesta()">
		<%@ include file="/partials/header.jsp" %>	
		
	<div id="container_richieste_admin">	
		<div id="content">
			<div id="content-content">
			<p class="adminTitoloPagina">Richieste</p> 
				<table id="prodottiTable">
					<thead class="adminHeadDataTable">
						<tr>
						
							<th class="row_title">Id</th>
							<th class="row_title">Data</th>
     						<th class="row_title">Titolo</th>
							<th class="row_title">Pagine</th>
							<th class="row_title">Materia</th>
							<th class="row_title">Univerisit&agrave;</th>
							<th class="row_title">Descrizione</th>
							<th class="row_title">Tipo</th>
							<th class="row_title">Azione</th>
						</tr>	
					</thead>
					<tbody id="bodyRichiesta" class="adminBodyDataTable">
						
					</tbody>
				</table>
				
				
				<div id="aggiungiRichiesta" class="adminAggiungi">
			
					<button id="buttonAggiungiRichiesta" class="adminButtonAggiungi"><i class="fas fa-plus" style="cursor: pointer;" title="Aggiungi Richiesta"></i></button>
				</div>
								
				<div id="formAggiungiRichiesta" class="adminFormAggiungi" style="display: none;">
				
				</div>
				
			</div>
		</div>
		</div>
	</body>
<%@ include file="/partials/footer.jsp" %>	
</html>