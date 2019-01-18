<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang = "it">

<head>
<%@ include file="/partials/head.jsp" %>				
	<script src="<%=request.getContextPath()%>/js/scripts_materieDispense.js"></script>	
	<script src="<%=request.getContextPath()%>/js/scripts_dispense.js"></script>				
	<title>urNotes | Documenti</title>		
</head>
<body onLoad="getMaterieDispense()">
	<%@ include file="/partials/header.jsp" %>		
	<section class="container_appunti">	
	<div id="content">
		<div id="content-content">
			<table id="materieTable">
				<thead class="adminHeadDataTable">
					<tr>
						<th>Cerca Dispense di una materia specifica</th>
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