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
<title>urNotes | Come Funziona</title>
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

</body>
</html>