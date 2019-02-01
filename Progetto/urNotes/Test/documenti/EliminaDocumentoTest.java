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

import documenti.EliminaDocumento;

public class EliminaDocumentoTest extends Mockito{

	 private EliminaDocumento servlet;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;

	  /**
	   * Before.
	   */
	  @Before
	  public void setUp() {
	    servlet = new EliminaDocumento();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	
	@Test
	  public void EliminaDoc() throws ServletException, IOException  {
	    request.addParameter("codice","1020");
		servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	
	@Test
	  public void EliminaDocget() throws ServletException, IOException  {
	    request.addParameter("codice","1020");
	    servlet.doGet(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
}