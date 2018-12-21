package bean;

public class StudenteBean {
private static final long serialVersionUID = 1L;
	
	private String Nome;
	private String Cognome;
	private String Email;
	private String Username;
	private String Password;
	private int admin;
	

	public StudenteBean(){
		Nome="";
		Cognome="";
		Email="";
		Username="";
		Password="";
		admin=0;
	}

	public StudenteBean(String Nome, String Cognome, String Email, String Username, String Password){
        this.Nome=Nome;
        this.Cognome=Cognome;
		this.Email=Email;
		this.Username=Username;
		this.Password=Password;
		this.admin=0;
	}

	public String getNome() {
		return 	Nome;
	}

	public void setNome(String Nome) {
		this.Nome = Nome;
	}

	public String getCognome() {
		return Cognome;
	}

	public void setCognome(String Cognome) {
		this.Cognome = Cognome;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String Email) {
		this.Email = Email;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String Username) {
		this.Username = Username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String Password) {
		this.Password = Password;
	}
	
	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ClienteBean [Nome="+ Nome + ", Cognome=" + Cognome + ", Email=" + Email +", Username="
				+ Username + ", Password=" + Password +", admin=" + admin + "]";
	}
	

}

