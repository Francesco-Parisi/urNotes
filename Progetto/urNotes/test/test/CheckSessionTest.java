package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;


import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import model.CheckSession;

public class CheckSessionTest {
	
	@Test
	  void testGetSession() {
	    MockHttpServletRequest request = new MockHttpServletRequest();
	    HttpSession session = request.getSession();
	    CheckSession check = new CheckSession(0,session);
	    assertEquals(session, check.getSession());
	  }
	
	
	@Test
	  void testSetSession() {
	    MockHttpServletRequest request = new MockHttpServletRequest();
	    HttpSession session = request.getSession();
	    CheckSession check = new CheckSession(0,session);
	    HttpSession session1 = request.getSession();
	    check.setSession(session1);
	    assertEquals(session1, check.getSession());
	  }
	
	
	@Test
	  void testGetUrlRedirect() {
	    MockHttpServletRequest request = new MockHttpServletRequest();
	    HttpSession session = request.getSession();
	    CheckSession check = new CheckSession(0,session);
	    assertEquals("/index.jsp", check.getUrlRedirect());
	  }
	  
	  @Test
	  void testSetUrlRedirect() {
	    MockHttpServletRequest request = new MockHttpServletRequest();
	    HttpSession session = request.getSession();
	    CheckSession check = new CheckSession(0,session);
	    check.setUrlRedirect("/logout.jsp");
	    assertEquals("/logout.jsp", check.getUrlRedirect());
	  }
	  
	
	  
	
}