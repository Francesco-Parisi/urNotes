package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import bean.Documento;
import bean.Materia;

public class DocumentoTest {
  
  @Test
  public void testgetDocumentoCodice() {
	 Documento d = new Documento(1040,5);
     assertTrue(d.getCodice()==1040);
  }
  
  @Test
  public void testgetDocumentoQuantita() {
	 Documento d = new Documento(1040,5);
     assertTrue(d.getQuantita()==5);
  }
  
  @Test
  public void testgetString() {
	 Documento d = new Documento(1040,5);
     assertTrue(d.toString()!=null);
  }
  
  @Test
  public void testsetDocumentoCodice() {
	  Documento d = new Documento(1040,5);
	  d.setCodice(1041);
	   assertTrue(d.getCodice()==1041);
  }
  
  @Test
  public void testsetDocumentoQuantita() {
	  Documento d = new Documento(1040,5);
	  d.setQuantita(12);
	   assertTrue(d.getQuantita()==12);
  }
  
}
