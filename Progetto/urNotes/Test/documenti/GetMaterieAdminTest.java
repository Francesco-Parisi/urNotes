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

import documenti.GetMaterieAdmin;

public class GetMaterieAdminTest extends Mockito{

	 private GetMaterieAdmin servlet;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;

	  /**
	   * Before.
	   */
	  @Before
	  public void setUp() {
	    servlet = new GetMaterieAdmin();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	
	@Test
	  public void MaterieAdmin() throws ServletException, IOException  {
	    servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	
	@Test
	  public void MaterieAdminget() throws ServletException, IOException  {
	    servlet.doGet(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
}