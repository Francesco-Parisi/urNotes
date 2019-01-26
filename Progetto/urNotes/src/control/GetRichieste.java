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
 * Servlet implementation class GetRichieste
 */
@WebServlet("/GetRichieste")
public class GetRichieste extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetRichieste() {
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
	    
		Integer risultato = 0;
	    String errore = "";
	    String contenuto = "";
	    
        ConnessioneDB connDB = new ConnessioneDB();
		if(connDB.getConn() != null) {
			
			try {
				Statement stmt = connDB.getConn().createStatement();
				String sql = "";
				sql = ""
						+ "SELECT r.id_richiesta, r.titolo, r.pagine, r.nome_materia, r.universita, r.descrizione, r.tipo, r.data_richiesta, IFNULL((SELECT username FROM utenti WHERE id_utente = r.id_utente), '') AS studente "
						+ "FROM richieste AS r "
						+ "WHERE r.attivo = 1; ";	
				//System.out.println(sql);
				ResultSet result = stmt.executeQuery(sql);				
				if(!result.wasNull()) {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					String cl = "";
					while(result.next()) {
						 contenuto += "<tr class='richieste'>";
							contenuto += "<td>"+sdf.format(result.getDate("data_richiesta"))+"</td>";	
							contenuto += "<td>"+result.getString("studente")+"</td>";
							contenuto += "<td>"+result.getString("titolo")+"</td>";							
							contenuto += "<td>"+result.getInt("pagine")+"</td>";							
							contenuto += "<td>"+result.getString("nome_materia")+"</td>";							
							contenuto += "<td>"+result.getString("universita")+"</td>";							
							contenuto += "<td>"+result.getString("descrizione")+"</td>";							
							contenuto += "<td>"+result.getString("tipo")+"</td>";							
							contenuto += "<td>";
							contenuto += "	<i class='aggiungiRichiesta fas fa-book-open' style='cursor: pointer;' data-id_richiesta='"+result.getInt("id_richiesta")+"' title='Visualizza Richiesta'></i>";

							contenuto += "	<i class='eliminaRichiesta fas fa-times' style='cursor: pointer;' data-id_richiesta='"+result.getInt("id_richiesta")+"' title='Elimina Richiesta'></i>";
							contenuto += "</td>";
						contenuto += "</tr>";
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
	
		
		JSONObject res = new JSONObject();
		res.put("risultato", risultato);
		res.put("errore", errore);
		res.put("contenuto", contenuto);
		out.println(res);		
	}

}
