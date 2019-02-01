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

import documenti.GetFormAggiungiRichiesta;

public class GetFormAggiungiRichiestaTest extends Mockito{

	 private GetFormAggiungiRichiesta servlet;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;

	  /**
	   * Before.
	   */
	  @Before
	  public void setUp() {
	    servlet = new GetFormAggiungiRichiesta();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	
	@Test
	  public void FormRichiesta() throws ServletException, IOException  {
	    request.addParameter("richiesta","1");
	    servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	
	@Test
	  public void FormRichiestaget() throws ServletException, IOException  {
	    request.addParameter("richiesta","1");
	    servlet.doGet(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
}