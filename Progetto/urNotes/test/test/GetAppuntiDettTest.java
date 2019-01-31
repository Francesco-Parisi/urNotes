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

import documenti.GetAppuntiDett;

public class GetAppuntiDettTest extends Mockito{

	 private GetAppuntiDett servlet;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;

	  /**
	   * Before.
	   */
	  @Before
	  public void setUp() {
	    servlet = new GetAppuntiDett();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	
	@Test
	  public void AppuntiDettaglio() throws ServletException, IOException  {
	    request.addParameter("value","");
	    servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	
	  public void AppuntiDettaglioget() throws ServletException, IOException  {
		    request.addParameter("value","");
		    servlet.doGet(request, response);
		    assertEquals("text/html", response.getContentType());
		  }
}