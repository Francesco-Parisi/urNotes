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
<title>urNotes | Come Funziona</title>
</head>

<body>
<%@ include file="/partials/header.jsp" %>		

<section class="container_come-funziona">
<div class="section-image">
<div class="title-section_come-funziona">Come Funziona urNotes Store</div>
<div class="section-text_come-funziona">
Valorizza i tuoi documenti didattici: Condividili con gli altri utenti!
</div>
</div>       
</section>

<section class="container-thumbnail">
<div class="title-section">Acquista ciò che ti serve</div>
<div class="section-text_image">Cerca i contenuti che ti servono per preparare un esame. Procedi con calma, puoi vedere tutti gli appunti e dispense presenti e poi decidi se acquistarli.</div>
<img class="image-section_come-funziona" alt="icon" src="/urNotes/images/buy.png">
</section>

<section class="container-thumbnail">
<div class="title-section">Carica i documenti</div>
<div class="section-text_image">Scegli i contenuti che vuoi condividere su urNotes e caricali in qualsiasi formato digitale. Procedi solo se ne possiedi la piena proprietà intellettuale</div>
<img class="image-section_come-funziona" alt="icon" src="/urNotes/images/document.png">
</section>

<section class="container-thumbnail">
<div class="title-section">Il nostro modello Educativo</div>
<div class="section-text_image">Crediamo che ogni studente abbia contenuti e conoscenze da condividere. urNotes crea l'ecosistema in cui è possibile farlo in modo divertente ed efficace.</div>
<img class="image-section_come-funziona" alt="icon" src="/urNotes/images/education.png">
</section>

</body>
<%@ include file="/partials/footer.jsp" %>	
</html>