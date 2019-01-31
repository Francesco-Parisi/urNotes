package test;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import documenti.AggiungiAlCarrello;;

public class AggiungiAlCarrelloTest extends Mockito {

	  private AggiungiAlCarrello servlet;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;
	  
	  
	  @Before
	  public void setUp() {
	    servlet = new AggiungiAlCarrello();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	  
	  @Test
	  public void AggiungiAlCarrello() throws ServletException, IOException  {
	    request.addParameter("codice","1001");
	    request.addParameter("quantita","2");
	    servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	  
	  @Test
	  public void FailAlCarrello() throws ServletException, IOException  {
	    request.addParameter("codice","2304");
	    request.addParameter("quantita","2");
	    servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	  
	  @Test
	  public void FailAlCarrelloGet() throws ServletException, IOException  {
	    request.addParameter("codice","2304");
	    request.addParameter("quantita","2");
	    servlet.doGet(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	  
	  
	  
	  
	  
	  
	  
	  
	  
}
