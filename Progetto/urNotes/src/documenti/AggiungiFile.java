package documenti;

import java.io.IOException; 
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.json.simple.JSONObject;

import connection.ConnessioneDB;
/**
 * Servlet implementation class AggiungiFile
 */
@WebServlet("/AggiungiFile")
public class AggiungiFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		Integer id_richiesta = Integer.parseInt(request.getParameter("id_richiesta"));
		String filenameFileRichiesta = request.getParameter("filenameFileRichiesta");

        Integer risultato = 0;        
        String errore = "";
        String contenuto = "";
        
        Integer continua = 0;
        
        ConnessioneDB connDB = new ConnessioneDB();
		if(connDB.getConn() != null) {
			try {				
				String sql = "";
			
					sql = "INSERT INTO richieste_file (id_richiesta, filename, attivo) VALUES (?, ?, ?) ;";
					PreparedStatement  stmt = connDB.getConn().prepareStatement(sql);
					stmt.setInt(1, id_richiesta);
					stmt.setString(2, filenameFileRichiesta);
					stmt.setInt(3, 1);				
					if(stmt.executeUpdate() == 1) {
						contenuto = "File Inserito con Successo";
						risultato = 1;					
					}
					else {
						errore = "Errore Inserimento File.";
						risultato = 0;					
					}															
				
				
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
        
        				
		JSONObject res = new JSONObject();
		res.put("risultato", risultato);
		res.put("errore", errore);
		res.put("contenuto", contenuto);
		out.println(res);
	}

}
