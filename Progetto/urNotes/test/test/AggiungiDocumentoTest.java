package test;
import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import account.Accedi;
import documenti.AggiungiDocumento;


public class AggiungiDocumentoTest extends Mockito {


	  private AggiungiDocumento servlet;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;
	  
	  
	  @Before
	  public void setUp() {
	    servlet = new AggiungiDocumento();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	  
	  
	  @Test
	  public void AggiungiAlCarrello() throws ServletException, IOException  {
	    request.addParameter("titolo","La Nebbia");
	    request.addParameter("pagine","20");
	    request.addParameter("universita","Bocconi");
	    request.addParameter("nome_materia","Arte");
	    request.addParameter("descrizione","è veramente un bel libro");
	    request.addParameter("prezzoDocumento","2.50");
	    request.addParameter("tipo","2");
	    servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	  
	  
	  @Test
	  public void AggiungiAlCarrelloFail() throws ServletException, IOException  {
	    request.addParameter("titolo","L");
	    request.addParameter("pagine","20");
	    request.addParameter("universita","Bocconi");
	    request.addParameter("nome_materia","Arte");
	    request.addParameter("descrizione","è");
	    request.addParameter("prezzoDocumento","2.50");
	    request.addParameter("tipo","4");
	    servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	  
	  
	  @Test
	  public void AggiungiAlCarrelloGet() throws ServletException, IOException  {
		    request.addParameter("titolo","La Pecora");
		    request.addParameter("pagine","20");
		    request.addParameter("universita","Bocconi");
		    request.addParameter("nome_materia","Arte");
		    request.addParameter("descrizione","è veramente un bel libro");
		    request.addParameter("prezzoDocumento","2.50");
		    request.addParameter("tipo","1");
		    servlet.doGet(request, response);
		    assertEquals("text/html", response.getContentType());
		  }
		  
	  
}
