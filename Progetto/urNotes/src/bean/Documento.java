package bean;

public class Documento {
	public Documento(Integer codice, Integer quantita) {
		this.setCodice(codice);
		this.setQuantita(quantita);
	}
	public Integer getQuantita() {		
		return this.quantita;
	}
	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}
	public Integer getCodice() {
		return this.codice;
	}
	public void setCodice(Integer codice) {
		this.codice = codice;
	}
	
	public String toString() {
		return this.getClass().getName()+"["+this.getCodice()+"]["+this.getQuantita()+"]";
	}

	private Integer codice;
	private Integer quantita;
}
