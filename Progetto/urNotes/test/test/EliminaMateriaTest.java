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

import documenti.EliminaMateria;

public class EliminaMateriaTest extends Mockito{

	 private EliminaMateria servlet;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;

	  /**
	   * Before.
	   */
	  @Before
	  public void setUp() {
	    servlet = new EliminaMateria();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	
	@Test
	  public void EliminaMat() throws ServletException, IOException  {
	    request.addParameter("nome","Telecomunicazioni");
		servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	
	@Test
	  public void EliminaMatget() throws ServletException, IOException  {
	    request.addParameter("nome","Telecomunicazioni");
	    servlet.doGet(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
}