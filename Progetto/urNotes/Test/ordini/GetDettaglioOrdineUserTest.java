package ordini;

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

import ordini.GetDettaglioOrdineUser;

public class GetDettaglioOrdineUserTest extends Mockito{

	 private GetDettaglioOrdineUser servlet;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;

	  /**
	   * Before.
	   */
	  @Before
	  public void setUp() {
	    servlet = new GetDettaglioOrdineUser();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	
	@Test
	  public void getDoPost() throws ServletException, IOException  {
		request.addParameter("serial_id", "100");
		request.addParameter("codice", "1001");
		request.addParameter("filename", "appunti-ilmondocontemporaneo.jpg");
	    servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	
	
	@Test
	  public void getDoGet() throws ServletException, IOException  {
		request.addParameter("serial_id", "100");
		request.addParameter("codice", "1001");
		request.addParameter("filename", "appunti-ilmondocontemporaneo.jpg");
		servlet.doGet(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
}
