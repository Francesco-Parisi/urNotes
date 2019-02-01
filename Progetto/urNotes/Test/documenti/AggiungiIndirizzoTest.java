package documenti;
import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;


import documenti.AggiungiIndirizzo;



public class AggiungiIndirizzoTest extends Mockito {
	
	
	  private AggiungiIndirizzo servlet;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;
	  
	  @Before
	  public void setUp() {
	    servlet = new AggiungiIndirizzo();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	  
	  @Test
	  public void AggiungiAlCarrello() throws ServletException, IOException  {
		    request.addParameter("id_utente","2");
		    request.addParameter("cap","19191");
		    request.addParameter("citta","Salerno");
		    request.addParameter("provincia","Fisciano");
		    request.addParameter("nome","Marco");
		    request.addParameter("cognome","La cortiglia");
		    request.addParameter("indirizzo","viaTemponeGiampiero");
		    request.addParameter("telefono","0828984843");
		    request.addParameter("cellulare","3336576323");
		    servlet.doPost(request, response);
		    assertEquals("text/html", response.getContentType());
		  }
	  
	  @Test
	  public void AggiungiAlCarrelloGet() throws ServletException, IOException  {
		    request.addParameter("cap","19191");
		    request.addParameter("citta","Salerno");
		    request.addParameter("provincia","Fisciano");
		    request.addParameter("nome","Marco");
		    request.addParameter("cognome","La cortiglia");
		    request.addParameter("indirizzo","viaTemponeGiampiero");
		    request.addParameter("telefono","0828984843");
		    request.addParameter("cellulare","3336576323");
		    servlet.doGet(request, response);
		    assertEquals("text/html", response.getContentType());
		  }
		  
	  
	  
	  

}
