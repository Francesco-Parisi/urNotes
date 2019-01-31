package test;

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

import documenti.ScaricaFile;

public class ScaricaFileTest extends Mockito{

	 private ScaricaFile servlet;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;

	  /**
	   * Before.
	   */
	  @Before
	  public void setUp() {
	    servlet = new ScaricaFile();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	
	@Test
	  public void Scarica() throws ServletException, IOException  {
		  request.addParameter("filename","analisi1.pdf");
		  request.addParameter("filePath","/urNotes/images/richieste/100/");
		  request.addParameter("idFile","1");
		  servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	
	@Test
	  public void Scaricaget() throws ServletException, IOException  {
		request.addParameter("filename","");
	    request.addParameter("idFile","1");
	    servlet.doGet(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
}