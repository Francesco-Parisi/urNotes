package documenti;

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

import documenti.GetFormAggiungiIndirizzo;

public class GetFormAggiungiIndirizzoTest extends Mockito{

	 private GetFormAggiungiIndirizzo servlet;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;

	  /**
	   * Before.
	   */
	  @Before
	  public void setUp() {
	    servlet = new GetFormAggiungiIndirizzo();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	
	@Test
	  public void FormIndirizzo() throws ServletException, IOException  {
	    request.addParameter("richiesta","1");
	    request.addParameter("username","mick99");
	    request.addParameter("id_utente","4");

	    servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	
	@Test
	  public void FormIndirizzoget() throws ServletException, IOException  {
	    request.addParameter("richiesta","1");
	    request.addParameter("richiesta","1");
	    request.addParameter("username","mick99");
	    request.addParameter("id_utente","4");
	    servlet.doGet(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
}