<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang = "it">

<head>
<%@ include file="/partials/head.jsp" %>				
	<script src="<%=request.getContextPath()%>/js/scripts_materieDispense.js"></script>	
	<script src="<%=request.getContextPath()%>/js/scripts_dispense.js"></script>				
	<title>urNotes | Catalogo</title>		
</head>
<body onLoad="getMaterieDispense()">
	<%@ include file="/partials/header.jsp" %>		
	<div id="container_appunti_admin">	
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
</div>	

<%@ include file="/partials/footer.jsp" %>	

</body>
</html>