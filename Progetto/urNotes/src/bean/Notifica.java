package bean;

public class Notifica {

	private int ID;
	private String testo;
	private String tipo;
	private String studente;
	
	public Notifica() {
		ID=0;
		testo="";
		tipo="";
		studente="";
	}
	
	public Notifica(int unID, String unTesto, String unTipo, String unoStudente) {
		ID=unID;
		testo=unTesto;
		tipo=unTipo;
		studente=unoStudente;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getStudente() {
		return studente;
	}

	public void setStudente(String studente) {
		this.studente = studente;
	}


	public String toString() {
		return "NotificaBean [ID=" + ID + ", testo=" + testo + ", tipo=" + tipo + ", studente=" + studente + "]";
	}
	
	
	
}