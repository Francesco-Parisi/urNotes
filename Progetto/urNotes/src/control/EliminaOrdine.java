package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.json.simple.JSONObject;

import model.ConnessioneDB;
/**
 * Servlet implementation class EliminaOrdine
 */
@WebServlet("/EliminaOrdine")
public class EliminaOrdine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaOrdine() {
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
		
		int serial_id = Integer.parseInt(request.getParameter("serial_id"));
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
						+ "SELECT o.serial_id, (SELECT nome FROM ordini_vettori WHERE id_vettore = o.id_vettore) AS vettore, o.data_ordine, "
						+ "(SELECT username FROM utenti WHERE id_utente = o.id_utente) AS cliente, "
						+ "(SELECT SUM(quantita) FROM ordini_documenti WHERE serial_id = o.serial_id) AS quantita_documenti, "
						+ "(SELECT email FROM utenti WHERE id_utente = o.id_utente) AS email, "
						+ "o.totale_ordine "
						+ "FROM ordini  AS o "
						+ "WHERE o.attivo = 1 "
						+ "ORDER BY o.data_ordine DESC, o.serial_id DESC; ";												
				
				if(continua == 1) {
					stmt0 = connDB.getConn().createStatement();
					String sql0 = "";
					sql0 = "UPDATE ordini_documenti SET attivo = 0 WHERE serial_id = "+serial_id+";";
					if(stmt0.executeUpdate(sql0) == 1) {
						Statement stmt1 = connDB.getConn().createStatement();
						sql = "UPDATE ordini SET attivo = 0 WHERE serial_id = "+serial_id+";";
						if(stmt1.executeUpdate(sql) == 1) {
							contenuto = "Ordine Eliminato con Successo";
							risultato = 1;			 		
						}
						else {
							errore = "Errore Cancellazione Ordine.";
							risultato = 0;					
						}
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
