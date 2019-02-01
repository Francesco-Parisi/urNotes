package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


import javax.servlet.http.HttpSession;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import model.CheckSession;

public class CheckSessionTest {

	@Test
	 public void testSetSession1() {
	    MockHttpServletRequest request = new MockHttpServletRequest();
	    HttpSession session = request.getSession();
	    CheckSession check = new CheckSession(1,session);
	    HttpSession session1 = request.getSession();
	    check.setSession(session1);
	    assertTrue(check.getSession().equals(session1));
	  }
	
	@Test
	 public void testSetSession2() {
		    MockHttpServletRequest request = new MockHttpServletRequest();
		    HttpSession session = request.getSession();
		    CheckSession check = new CheckSession(0,session);
		    HttpSession session1 = request.getSession();
		    check.setSession(session1);
		    assertTrue(check.getSession().equals(session1));
		  }
	
	@Test
	 public void testGetSession1() {
	    MockHttpServletRequest request = new MockHttpServletRequest();
	    HttpSession session = request.getSession();
	    CheckSession check = new CheckSession(1,session);
	    assertTrue(check.getSession().equals(session));
	  }
	
	@Test
	 public void testGetSession2() {
	    MockHttpServletRequest request = new MockHttpServletRequest();
	    HttpSession session = request.getSession();
	    CheckSession check = new CheckSession(0,session);
	    assertTrue(check.getSession().equals(session));
	  }
	
	
	
	
	@Test
	 public void testGetUrlRedirect1() {
	    MockHttpServletRequest request = new MockHttpServletRequest();
	    HttpSession session = request.getSession();
	    CheckSession check = new CheckSession(1,session);
	    String redirect = "/accedi.jsp";
	    assertTrue(check.getUrlRedirect().equals(redirect));
	  }
	
	@Test
	 public void testGetUrlRedirect2() {
	    MockHttpServletRequest request = new MockHttpServletRequest();
	    HttpSession session = request.getSession();
	    CheckSession check = new CheckSession(0,session);
	    String redirect = "/accedi.jsp";
	    assertTrue(check.getUrlRedirect().equals(redirect));
	  }
	  
	  @Test
	 public void testSetUrlRedirect1() {
	    MockHttpServletRequest request = new MockHttpServletRequest();
	    HttpSession session = request.getSession();
	    CheckSession check = new CheckSession(1,session);
	    check.setUrlRedirect("/logout.jsp");
	    assertTrue(check.getUrlRedirect().equals("/logout.jsp"));
	  }
	  @Test
		 public void testSetUrlRedirect2() {
		    MockHttpServletRequest request = new MockHttpServletRequest();
		    HttpSession session = request.getSession();
		    CheckSession check = new CheckSession(0,session);
		    check.setUrlRedirect("/logout.jsp");
		    assertTrue(check.getUrlRedirect().equals("/logout.jsp"));
		  }
	  
	  @Test
	  public void testGetTipoCheck1() {
		  MockHttpServletRequest request = new MockHttpServletRequest();
		    HttpSession session = request.getSession();
		    CheckSession check = new CheckSession(1,session);
		    assertTrue(check.getTipoCheck() == 1);
	  }
	  
	  @Test
	  public void testGetTipoCheck2() {
		  MockHttpServletRequest request = new MockHttpServletRequest();
		    HttpSession session = request.getSession();
		    CheckSession check = new CheckSession(0,session);
		    assertTrue(check.getTipoCheck() == 0);
	  }
	  
	  @Test
		 public void testGetRedirect1() {
		    MockHttpServletRequest request = new MockHttpServletRequest();
		    HttpSession session = request.getSession();
		    CheckSession check = new CheckSession(1,session);
		    assertTrue(check.getRedirect());
		  }
	  @Test
		 public void testGetRedirect2() {
		    MockHttpServletRequest request = new MockHttpServletRequest();
		    HttpSession session = request.getSession();
		    CheckSession check = new CheckSession(0,session);
		    assertTrue(check.getRedirect());
		  }
		  
		  @Test
		 public void testSetRedirect1() {
		    MockHttpServletRequest request = new MockHttpServletRequest();
		    HttpSession session = request.getSession();
		    CheckSession check = new CheckSession(1,session);
		    check.setRedirect(true);
		    assertTrue(check.getRedirect());
		  }
		  @Test
			 public void testSetRedirect2() {
			    MockHttpServletRequest request = new MockHttpServletRequest();
			    HttpSession session = request.getSession();
			    CheckSession check = new CheckSession(0,session);
			    check.setRedirect(true);
			    assertTrue(check.getRedirect());
			  }

		@Test
			 public void testSetUrlRedirect() {
			    MockHttpServletRequest request = new MockHttpServletRequest();
			    HttpSession session = request.getSession();
			    CheckSession check = new CheckSession(0,session);
			    check.setUrlRedirect("/logout.jsp");
			    assertTrue(check.getUrlRedirect().equals("/logout.jsp"));
			  }
	
}