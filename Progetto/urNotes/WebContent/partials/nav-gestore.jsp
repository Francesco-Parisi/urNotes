<ul class="header_menu animate">
<li class="header_item"><a>Documenti</a>
<ul>
<li class="drop_item"><a href="<%=request.getContextPath()%>/_GestoreArea/appuntiGestore.jsp">Appunti</a></li>
<li class="drop_item"><a href="<%=request.getContextPath()%>/_GestoreArea/dispenseGestore.jsp">Dispense</a></li>
<li class="drop_item"><a href="<%=request.getContextPath()%>/_GestoreArea/aggiungi_documentoGestore.jsp">Aggiungi Documento</a></li>
</ul>
</li>

<li class="header_item"><a>Store</a>
<ul>
<li class="drop_item"><a href="<%=request.getContextPath()%>/_GestoreArea/ordiniGestore.jsp">Ordini</a></li>
<li class="drop_item"><a href="<%=request.getContextPath()%>/faq.jsp">FAQ</a></li>
</ul>
</li>

<li class="header_item"><a href="<%=request.getContextPath()%>/_GestoreArea/contattiGestore.jsp">Contatti</a></li>

<ul class="header_menu_dx">
<li class=header_item_dx><a href="<%=request.getContextPath()%>/_GestoreArea/profilo_gestore.jsp">
<p class="account_item">Ciao <%=request.getSession().getAttribute("nome") %> <i class="fas fa-user"></i></p></a></li>
<li class=header_item_dx><a href="<%=request.getContextPath()%>/logout.jsp">Logout</a></li>
</ul>

</ul>