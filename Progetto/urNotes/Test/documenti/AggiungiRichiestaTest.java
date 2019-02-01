package documenti;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import documenti.AggiungiRecensione;
import documenti.AggiungiRichiesta;

public class AggiungiRichiestaTest extends Mockito {
	
	  private AggiungiRichiesta servlet;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;

	  
	  @Before
	  public void setUp() {
	    servlet = new AggiungiRichiesta();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	  
	  
	  @Test
	  public void AggiungiRichiesta() throws ServletException, IOException  {
		    request.addParameter("titolo","Mia Richiests");
		    request.addParameter("pagine","21");
		    request.addParameter("universita","Bocconi");
		    request.addParameter("nome_materia","Arte");
		    request.addParameter("descrizione","Questa è la mia richiesta ");
		    request.addParameter("tipo","1");
		    servlet.doPost(request, response);
		    assertEquals("text/html", response.getContentType());
		  }
	  
	  
	  
	  @Test
	  public void AggiungiRichiestaGet() throws ServletException, IOException  {
		    request.addParameter("titolo","Mia Richiests");
		    request.addParameter("pagine","21");
		    request.addParameter("universita","Bocconi");
		    request.addParameter("nome_materia","Arte");
		    request.addParameter("descrizione","Questa è la mia richiesta ");
		    request.addParameter("tipo","1");
		    servlet.doGet(request, response);
		    assertEquals("text/html", response.getContentType());
		  }
	  
	  

}
