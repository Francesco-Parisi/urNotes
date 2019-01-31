package test;

import connection.ConnessioneDB;
import bean.Materia;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.*;
public class ConnessioneDBTest {

  //Test Metodi GET
  
  @Test
  public void testgetDatabaseName() {
    ConnessioneDB db = new ConnessioneDB();
    assertEquals("appunti", db.getDBname());
  }

  @Test
  public void testgetUserName() {
	  ConnessioneDB db = new ConnessioneDB();
    assertEquals("root", db.getUserDB());
  }

  @Test
  public void testgetPassword() {
	  ConnessioneDB db = new ConnessioneDB();
    assertEquals("fr6108pr", db.getPassDB());
  }

  @Test
  public void testgetHostPort() {
	  ConnessioneDB db = new ConnessioneDB();
    assertEquals(3306, db.getHostPort());
  }

  @Test
  public void testgetHostName() {
	  ConnessioneDB db = new ConnessioneDB();
    assertEquals("localhost", db.getServer());
  }

  @Test
  public void testgetConn() {
	  ConnessioneDB db = new ConnessioneDB();
    assertNotEquals(null, db.getConn());
  }



  //Test Metodi SET
  
  @Test
  public void testsetDatabaseName() {
	  ConnessioneDB db = new ConnessioneDB();
    db.setUserDB("root");
    assertEquals("root", db.getUserDB());
  }

  @Test
  public void testsetUserName() {
	  ConnessioneDB db = new ConnessioneDB();
    db.setDBname("appunti");
    assertEquals("appunti", db.getDBname());
  }

  @Test
  public void testsetPassword() {
	  ConnessioneDB db = new ConnessioneDB();
    db.setPassDB("fr6108pr");
    assertEquals("fr6108pr", db.getPassDB());
  }

  @Test
  public void testsetHostPort() {
	  ConnessioneDB db = new ConnessioneDB();
    db.setHostPort(3306);
    assertEquals(3306, db.getHostPort());
  }

  @Test
  public void testsetHostName() {
	  ConnessioneDB db = new ConnessioneDB();
    db.setServer("localhost");
    assertEquals("localhost", db.getServer());
  }

  @Test
  public void testsetConn() throws Exception {
    new ConnessioneDB().setConn(null);
  }
}
