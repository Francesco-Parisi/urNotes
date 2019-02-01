package documenti;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import documenti.AggiornaPrezzoDocumentoAdmin;

public class AggiornaPrezzoDocumentoAdminTest {

	 private AggiornaPrezzoDocumentoAdmin servlet;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;
	  
	  @Before
	  public void setUp() {
	    servlet = new AggiornaPrezzoDocumentoAdmin();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	  

		@Test
		  public void AggiornaPrezzo() throws ServletException, IOException  {
		    request.addParameter("codice","1001");
		    request.addParameter("prezzoDocumento","1.50");
		    servlet.doPost(request, response);
		    assertEquals("text/html", response.getContentType());
		  }
		

		@Test
		  public void AggiornaFail() throws ServletException, IOException  {
		    request.addParameter("codice","96");
		    request.addParameter("prezzoDocumento","3.20");
		    servlet.doPost(request, response);
		    assertEquals("text/html", response.getContentType());
		  }
		
		@Test
		  public void TestDoGet() throws ServletException, IOException  {
		    request.addParameter("codice","1001");
		    request.addParameter("prezzoDocumento","1.50");
		    servlet.doGet(request, response);
		    assertEquals("text/html", response.getContentType());
		  }
		

}
