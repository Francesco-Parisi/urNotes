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

import account.SalvaProfiloAdmin;

public class SalvaProfiloAdminTest extends Mockito{

	 private SalvaProfiloAdmin servlet;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;

	  /**
	   * Before.
	   */
	  @Before
	  public void setUp() {
	    servlet = new SalvaProfiloAdmin();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	
	@Test
	  public void salvaUpdateNomeCognomeUsername() throws ServletException, IOException  {
	    request.addParameter("id_utente","3");
	    request.addParameter("tipo_utente","2");
	    request.addParameter("username","franpa96");
	    request.addParameter("nome","Francesco");
	    request.addParameter("cognome","Parisi");
	    servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	
	@Test
	  public void salvaUpdateNomeCognomeUsernamePassword() throws ServletException, IOException  {
		request.addParameter("id_utente","3");
	    request.addParameter("tipo_utente","1");
	    request.addParameter("username","franpa96");
	    request.addParameter("nome","Francesco");
	    request.addParameter("cognome","Parisi");
	    request.addParameter("password","admin2");
	    servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	
	@Test
	  public void salvaAdminFail() throws ServletException, IOException  {
		request.addParameter("id_utente","3");
	    request.addParameter("tipo_utente","1");
	    request.addParameter("username","franpa96");
	    request.addParameter("nome","");
	    request.addParameter("cognome","");
	    request.addParameter("password","");
	    servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	
	@Test
	  public void salvaAdminFailPassword() throws ServletException, IOException  {
		request.addParameter("id_utente","3");
	    request.addParameter("tipo_utente","1");
	    request.addParameter("username","franpa96");
	    request.addParameter("nome","Francesco");
	    request.addParameter("cognome","Parisi");
	    request.addParameter("password","+++");
	    servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	
	@Test
	  public void salvaAdminDoGet() throws ServletException, IOException  {
		request.addParameter("id_utente","3");
	    request.addParameter("tipo_utente","1");
	    request.addParameter("username","franpa96");
	    request.addParameter("nome","Francesco");
	    request.addParameter("cognome","Parisi");
	    request.addParameter("password","admin1");
	    servlet.doGet(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
}
