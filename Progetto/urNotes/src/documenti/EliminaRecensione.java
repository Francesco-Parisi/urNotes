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
 * Servlet implementation class EliminaRecensione
 */
@WebServlet("/EliminaRecensione")
public class EliminaRecensione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaRecensione() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
	    Integer id_recensione = Integer.parseInt(request.getParameter("id_recensione"));
        Integer risultato = 0;
        String errore = "";
        String contenuto = "";
        
        ConnessioneDB connDB = new ConnessioneDB();
		if(connDB.getConn() != null) {
			try {
				Integer continua = 1;
				
				Statement stmt0 = connDB.getConn().createStatement();
				String sql = "";
				sql = ""
						+ "SELECT r.id_recensione "
						+ "FROM recensioni AS r "
						+ "WHERE r.flag = 1 AND r.id_recensione= '"+id_recensione+"';";												
				
				if(continua == 1) {
					//Cancello la materia
					sql = "UPDATE recensioni SET flag = 0 WHERE id_recensione= '"+id_recensione+"';";
					if(stmt0.executeUpdate(sql) == 1) {					
						contenuto = "Recensione Eliminata con Successo";
						risultato = 1;			
					}
					else {
						errore = "Errore Cancellazione Recensione.";
						risultato = 0;					
					}
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
