<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>

<head>
<link rel="stylesheet" href="stile.css">
<link rel="stylesheet" type="text/css" href="engine1/style.css" />
<script type="text/javascript" src="engine1/jquery.js"></script>
<link rel="icon"  type="image/png" href="/urNotes/images/logo.png">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.0/normalize.css">
<title>urNotes | Faq su Store</title>
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
<li class="drop_item"><a href="/urNotes/appunti.jsp">Appunti</a></li>
<li class="drop_item"><a href="/urNotes/dispense.jsp">Dispense</a></li>
</ul>
</li>

<li class="header_item"><a>Store</a>
<ul>
<li class="drop_item"><a href="/urNotes/comeFunziona.jsp">Come Funziona</a></li>
<li class="drop_item"><a href="/urNotes/faq.jsp">FAQ</a></li>
</ul>
</li>

<li class="header_item"><a href="/urNotes/contatti.jsp">Contatti</a></li>

<ul class="header_menu_dx">
<li class=header_item_dx><a href="/urNotes/accedi.jsp">Accedi</a></li>
<li class=header_item_dx><a href="/urNotes/registrati.jsp">Registrati</a></li>
</ul>

</ul>
</header>

<section class="container_appunti">

<div class="titolo-sezione">Come possiamo aiutarti?</div>

<div class="container_faq">

<div class="faq_menu">

<div class="faq_item"><a>Cosa vedere su urNotes?</a>
<div class="faq_item_content">
<a>Su urNotes puoi vedere Appunti, Dispense e tutto cio che ti serve per prepararti agli esami.</a>
</div>
</div>

<div class="faq_item"><a>In che modo saranno visibili i documenti che caricherò su urNotes?</a>
<div class="faq_item_content">
<a>I tuo documenti caricati avranno la massima visibilità su urNotes</a>
</div>
</div>

<div class="faq_item"><a>Come caricare un appunto su urNotes?</a>
<div class="faq_item_content">
<a>Ti basta andare nella sezione dedicata all'Invio Appunti e ,una volta compilati i campi richiesti, invia il documento</a>
</div>
</div>

<div class="faq_item"><a>Posso rimuovere un appunto caricato su urNotes?</a>
<div class="faq_item_content">
<a>Certo, ti basta contattare il gestore tramite email e in 48/72 ore verrà rimosso.</a>
</div>
</div>

</div>

</div>



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

<script>
function myFunction() {
    document.getElementById("content").classList.toggle("show");
}
</script>

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