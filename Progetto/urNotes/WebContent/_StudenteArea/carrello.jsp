<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="connection.ConnessioneDB,java.sql.*, model.SystemInformation, model.CheckSession, model.*"%>
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
		<script src="<%=request.getContextPath()%>/js/scripts_carrello.js"></script>
		<title>urNotes | Carrello</title>		
	</head>
	<body onLoad="getCarrello()">
	<%@ include file="/partials/header.jsp" %>		
	<div id="container_appunti_admin">	
		<div id="content">
			<div id="content-content">
				<p class='adminTitoloPagina'>Il tuo carrello</p>

        			<table id='documentiTable'>
       					<thead class='adminHeadDataTable'>
      						<tr>
     							<th class="row_title">Foto</th>
     							<th class="row_title">Titolo</th>
        						<th class="row_title">Prezzo</th>
        					    <th class="row_title">Quantit&agrave;</th>
        						<th class="row_title">Azioni</th>
        					</tr>	
						</thead>
						<tbody id="bodyCarrello" class='adminBodyDataTable'>
						
						</tbody>
					</table>
					
					<button  data-href="<%=request.getContextPath()%>/_StudenteArea/checkout.jsp" class='userButtonCheckout'>Vai al Checkout</button>
			</div>
		</div>
	</div>
		<%@ include file="/partials/footer.jsp" %>	
	</body>
</html>