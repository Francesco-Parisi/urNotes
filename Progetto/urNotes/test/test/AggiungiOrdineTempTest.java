package test;

import static org.junit.Assert.*;



import java.io.IOException;
import java.security.SecureRandom;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import account.Accedi;
import account.SalvaUtente;
import ordini.AggiungiOrdine;
import ordini.AggiungiOrdineTemp;

public class AggiungiOrdineTempTest extends Mockito{

	 private AggiungiOrdineTemp servlet;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;

	  /**
	   * Before.
	   */
	  @Before
	  public void setUp() {
	    servlet = new AggiungiOrdineTemp();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	
	@Test
	  public void aggiunta() throws ServletException, IOException  {
		request.addParameter("id_richiesta", "1");
	    request.addParameter("spedizioneCheckout","Via Garibaldi 61");
	    request.addParameter("vettoreCheckout", "GLS");
	    request.addParameter("metodoPagamentoCheckout","Bonifico Bancario");
	    servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	
	
	@Test
	  public void aggiuntaDoGet() throws ServletException, IOException  {
		request.addParameter("richiesta","1");
	    //request.addParameter("serial_id","100");
	    //request.addParameter("id_utente","2");
	    servlet.doGet(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
}
