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

import documenti.GetDispenseDett;

public class GetDispenseDettTest extends Mockito{

	 private GetDispenseDett servlet;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;

	  /**
	   * Before.
	   */
	  @Before
	  public void setUp() {
	    servlet = new GetDispenseDett();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	
	@Test
	  public void Dispense() throws ServletException, IOException  {
	    request.addParameter("value","1015");
	    servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	
	@Test
	  public void DispenseDettaglioget() throws ServletException, IOException  {
		    request.addParameter("value","1015");
		    servlet.doGet(request, response);
		    assertEquals("text/html", response.getContentType());
		  }
}