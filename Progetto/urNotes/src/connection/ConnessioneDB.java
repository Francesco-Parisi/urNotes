package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnessioneDB {
	
	public ConnessioneDB(){
		this.conn = null;
		this.server = "localhost";
		this.porta  = 3306;
		this.nomeDB = "appunti";
		this.userDB = "root";
		this.passDB = "fr6108pr";	
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+this.server+":"+this.porta+"/"+this.nomeDB+"?useSSL=false";
			this.conn = DriverManager.getConnection(url, this.userDB, this.passDB);
			this.conn.setAutoCommit(false);
		}
		catch(Exception exc) {
			this.error = "Connessione Fallita \n"+exc.getMessage();
		}  
	}
	
	public Connection getConn(){
		return this.conn;
	}
	
	public void setConn(Connection conn){
		this.conn = conn ;
	}
	
	public String getDBname(){
		return this.nomeDB;
	}
	
	public void setDBname(String nomeDB){
		this.nomeDB = nomeDB;
	}
	
	public String getUserDB(){
		return this.userDB;
	}
	
	public void setUserDB(String userDB){
		this.userDB = userDB;
	}
	
	public String getPassDB(){
		return this.passDB;
	}
	
	public void setPassDB(String passDB){
		this.passDB = passDB;
	}
	
	public int getHostPort(){
		return this.porta;
	}
	
	public void setHostPort(int porta){
		this.porta = porta;
	}
	
	public String getServer(){
		return this.server;
	}
	
	public void setServer(String server){
		this.server = server;
	}
	
	public String getError(){
		return this.error;
	}
	
	
	public void closeConn() throws SQLException{
		this.conn.close();
	}
	
	private Connection conn;
	private String server;
	private int porta;
	private String nomeDB;
	private String userDB;
	private String passDB;
	private String error;
}
