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
		<script src="<%=request.getContextPath()%>/js/scripts_ordini_admin.js"></script>					
		<title>urNotes | Ordini</title>		
	</head>
	<body onLoad="getOrdini()">
		<%@ include file="/partials/header.jsp" %>		
			<div id="container_appunti_admin">			
		<div id="content">
			<div id="content-content">
				<p class="adminTitoloPagina">Ordini Ricevuti</p> 
				<table id="prodottiTable">
					<thead class="adminHeadDataTable">
						<tr>
							<th class="row_title">ID</th>
							<th class="row_title">Data</th>
							<th class="row_title">N. Prodotti</th>
						    <th class="row_title">Totale</th>
							<th class="row_title">Azioni</th>	
						</tr>	
					</thead>
					<tbody id="bodyOrdini" class="adminBodyDataTable">
						
					</tbody>
				</table>
			</div>
		</div>
		</div>
		<%@ include file="/partials/footer.jsp" %>	
	</body>
</html>