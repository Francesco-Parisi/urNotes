package model;

import java.util.ArrayList;

public class Carrello {
	public Carrello(Integer id_utente) {
		this.documenti = new ArrayList<Documento>();	
		this.setIdUtente(id_utente);
	}
	
	public ArrayList<Documento> getDocumenti(){
		return this.documenti;
	}
	
	public int getNumeroDocumenti() {
		int i = 0;
		for(Documento documento: this.documenti) {
		    i += documento.getQuantita();
		}
		return i;
	}
	
	public Documento getDocumento(Integer i) {		
		if(this.documenti.get(i) != null) return this.documenti.get(i);
		else return null;
	}

	public Documento getDocumentoByCodiceDocumento(Integer Codice) {
		for(Documento documento: this.documenti) {
		    if(documento.getCodice() == Codice) {
		    	return documento;
		    }
		}
		return null;
	}

	public void setDocumento(Integer Codice, Integer quantita) {
		Documento d = new Documento(Codice, quantita);
		this.documenti.add(d);
	}
	
	public void delDocumento(Integer Codice) {
		int i = 0;
		for(Documento documento: this.getDocumenti()) {
		    if(documento.getCodice()== Codice) {
		    	this.getDocumenti().remove(i);
		    	return;
		    }
		    i++;
		}
	}
	
	public void modQuantDocumento(Integer Codice, Integer quantita) {
		for(Documento documento: this.documenti) {
		    if(documento.getCodice() == Codice) {
		    	documento.setQuantita(quantita);
		    }
		}
	}

	public Integer getIdUtente() {
		return id_utente;
	}

	public void setIdUtente(Integer id_utente) {
		this.id_utente = id_utente;
	}
	
	public String toString() {
		String documenti = "";
		for(Documento documento: this.documenti) {
			documenti += "["+documento.toString()+"]";
		}
		return this.getClass().getName()+"["+this.getIdUtente()+"]["+documenti+"]";
	}

	private ArrayList<Documento> documenti;
	private Integer id_utente;
}
