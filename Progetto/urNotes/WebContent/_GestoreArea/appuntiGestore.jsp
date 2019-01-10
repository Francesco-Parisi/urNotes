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
	<script src="<%=request.getContextPath()%>/js/scripts_materie.js"></script>	
	<script src="<%=request.getContextPath()%>/js/scripts_appunti.js"></script>				
	<title>urNotes | Catalogo Materie</title>		
</head>
<body onLoad="getMaterie()">
	<%@ include file="/partials/header.jsp" %>		
	<section class="container_appunti">	
	<div id="content">
		<div id="content-content">
			<table id="materieTable">
				<thead class="adminHeadDataTable">
					<tr>
						<th>Materie</th>
					</tr>	
				</thead>
				<tbody id="bodyMaterie" class="adminBodyDataTable">
					
				</tbody>
			</table>
			
		</div>
	</div>
</section>	

<%@ include file="/partials/footer.jsp" %>	

</body>
</html>