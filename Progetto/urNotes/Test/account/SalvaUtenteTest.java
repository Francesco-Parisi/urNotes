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

import model.MD5;
import account.SalvaUtente;

public class SalvaUtenteTest extends Mockito{

	 private SalvaUtente servlet;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;

	  /**
	   * Before.
	   */
	  @Before
	  public void setUp() {
	    servlet = new SalvaUtente();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	
	@Test
	  public void registrazioneUser() throws ServletException, IOException  {
	    request.addParameter("username","Lamonica9741");
	    request.addParameter("nome","Tiziano");
	    request.addParameter("cognome","La monica");
	    request.addParameter("email","lamonica1@gmail.com");
	    request.addParameter("password",MD5.crypt("070ED0CA070A7B4C1AA84BCB0979B9AE"));
	    
	    servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	
	@Test
	  public void registrazioneFailCampiVuoti() throws ServletException, IOException  {
		request.addParameter("username","");
	    request.addParameter("nome","");
	    request.addParameter("cognome","");
	    request.addParameter("email","");
	    request.addParameter("password","");
	    servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	@Test
	  public void registrazioneFailPassw() throws ServletException, IOException  {
		request.addParameter("username","Lamonica97");
	    request.addParameter("nome","Tiziano");
	    request.addParameter("cognome","La monica");
	    request.addParameter("email","lamonica@gmail.com");
	    request.addParameter("password","");
	    servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	@Test
	  public void registrazioneFailUsername() throws ServletException, IOException  {
		request.addParameter("username","Franpa96");
	    request.addParameter("nome","Marco");
	    request.addParameter("cognome","La cortiglia");
	    request.addParameter("email","lacortiglia@gmail.com");
	    request.addParameter("password",MD5.crypt("070ED0CA070A7B4C1AA84BCB0979B9AE"));

	    servlet.doPost(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
	
	@Test
	  public void registrazioneDoGet() throws ServletException, IOException  {
	    request.addParameter("username","Gio4696");
	    request.addParameter("nome","Giovanni");
	    request.addParameter("cognome","Marottoli");
	    request.addParameter("email","gio@gmail.com");
	    request.addParameter("password",MD5.crypt("070ED0CA070A7B4C1AA84BCB0979B9AE"));
	    servlet.doGet(request, response);
	    assertEquals("text/html", response.getContentType());
	  }
}
