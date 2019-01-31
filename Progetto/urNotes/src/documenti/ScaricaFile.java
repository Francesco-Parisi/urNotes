package documenti;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.json.simple.JSONObject;

import connection.ConnessioneDB;
import model.SystemInformation;



/**
 * Servlet implementation class ScaricaFile.
 */
@WebServlet("/ScaricaFile")
public class ScaricaFile extends HttpServlet {
  private static final long serialVersionUID = 1L;
  @SuppressWarnings("unused")
  private File file;

  /**
   * constructor.
   * 
   * @see HttpServlet#HttpServlet()
   */
  public ScaricaFile() {
	  super();
  	 

  	}
    // TODO Auto-generated constructor stub
@SuppressWarnings("unchecked")
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request, response);
	PrintWriter out = response.getWriter();
	response.setContentType("text/html");
	
	Integer risultato = 0;
	String contenuto = "";
	String errore="";
	String filename = "";
	String filePath = new SystemInformation().getPathFileDownload()+request.getSession().getAttribute("id_richiesta")+"\\";  
	Integer idFile = Integer.parseInt(request.getParameter("idFile"));
	
	ConnessioneDB connDB = new ConnessioneDB();
	if(connDB.getConn() != null) { 
	try {
	    	
		    Statement stmt = (Statement) connDB.getConn().createStatement();
	    	String sql = "";
	    	sql = ""
	    			+ "SELECT filename "
	    			+ "FROM richieste_file "
	    			+ "WHERE id_file = "+idFile+";";	
	    	ResultSet result = stmt.executeQuery(sql);	
	    	if(!result.wasNull()) {
	    		while(result.next()) {
	    			filename = result.getString("filename");
	    		}
	    	}
	    	
	    	risultato = 1;
			if(risultato == 0) {
				connDB.getConn().rollback();
			}
			else {
				connDB.getConn().commit();
			}																	
			connDB.getConn().close();
			
		}
		catch(Exception e) {
			errore = "Errore esecuzione Query.";
			risultato = 0;
		}
	}
	else {
		errore = connDB.getError();
		risultato = 0;
	}
	if ((new File(filePath+filename)).exists()) {

			Process p = Runtime
			   .getRuntime()
			   .exec("rundll32 url.dll,FileProtocolHandler "+filePath+filename);
			try {
				p.waitFor();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			contenuto = "Apertura file in corso";	
		} else {

			System.out.println("File is not exists");

		}

	
	JSONObject res = new JSONObject();
	res.put("risultato", risultato);
	res.put("errore", errore);
	res.put("contenuto", contenuto);
	out.println(res);
}
/**
 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
 */
@SuppressWarnings("unchecked")
public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
}
}
  

