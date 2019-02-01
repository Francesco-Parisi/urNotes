package documenti;

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

import documenti.UploadImmagineDocumento;

public class UploadImmagineDocumentoTest extends Mockito{

	 private UploadImmagineDocumento servlet;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;

	  /**
	   * Before.
	   */
	  @Before
	  public void setUp() {
	    servlet = new UploadImmagineDocumento();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	
	@Test
	  public void UploadImmagine() throws ServletException, IOException  {
	    request.addParameter("filePath","prova");
		servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	
	@Test
	  public void UploadImmagineget() throws ServletException, IOException  {
	    request.addParameter("filePath","prova");
	    servlet.doGet(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
}