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

import documenti.GetRecensione;

public class GetRecensioneTest extends Mockito{

	 private GetRecensione servlet;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;

	  /**
	   * Before.
	   */
	  @Before
	  public void setUp() {
	    servlet = new GetRecensione();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	
	@Test
	  public void Recensione() throws ServletException, IOException  {
	    request.addParameter("codice","1002");
	    request.addParameter("username","mick99");
	    request.addParameter("descrizione","Consigliato");
	    servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	
	@Test
	  public void Recensioneget() throws ServletException, IOException  {
		 request.addParameter("codice","1002");
		 request.addParameter("username","mick99");
		 request.addParameter("descrizione","Consigliato");
		 servlet.doGet(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
}