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

import ordini.AggiungiOrdine;

public class AggiungiOrdineTest extends Mockito{

	 private AggiungiOrdine servlet;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;

	  /**
	   * Before.
	   */
	  @Before
	  public void setUp() {
	    servlet = new AggiungiOrdine();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	
	@Test
	  public void aggiunta() throws ServletException, IOException  {
	    request.addParameter("richiesta","1");
	    request.addParameter("serial_id","108");
	    request.addParameter("id_utente","2");
	    servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	
	
	@Test
	  public void aggiuntaDoGet() throws ServletException, IOException  {
		request.addParameter("richiesta","1");
		request.addParameter("serial_id","108");
	    request.addParameter("id_utente","2");
	    servlet.doGet(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
}
