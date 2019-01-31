package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit38ClassRunner;

import model.CheckSession;

public class CheckSessionTest {

	private CheckSession check;
	private CheckSession manager;
	
	 @Before
	    public void setUp() throws Exception {
		 check = new CheckSession(null, null);
		 check.setTipoCheck(0);
	        manager = new CheckSession(null,null);
	    }
	
	
	@Test
		public void setTipoCheck() throws Exception {
	        check.setTipoCheck(0);
	        Integer res = manager.getTipoCheck();
	        assertFalse(res);
	    }
	}
