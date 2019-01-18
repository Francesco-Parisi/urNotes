<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang = "it">

<head>

<%@ include file="/partials/head.jsp" %>
<title>urNotes | FAQ</title>
</head>

<body>
<%@ include file="/partials/header.jsp" %>		


<section class="container_come-funziona">
<div class="section-image">
<div class="title-section_come-funziona">FAQ</div>
<div class="section-text_come-funziona">
Cerca le risposte alle domande generali più frequenti riguardo urNotes
</div>
</div>
</section>

<section class="container_faq">
<div class="title-section">Acquisti</div>
<input type="button" id="faq_button" value="Cosa posso comprare su urNotes?"><p class="faq_content">Su urNotes puoi comprare tutti i documenti di cui hai bisogno: appunti, dispense ecc.</p>
<input type="button" id="faq_button1" value="Di cosa ho bisogno per acquistare un documento?"><p class="faq_content1">Per poter acquistare documenti è necessaria una Carta di Credito,un Bonifico o Contrassegno.</p>
<input type="button" id="faq_button2" value="Dove trovo i documenti comprati?"><p class="faq_content2">Acquistati i documenti verrai avvisato tramite email e in 24h  avverrà la relativa spedizione. Potrai visualizzare l'ordine nella sezione dedicata del tuo profilo.</p>
</section>

<section class="container_faq">
<div class="title-section">Gestione e visibilità Documenti</div>
<input type="button" id="faq_button3" value="Posso caricare un documento su urNotes?"><p class="faq_content3">Certo. Ti basta andare nella sezione dedicata all'invio richiesta di pubblicazione. I tuoi documenti avranno la massima visibilità su urNotes.</p>
<input type="button" id="faq_button4" value="Ho caricato un documento ma non è presente sul sito. Come mai?"><p class="faq_content4">Il documento sarà visualizzabile sul sito entro 24/48 ore.</p>
<input type="button" id="faq_button5" value="Di chi è la responsabilità, in caso di utilizzo non lecito di un mio documento?"><p class="faq_content5">La responsabilità è di chi utilizza in modo illecito il documento</p>
</section>

</body>
<%@ include file="/partials/footer.jsp" %>	
</html>