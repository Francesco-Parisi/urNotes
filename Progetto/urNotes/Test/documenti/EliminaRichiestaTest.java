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

import documenti.EliminaRichiesta;

public class EliminaRichiestaTest extends Mockito{

	 private EliminaRichiesta servlet;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;

	  /**
	   * Before.
	   */
	  @Before
	  public void setUp() {
	    servlet = new EliminaRichiesta();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	
	@Test
	  public void EliminaRic() throws ServletException, IOException  {
	    request.addParameter("id_richiesta","101");
		servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	
	@Test
	  public void EliminaRicget() throws ServletException, IOException  {
	    request.addParameter("id_richiesta","101");
	    servlet.doGet(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
}