<div id="modalDettaglioOrdine">			
			<div id="modalDettaglioOrdineNav">
				<i class="fas fa-times chiudiModalDettaglioOrdine"></i>
			</div>			
			<div id="modalDettaglioOrdineBody">				
			</div>
		</div>
<header class="header clear">

<div><a class="header_logo" href="<%=request.getContextPath()%>/index.jsp"><img src="<%=request.getContextPath()%>/images/menu_logo3.png" height="45" width="150" alt="copertina"></a></div>

<a class="header_icon" href="">
<span></span>
<span></span>
<span></span>
</a>

<%		
	if((Integer) request.getSession().getAttribute("tipo_utente") == null){
		%><%@ include file="/partials/nav-ospite.jsp" %><%
	} 
	else if((Integer) request.getSession().getAttribute("tipo_utente") == 1){ 
		%><%@ include file="/partials/nav-gestore.jsp" %><%
   	}
   	else if((Integer) request.getSession().getAttribute("tipo_utente") == 2){					   	
   		%><%@ include file="/partials/nav-studente.jsp" %><%
   	}
%>		
</header>
