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

import documenti.GetFormModificaPrezzoDocumentoAdmin;

public class GetFormModificaPrezzoDocumentoAdminTest extends Mockito{

	 private GetFormModificaPrezzoDocumentoAdmin servlet;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;

	  /**
	   * Before.
	   */
	  @Before
	  public void setUp() {
	    servlet = new GetFormModificaPrezzoDocumentoAdmin();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	
	@Test
	  public void FormModificaPrezzo() throws ServletException, IOException  {
	    request.addParameter("codice","1004");
	    servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	
	@Test
	  public void FormModificaPrezzoget() throws ServletException, IOException  {
	    request.addParameter("codice","1004");
	    servlet.doGet(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
}