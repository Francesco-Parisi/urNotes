package test;

import static org.junit.Assert.*;
import bean.Materia;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
public class MateriaTest {

  public Materia materia;
  public Materia materia2;
  

  @Test
  public void testgetMateriaName() {
    Materia m = new Materia("Geografia");
    assertEquals("Geografia", m.getNome());
  }

  @Test
  public void testsetMateriaName() {
	  Materia m = new Materia("Storia");
    m.setNome("Geografia");
    assertEquals("Geografia", m.getNome());
  }
  
}
