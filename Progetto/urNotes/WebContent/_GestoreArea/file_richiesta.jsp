<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="model.CheckSession, javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html lang = "it">
	<head>
		<% 
			CheckSession ck = new CheckSession(1 , request.getSession());
			if(ck.getRedirect()){
				String path = request.getContextPath()+ck.getUrlRedirect();
				%>
					<script>
						window.location.href = '<%=path%>';
					</script>
				<%	
			} 
			
			Integer id_richiesta = Integer.parseInt(request.getParameter("id_richiesta"));
		%>
		<%@ include file="/partials/head.jsp" %>		
		<script src="<%=request.getContextPath()%>/js/scripts_upload_file.js"></script>					
		<title>urNotes | File</title>		
	</head>
	<body onLoad="getFile()">
		<%@ include file="/partials/header.jsp" %>	
			<div id="container_appunti_admin">	
					
		<div id="content">
			<div id="content-content">
				<table id="prodottiTable">
					<thead class="adminHeadDataTable">
						<tr>
							<th>File</th>
							<th>Azioni</th>
						</tr>	
					</thead>
					<tbody id="bodyFile" class="adminBodyDataTable">
						
					</tbody>
				</table>
				
				
				<input type="hidden" id="idFileRichiesta" value="<%=id_richiesta %>" />
			</div>
		</div>
		</div>
		<%@ include file="/partials/footer.jsp" %>	
	</body>
</html>