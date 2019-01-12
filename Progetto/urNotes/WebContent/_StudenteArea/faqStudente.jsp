<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="model.CheckSession, javax.servlet.http.HttpSession, model.SystemInformation" %>

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
<title>urNotes | FAQ</title>
</head>

<body>
<%@ include file="/partials/header.jsp" %>		


<section class="container_appunti">

<div class="titolo-sezione">Come possiamo aiutarti?</div>
<div class="container_faq">
<div class="faq_menu">

<div class="faq_item"><a>Cosa vedere su urNotes?</a>
<div class="faq_item_content">
</div>
</div>

<div class="faq_item"><a>In che modo saranno visibili i documenti che caricherò su urNotes?</a>
<div class="faq_item_content">
</div>
</div>

<div class="faq_item"><a>Come caricare un appunto su urNotes?</a>
<div class="faq_item_content">
</div>
</div>

<div class="faq_item"><a>Posso rimuovere un appunto caricato su urNotes?</a>
<div class="faq_item_content">
</div>
</div>

</div>

</div>
</section>

<%@ include file="/partials/footer.jsp" %>	

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>


</body>
</html>