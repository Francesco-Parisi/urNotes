<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang = "it">

<head>
<%@ include file="/partials/head.jsp" %>	
<script src="<%=request.getContextPath()%>/js/scripts_appunti.js"></script>			
<title>urNotes | Appunti</title>
</head>
<!--  
<body>
<%@ include file="/partials/header.jsp" %>		


<section class="container_ricerca">
</section>

<section class="container_appunti">
<div class="titolo-sezione">Appunti</div>
-->
<body onLoad="getProdotti()">
		<%@ include file="/partials/header.jsp" %>				
		<div id="content">
			<div id="content-content">
				<p class="adminTitoloPagina">Gestione Prodotti</p> 
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
					<button id="buttonAggiungiAppunto" class="adminButtonAggiungi"><i class="fas fa-plus"></i></button>
				</div>
				
				<div id="formAggiungiAppunto" class="adminFormAggiungi" style="display: none;">
					
				</div>









<!--
<table>

<tr>
<th>A</th>
<th>B</th>
<th>C</th>
<th>D</th>
<th>E</th>
</tr>

<div class="tabella">
<tr>
<th><a href="#">Algebra</a></th>
<th><a href="#">Biochimica</a></th>
<th><a href="#">Chimica</a></th>
<th><a href="#">Dietetica</a></th>
<th><a href="#">Ecologia</a></th>
</tr>
</div>

<div class="tabella">
<tr>
<th>Algoritmi</th>
<th>Basi Dati</th>
<th>Calcolatori</th>
<th>Diritto</th>
<th>Economia</th>
</tr>
</div>

<div class="tabella">
<tr>
<th>Anatomia</th>
<th>Biofisica</th>
<th>Analisi I</th>
<th>Analisi II</th>
<th>Analisi II</th>
</tr>
</div>

<div class="tabella">
<tr>
<th>Algoritmi</th>
<th>Basi Dati</th>
<th>Analisi I</th>
<th>Analisi II</th>
<th>Analisi II</th>
</tr>
</div>
</table>

<br>

<table>

<tr>
<th>F</th>
<th>G</th>
<th>I</th>
<th>L</th>
<th>M</th>
</tr>

<div class="tabella">
<tr>
<th>Algebra</th>
<th>Biochimica</th>
<th>Analisi I</th>
<th>Analisi II</th>
<th>Analisi II</th>
</tr>
</div>

<div class="tabella">
<tr>
<th>Algoritmi</th>
<th>Basi Dati</th>
<th>Analisi I</th>
<th>Analisi II</th>
<th>Analisi II</th>
</tr>
</div>

<div class="tabella">
<tr>
<th>Algoritmi</th>
<th>Basi Dati</th>
<th>Analisi I</th>
<th>Analisi II</th>
<th>Analisi II</th>
</tr>
</div>

<div class="tabella">
<tr>
<th>Algoritmi</th>
<th>Basi Dati</th>
<th>Analisi I</th>
<th>Analisi II</th>
<th>Analisi II</th>
</tr>
</div>

</table>

</section>
-->
</div>
</div>



<%@ include file="/partials/footer.jsp" %>	




<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script>
	 $(document).ready(function(){

			$(".header_icon").click(function(e){

				$(".header_menu").toggleClass('is-open');
				e.preventDefault();

			});
	 });
</script>

</body>
</html>