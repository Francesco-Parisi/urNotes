package test;
import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import documenti.AggiungiImmagine;





public class AggiungiImmagineTest extends Mockito{
	
	  private AggiungiImmagine servlet;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;
	  
	  @Before
	  public void setUp() {
	    servlet = new AggiungiImmagine();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	  
	  
	  @Test
	  public void AggiungiImmagine() throws ServletException, IOException  {
	    request.addParameter("codice","1040");
	    request.addParameter("filenameImmagineDocumento","Appunti_Di_Biochimica_Scienze_Motorie.jpg");
	    request.addParameter("defaultImmagine","1");
	    servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	  
	  
	  
	  @Test
	  public void AggiungiImmagineget() throws ServletException, IOException  {
		request.addParameter("codice","1040");
	    request.addParameter("filenameImmagineDocumento","Appunti_Di_Biochimica_Scienze_Motorie.jpg");
	    request.addParameter("defaultImmagine","1");
	    servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	  

}
