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
		<script src="<%=request.getContextPath()%>/js/scripts_documenti_admin.js"></script>					
		<title>urNotes | Documenti</title>
</head>

<body onLoad="getDocumenti()">
		<%@ include file="/partials/header.jsp" %>	
		
	<div id="container_richieste_admin">	
		<div id="content">
			<div id="content-content">
			<p class="adminTitoloPagina">Documenti</p> 
				<table id="prodottiTable">
					<thead class="adminHeadDataTable">
						<tr>
							<th class="row_title">Titolo</th>
							<th class="row_title">Pagine</th>
							<th class="row_title">Univerisit&agrave;</th>
							<th class="row_title">Materia</th>
							<th class="row_title">Descrizione</th>
							<th class="row_title">Prezzo</th>
							<th class="row_title">Tipo</th>
							<th class="row_title">Azioni</th>
						</tr>	
					</thead>
					<tbody id="bodyDocumento" class="adminBodyDataTable">
						
					</tbody>
				</table>
				
				
				<div id="aggiungiDocumento" class="adminAggiungi">
			
					<button id="buttonAggiungiDocumento" class="adminButtonAggiungi"><i class="fas fa-plus" style="cursor: pointer;" title="Aggiungi Documento"></i></button>
				</div>
								
				<div id="formAggiungiDocumento" class="adminFormAggiungi" style="display: none;">
				
				</div>
				
			</div>
		</div>
		</div>
	</body>
<%@ include file="/partials/footer.jsp" %>	
</html>