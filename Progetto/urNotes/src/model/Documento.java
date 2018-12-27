package model;

public class Documento {
	public Documento(Integer Codice, Integer quantita) {
		this.setCodice(Codice);
		this.setQuantita(quantita);
	}
	public Integer getQuantita() {		
		return this.quantita;
	}
	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}
	public Integer getCodice() {
		return this.Codice;
	}
	public void setCodice(Integer Codice) {
		this.Codice = Codice;
	}
	
	public String toString() {
		return this.getClass().getName()+"["+this.getCodice()+"]["+this.getQuantita()+"]";
	}

	private Integer Codice;
	private Integer quantita;
}
