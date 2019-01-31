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

import documenti.GetFormAggiungiFile;

public class GetFormAggiungiFileTest extends Mockito{

	 private GetFormAggiungiFile servlet;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;

	  /**
	   * Before.
	   */
	  @Before
	  public void setUp() {
	    servlet = new GetFormAggiungiFile();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	
	@Test
	  public void FormFile() throws ServletException, IOException  {
	    request.addParameter("richiesta","1");
	    servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	
	@Test
	  public void FormFileget() throws ServletException, IOException  {
	    request.addParameter("richiesta","1");
	    servlet.doGet(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
}