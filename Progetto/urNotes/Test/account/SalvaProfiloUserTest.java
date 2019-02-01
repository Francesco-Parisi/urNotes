package account;

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

import account.SalvaProfiloUser;

public class SalvaProfiloUserTest extends Mockito{

	 private SalvaProfiloUser servlet;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;

	  /**
	   * Before.
	   */
	  @Before
	  public void setUp() {
	    servlet = new SalvaProfiloUser();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	
	@Test
	  public void salvaUpdateNomeCognome() throws ServletException, IOException  {
	    request.addParameter("id_utente","1");
	    request.addParameter("tipo_utente","2");
	    request.addParameter("nome","Tiziano");
	    request.addParameter("cognome","La monica");
	    servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	
	@Test
	  public void salvaUpdateNomeCognomePassword() throws ServletException, IOException  {
		request.addParameter("id_utente","1");
	    request.addParameter("tipo_utente","1");
	    request.addParameter("nome","Tiziano");
	    request.addParameter("cognome","La monica");
	    request.addParameter("password","Lamonica97");
	    servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	
	@Test
	  public void salvaUtenteFail() throws ServletException, IOException  {
		request.addParameter("id_utente","1");
	    request.addParameter("tipo_utente","1");
	    request.addParameter("nome","");
	    request.addParameter("cognome","");
	    request.addParameter("password","");
	    servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	
	@Test
	  public void salvaUtenteFailPassword() throws ServletException, IOException  {
		request.addParameter("id_utente","1");
	    request.addParameter("tipo_utente","1");
	    request.addParameter("nome","Tiziano");
	    request.addParameter("cognome","La monica");
	    request.addParameter("password","");
	    servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	
	@Test
	  public void salvaUtenteDoGet() throws ServletException, IOException  {
		request.addParameter("id_utente","1");
	    request.addParameter("tipo_utente","1");
	    request.addParameter("nome","Tiziano");
	    request.addParameter("cognome","La monica");
	    request.addParameter("password","Lamonica97");
	    servlet.doGet(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
}
