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

import ordini.GetDettaglioOrdineAdmin;
import ordini.GetOrdiniUser;

public class GetDettaglioOrdineAdminTest extends Mockito{

	 private GetDettaglioOrdineAdmin servlet;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;

	  /**
	   * Before.
	   */
	  @Before
	  public void setUp() {
	    servlet = new GetDettaglioOrdineAdmin();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	
	@Test
	  public void getDoPost() throws ServletException, IOException  {
		request.addParameter("serial_id", "100");
	    servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	
	
	@Test
	  public void getDoGet() throws ServletException, IOException  {
		request.addParameter("serial_id", "100");
	    servlet.doGet(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
}
