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

import ordini.AggiungiOrdine;
import ordini.EliminaOrdine;

public class EliminaOrdineTest extends Mockito{

	 private EliminaOrdine servlet;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;

	  /**
	   * Before.
	   */
	  @Before
	  public void setUp() {
	    servlet = new EliminaOrdine();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	
	@Test
	  public void elimina() throws ServletException, IOException  {
	    request.addParameter("serial_id","100");
	    servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	
	
	@Test
	  public void eliminaDoGet() throws ServletException, IOException  {
		request.addParameter("serial_id","100");
	    servlet.doGet(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
}
