<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="model.ConnessioneDB,java.sql.*, model.SystemInformation, model.CheckSession"%>
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
			<section class="container_appunti">		
		<div id="content">
			<div id="content-content">

        			<table id='materieTable'>
       					<thead class='adminHeadDataTable'>
      						<tr>
     							<th>Foto</th>
     							<th>Titolo</th>
        						<th>Prezzo</th>
        						<th>Elimina</th>
        					</tr>	
						</thead>
						<tbody id="bodyCarrello" class='adminBodyDataTable'>
						
						</tbody>
					</table>
					
					<button  data-href="<%=request.getContextPath()%>/_StudenteArea/checkout.jsp" class='userButtonCheckout'>Vai al Checkout</button>
			</div>
		</div>
		</section>
		<%@ include file="/partials/footer.jsp" %>	
	</body>
</html>