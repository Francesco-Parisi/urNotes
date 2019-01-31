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

import ordini.GetOrdiniUser;

public class GetOrdiniUserTest extends Mockito{

	 private GetOrdiniUser servlet;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;

	  /**
	   * Before.
	   */
	  @Before
	  public void setUp() {
	    servlet = new GetOrdiniUser();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	
	@Test
	  public void getDoPost() throws ServletException, IOException  {
		request.addParameter("id_utente", "2");
	    servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	
	
	@Test
	  public void getDoGet() throws ServletException, IOException  {
	    servlet.doGet(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
}
