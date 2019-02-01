package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


import javax.servlet.http.HttpSession;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import model.Carrello;
import model.CheckSession;

public class CarrelloTest {

	@Test
	 public void testGetIdUtente() {
	    Carrello cart = new Carrello(2);
	    assertTrue(cart.getIdUtente() == 2);
	  }
	
	@Test
	 public void testSetIdUtente() {
		Carrello cart = new Carrello(2);
		cart.setIdUtente(1);
	    assertTrue(cart.getIdUtente() == 1);
	}	
	
	
	
	@Test
	 public void testGetDocumenti() {
		Carrello cart = new Carrello(2);
	    assertTrue(cart.getDocumenti() != null);
	}	
	
	@Test
	 public void testSetDocumenti() {
		Carrello cart = new Carrello(1);
	    assertTrue(cart.getDocumenti() != null);
	}
	
	
	@Test
	 public void testGetNumeroDocumenti() {
		Carrello cart = new Carrello(2);
	    assertTrue(cart.getNumeroDocumenti() == 0);
	}	
	
	@Test
	 public void testSetNumeroDocumenti() {
		Carrello cart = new Carrello(2);
	    assertTrue(cart.getNumeroDocumenti() == 0);
	}	
}