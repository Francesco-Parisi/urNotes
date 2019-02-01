package documenti;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;


import documenti.AggiungiMateria;


public class AggiungiMateriaTest extends Mockito {


	  private AggiungiMateria servlet;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;
	  
	  @Before
	  public void setUp() {
	    servlet = new AggiungiMateria();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	  
	  @Test
	  public void AggiungiMateria() throws ServletException, IOException  {
		    request.addParameter("nome","Storia Moderna");
		    servlet.doPost(request, response);
		    assertEquals("text/html", response.getContentType());
		  }
	  
	  @Test
	  public void AggiungiMateriaFail() throws ServletException, IOException  {
		    request.addParameter("nome","Arte");
		    servlet.doPost(request, response);
		    assertEquals("text/html", response.getContentType());
		  }
	  
	  @Test
	  public void AggiungiMateriaGet() throws ServletException, IOException  {
		    request.addParameter("nome","Storia Moderna");
		    servlet.doGet(request, response);
		    assertEquals("text/html", response.getContentType());
		  }
}
