package documenti;

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
	    
		Integer id_utente = (int) request.getSession().getAttribute("id_utente");

		
		Integer risultato = 0;
	    String errore = "";
	    String contenuto = "";
	    
	    
	    
        ConnessioneDB connDB = new ConnessioneDB();
		if(connDB.getConn() != null) {
			try {
				Statement stmt = connDB.getConn().createStatement();
				String sql = "";
				sql = ""
						+ "SELECT r.id_richiesta, r.titolo, r.pagine, r.nome_materia, r.universita, r.descrizione, r.tipo, r.data_richiesta "
						+ "FROM richieste AS r "
						+ "WHERE r.attivo = 1 AND id_utente="+id_utente+"; ";
				//System.out.println(sql);
				ResultSet result = stmt.executeQuery(sql);	
				
				if(!result.wasNull()) {
					
					while(result.next()) {
						contenuto += "<tr>";
						contenuto += "<td>"+result.getString("id_richiesta")+"</td>";
						contenuto += "<td>"+result.getString("data_richiesta")+"</td>";		
						contenuto += "<td>"+result.getString("titolo")+"</td>";		
						contenuto += "<td>"+result.getInt("pagine")+"</td>";
						contenuto += "<td>"+result.getString("nome_materia")+"</td>";
						contenuto += "<td>"+result.getString("universita")+"</td>";		
						contenuto += "<td>"+result.getString("descrizione")+"</td>";		
						contenuto += "<td>"+result.getString("tipo")+"</td>";		
						contenuto += "<td>";
						contenuto += "	<i class='aggiungiRichiesta fas fa-book-open' style='cursor: pointer;' data-id_richiesta='"+result.getInt("id_richiesta")+"' title='Aggiungi Richiesta'></i>";
						contenuto += "</td>";
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