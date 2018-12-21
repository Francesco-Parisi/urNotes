package bean;

public class OrdineBean {
	private int Codice;
	private String Data;
	private String Ora;
	private String Paese;
	private String Città;
	private int Cap;
	private String Indirizzo;
	private String Username_Studente;

	
	
	public OrdineBean(){
		Codice=10;
		Data="";
		Ora="";
		Paese="";
		Città="";
		Cap=0;
		Indirizzo="";
		Username_Studente="";
	}
	
	public OrdineBean(int Codice, String Data, String Ora, String Paese, String Città, int Cap, String Indirizzo, String Username_Studente){
		this.Codice=Codice;
		this.Data=Data;
		this.Ora=Ora;
		
		this.Indirizzo=Indirizzo;
		this.Username_Studente=Username_Studente;
	}

	public int getCodice() {
		return Codice;
	}

	public void setCodice(int Codice) {
		this.Codice=Codice;
	}

	public String getData() {
		return Data;
	}

	public void setData(String Data) {
		this.Data=Data;
	}

	public String getOra() {
		return Ora;
	}

	public void setOra(String Ora) {
		this.Ora=Ora;
	}
	
	public String getPaese() {
		return Paese;
	}
	
	public void setPaese(String Paese) {
		this.Paese=Paese;
	}
	
	public String getCitta() {
		return Città;
	}
	
	public void setCitta(String Città) {
		this.Città=Città;
	}
	
	
	public int getCap() {
		return Cap;
	}
	
	public void setCitta(int Cap) {
		this.Cap=Cap;
	}
	

	public String getIndirizzo() {
		return Indirizzo;
	}

	public void setIndirizzo(String Indirizzo) {
		this.Indirizzo=Indirizzo;
	}

	public String getUnStudente() {
		return Username_Studente;
	}

	public void setUnStudente(String Username_Studente) {
		this.Username_Studente=Username_Studente;
	}
	
	@Override
	public String toString() {
		return "OrdineBean [Codice=" + Codice + ", Data=" + Data + ", Ora=" + Ora +", Paese="+ Paese+ ", Città="+ ", Cap="+ Cap + ", Indirizzo="
				+ Indirizzo + ", Username Studente=" + Username_Studente + "]";
	}
}
