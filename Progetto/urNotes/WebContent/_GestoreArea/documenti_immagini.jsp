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
			
			Integer codice = Integer.parseInt(request.getParameter("codice"));
		%>
		<%@ include file="/partials/head.jsp" %>		
		<script src="<%=request.getContextPath()%>/js/scripts_prodotti_immagini.js"></script>					
		<title>urNotes | Documenti Immagini</title>		
	</head>
	<body onLoad="getImmagini()">
		<%@ include file="/partials/header.jsp" %>	
			<div id="container_appunti_admin">	
					
		<div id="content">
			<div id="content-content">
				<table id="immaginiTable">
					<thead class="adminHeadDataTable">
						<tr>
							<th>FileName</th>
							<th>Primaria</th>
							<th>Elimina</th>
						</tr>	
					</thead>
					<tbody id="bodyImmagini" class="adminBodyDataTable">
						
					</tbody>
				</table>
				
				<div id="aggiungiImmagine" class="adminAggiungi">
					<button id="buttonAggiungiImmagine" class="adminButtonAggiungi"><i class="fas fa-plus"></i></button>
				</div>
				
				<div id="formAggiungiImmagine" class="adminFormAggiungi" style="display: none;">
					
				</div>
				<input type="hidden" id="idDocumentoImmagine" value="<%=codice %>" />
			</div>
		</div>
		</div>
		<%@ include file="/partials/footer.jsp" %>	
	</body>
</html>