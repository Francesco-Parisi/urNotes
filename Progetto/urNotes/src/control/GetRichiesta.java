package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.json.simple.JSONObject;

import model.ConnessioneDB;
/**
 * Servlet implementation class GetRichiesta
 */
@WebServlet("/GetRichiesta")
public class GetRichiesta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetRichiesta() {
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
		Integer id_richiesta = Integer.parseInt(request.getParameter("id_richiesta"));
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
	    
		Integer risultato = 0;
	    String errore = "";
	    String contenuto = "";
	    
        ConnessioneDB connDB = new ConnessioneDB();
		if(connDB.getConn() != null) {
			
			try {
				String sql = "";
				Statement stmt0 = connDB.getConn().createStatement();				
				sql = "UPDATE richieste SET letta = 1 WHERE id_richiesta = "+id_richiesta+";";
				if(stmt0.executeUpdate(sql) == 1) {
					Statement stmt = connDB.getConn().createStatement();				
					sql = ""
						+ "SELECT r.id_richiesta, r.titolo, r.pagine, r.nome_materia, r.universita, r.descrizione, r.tipo, r.data_richiesta, r.letta, IFNULL((SELECT username FROM utenti WHERE id_utente = r.id_utente), '') AS studente "
						+ "FROM richieste AS r "
						+ "WHERE r.attivo = 1 AND r.id_richiesta = "+id_richiesta+"; ";				
					//System.out.println(sql);
					ResultSet result = stmt.executeQuery(sql);				
					if(!result.wasNull()) {
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						while(result.next()) {
							contenuto += "<h2>Richiesta N."+result.getString("id_richiesta")+"</h2>";
							contenuto += "<p class='fieldContatto'>Data: "+sdf.format(result.getDate("data_richiesta"))+"</p>";
							contenuto += "<p class='fieldContatto'>Studente: "+result.getString("studente")+"</p>";
							contenuto += "<p class='fieldContatto'>Titolo: "+result.getString("titolo")+"</p>";
							contenuto += "<p class='fieldContatto'>Pagine: "+result.getInt("pagine")+"</p>";
							contenuto += "<p class='fieldContatto'>Materia: "+result.getString("nome_materia")+"</p>";
							contenuto += "<p class='fieldContatto'>Università: "+result.getString("universita")+"</p>";
							contenuto += "<p class='fieldContatto'>Descrizione: "+result.getString("descrizione")+"</p>";
							contenuto += "<p class='fieldContatto'>Tipo: "+result.getString("tipo")+"</p>";
						}
						risultato = 1;
					}				 
				}
				else {
					errore = "Errore Aggiornamento Lettura.";
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
