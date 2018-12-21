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
<title>urNotes | Appunti e Dispense per Studenti Universitari</title>
</head>

<body>
<header class="header clear">

<div><a class="header_logo" href=""><img src="/urNotes/images/menu_logo3.png" height="45" width="150" alt="copertina"></a></div>
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


<div id="wowslider-container1">
<div class="ws_images"><ul>
		<li><img src="data1/images/imagescroll1.png" alt="image1" title="imagescroll1" id="wows1_0"/></li>
		<li><img src="data1/images/imagescroll2.jpg" alt="image2" title="imagescroll2" id="wows1_2"/></li>
		<li><img src="data1/images/imagescroll3.jpg" alt="image3" title="imagescroll3" id="wows1_1"/></li>
	</ul>
</div>

<div class="ws_bullets"><div>
		<a href="#" title="imagescroll1"><span>1</span></a>
		<a href="#" title="imagescroll2"><span>2</span></a>
		<a href="#" title="imagescroll3"><span>3</span></a>
	</div>
</div>

</div>
<script type="text/javascript" src="engine1/wowslider.js"></script>
<script type="text/javascript" src="engine1/script.js"></script>

<section class="container-section">
<div class="section-image">

<div class="title-section">Gli Appunti 2.0</div>

<div class="section-text">
Sarà possibile per ogni insegnamento caricare e visualizzare dispense e appunti.
Ogni Documento sarà commentabile e visionabile in anteprima senza doverlo acquistare.
<img class="image-section1" alt="icon" src="/urNotes/images/icon1.png">
<br>
<a id="button" href="/urNotes/appunti.jsp">Appunti</a>
</div>

</div>       
</section>


<section class="container-section">
<div class="section-image">
<div class="title-section">Ti Aiutiamo nello Studio</div>

<div class="section-text">
 Migliaia di studenti utilizzano urNotes per preparare i loro esami.
 <p>Facile e Veloce</p>
 Cerca l'appunto che ti serve, leggi la sua descrizione e acquista!
 <img class="image-section1" alt="icon" src="/urNotes/images/icon2.png">
<br>
<a id="button" href="/urNotes/come_funziona.jsp">Come Funziona</a>
</div>

</div>       
</section>


<section class="container-section">
<div class="section-image">
<div class="title-section">Studia quando e dove vuoi</div>

<div class="section-text">
Trovare tempo per preparare un esame non è sempre facile, 
per questo hai bisogno di studiare quando e dove vuoi. 
Trova i tuoi appunti sul tuo tablet o 
sul tuo smartphone,mentre vai all'Università.
Acquistali in modo semplice e veloce.
<img class="image-section" alt="icon" src="/urNotes/images/icon3.png">
<br>
<a id="button" href="/urNotes/registrati.jsp">Registrati</a>
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