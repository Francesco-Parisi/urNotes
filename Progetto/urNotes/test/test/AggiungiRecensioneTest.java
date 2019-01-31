package test;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import documenti.AggiungiMateria;
import documenti.AggiungiRecensione;


public class AggiungiRecensioneTest extends Mockito{
	

	  private AggiungiRecensione servlet;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;

	  
	  @Before
	  public void setUp() {
	    servlet = new AggiungiRecensione();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	  
	  
	  @Test
	  public void AggiungiRecensione() throws ServletException, IOException  {
		    request.addParameter("descrizione","Ottimo libro.");
		    request.addParameter("nome_utente","mick99");
		    request.addParameter("codice","1001");
		    servlet.doPost(request, response);
		    assertEquals("text/html", response.getContentType());
		  }
	  
	  @Test
	  public void AggiungiRecensioneGet() throws ServletException, IOException  {
		    request.addParameter("descrizione","Ottimo libro.");
		    request.addParameter("nome_utente","mick99");
		    request.addParameter("codice","1001");
		    servlet.doGet(request, response);
		    assertEquals("text/html", response.getContentType());
		  }
}
