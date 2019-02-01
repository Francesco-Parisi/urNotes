package account;

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

import account.Accedi;

public class AccediTest extends Mockito{

	 private Accedi servlet;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;

	  /**
	   * Before.
	   */
	  @Before
	  public void setUp() {
	    servlet = new Accedi();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	
	@Test
	  public void AccediAdmin() throws ServletException, IOException  {
	    request.addParameter("username","franpa96");
	    request.addParameter("password","admin1");
	    servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	
	@Test
	  public void AccediUser() throws ServletException, IOException  {
	    request.addParameter("username","mick99");
	    request.addParameter("password","mrullo");
	    servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	
	@Test
	  public void AccediFail() throws ServletException, IOException  {
	    request.addParameter("username","pippo");
	    request.addParameter("password","admin1");
	    servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	
	@Test
	  public void AccedidoGet() throws ServletException, IOException  {
	    request.addParameter("username","franpa96");
	    request.addParameter("password","admin1");
	    servlet.doGet(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
}
