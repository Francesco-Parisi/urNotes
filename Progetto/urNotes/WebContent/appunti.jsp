<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>

<head>
<link rel="stylesheet" href="stile.css">
<link rel="icon"  type="image/png" href="/urNotes/images/urnotes.png">
<link rel="icon"  type="image/png" href="/urNotes/images/logo.png">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.0/normalize.css">
<title>urNotes | Appunti</title>
</head>

<body>
<header class="header clear">

<div><a class="header_logo" href="/urNotes/index.jsp"><img src="/urNotes/images/menu_logo3.png" height="45" width="150" alt="copertina"></a></div>
<a class="header_icon" href="">
<span></span>
<span></span>
<span></span>
</a>

<ul class="header_menu animate">
<li class="header_item"><a>Documenti</a>
<ul>
<li class="drop_item"><a href="#">Appunti</a></li>
<li class="drop_item"><a href="#">Dispense</a></li>
</ul>
</li>

<li class="header_item"><a>Store</a>
<ul>
<li class="drop_item"><a href="#">Come Funziona</a></li>
<li class="drop_item"><a href="#">FAQ</a></li>
</ul>
</li>

<li class="header_item"><a href="#">Contatti</a></li>

<ul class="header_menu_dx">
<li class=header_item_dx><a href="/urNotes/accedi.jsp">Accedi</a></li>
<li class=header_item_dx><a href="/urNotes/registrati.jsp">Registrati</a></li>
</ul>
</ul>
</header>

<section class="container_ricerca">
</section>

<section class="container_appunti">
<div class="titolo-sezione">Appunti</div>

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





<footer class="footer">
<div class="image-footer">
<a href="/urNotes/index.jsp"><img src="/urNotes/images/menu_logo3.png" height="100" width="250" alt="logo"></a>
</div>

<div class="container-footer">
<div class="copyright">Copyright&copy; - 2018 urNotes</div>
<p class="footer-terms"><a href="/urNotes/index.jsp">Home</a> | <a href="/urNotes/appunti.jsp">Appunti</a> | <a href="/urNotes/dispense.jsp">Dispense</a> | <a href="/urNotes/store.jsp">Store</a> | <a href="/urNotes/contatti.jsp">Contatti</a> | <a href="/urNotes/privacy.jsp">Privacy</a></p>
<div class="copyright">urNotes Italia S.r.l </div>
</div>

</footer>



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