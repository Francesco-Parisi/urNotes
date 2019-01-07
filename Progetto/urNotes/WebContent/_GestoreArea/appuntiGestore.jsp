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
		<script src="<%=request.getContextPath()%>/js/scripts_appunti.js"></script>					
		<title>urNotes | Catalogo Materie</title>		
	</head>
	<body onLoad="getAppunti()">
		<%@ include file="/partials/header.jsp" %>
		<section class="container_appunti"> 				
		<div id="content">
			<div id="content-content">
				<p class="adminTitoloPagina">Gestione Appunti</p> 
				<table id="prodottiTable">
					<thead class="adminHeadDataTable">
						<tr>
							<th>ID</th>
							<th>Categoria</th>
							<th>Nome</th>
							<th>Descrizione</th>
							<th>Descrizione Abbreviata</th>
							<th>Qt&agrave; Disp.</th>
							<th>Unit&agrave;</th>
							<th>Prezzo</th>
							<th>Aliquota</th>							
							<th>Azioni</th>
						</tr>	
					</thead>
					<tbody id="bodyAppunti" class="adminBodyDataTable">
						
					</tbody>
				</table>
				
				<div id="aggiungiAppunto" class="adminAggiungi">
					<button id="buttonAggiungiProdotto" class="adminButtonAggiungi"><i class="fas fa-plus"></i></button>
				</div>
				
				<div id="formAggiungiAppunto" class="adminFormAggiungi" style="display: none;">
					
				</div>
				
			</div>
		</div>
		</section>
		<%@ include file="/partials/footer.jsp" %>	
	</body>
</html>