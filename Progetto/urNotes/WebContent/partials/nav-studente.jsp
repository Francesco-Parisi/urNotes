<ul class="header_menu animate">
<li class="header_item"><a>Documenti</a>
<ul>
<li class="drop_item"><a href="<%=request.getContextPath()%>/_StudenteArea/appuntiStudente.jsp">Appunti</a></li>
<li class="drop_item"><a href="<%=request.getContextPath()%>/_StudenteArea/dispenseStudente.jsp">Dispense</a></li>
</ul>
</li>

<li class="header_item"><a>Store</a>
<ul>
<li class="drop_item"><a href="<%=request.getContextPath()%>/_StudenteArea/comeFunzionaStudente.jsp">Come Funziona</a></li>
<li class="drop_item"><a href="<%=request.getContextPath()%>/_StudenteArea/faqStudente.jsp">FAQ</a></li>
</ul>
</li>

<li class="header_item"><a href="<%=request.getContextPath()%>/_StudenteArea/contattiStudente.jsp">Contatti</a></li>

<ul class="header_menu_dx">
<li class=header_item_dx><a href="<%=request.getContextPath()%>/_StudenteArea/profilo_studente.jsp">
<p class="account_item">Ciao <%=request.getSession().getAttribute("nome") %> <i class="fas fa-user"></i></p></a></li>
<li class=header_item_dx><a href="<%=request.getContextPath()%>/_StudenteArea/carrello.jsp"><i class="fas fa-shopping-cart"></i> <span class="numeroProdottiCarrello"></span></a></li>
<li class=header_item_dx><a href="<%=request.getContextPath()%>/logout.jsp">Logout</a></li>
</ul>

</ul>