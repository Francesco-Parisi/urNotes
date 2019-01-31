package test;
import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;


import documenti.AggiungiFile;





public class AggiungiFileTest extends Mockito {

	  private AggiungiFile servlet;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;
	  
	  @Before
	  public void setUp() {
	    servlet = new AggiungiFile();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	  
	  
	  @Test
	  public void AggiungiFile() throws ServletException, IOException  {
	    request.addParameter("id_richiesta","163");
	    request.addParameter("filenameFileRichiesta","richiesta.pdf");
	    servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	  
	  @Test
	  public void AggiungiFileget() throws ServletException, IOException  {
	    request.addParameter("id_richiesta","190");
	    request.addParameter("filenameFileRichiesta","prova.pdf");
	    servlet.doGet(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	  
}
