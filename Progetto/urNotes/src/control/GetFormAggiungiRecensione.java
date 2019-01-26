package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.json.simple.JSONObject;

import model.ConnessioneDB;
/**
 * Servlet implementation class GetFormAggiungiRecensione
 */ 
@WebServlet("/GetFormAggiungiRecensione")
public class GetFormAggiungiRecensione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFormAggiungiRecensione() {
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
        
		if(Integer.parseInt(request.getParameter("richiesta"))  == 1) {		
			Integer id_utente = 0;			
			if(request.getSession().getAttribute("id_utente") != null) {
				id_utente = (Integer) request.getSession().getAttribute("id_utente");
			}	
	        Integer risultato = 1;
	        String errore = "";
	        String contenuto = "";
	        
	        
	        /*PRELEVO LE MATERIE*/
	        String recensione = "";
	        Statement stmt;
	        String sql;
	        ResultSet result;
	        ConnessioneDB connDB = new ConnessioneDB();
			if(connDB.getConn() != null) {			
				try {
					//Materie
					stmt = connDB.getConn().createStatement();
					sql = "";
					sql = ""
							+ "SELECT username "
							+ "FROM utenti "
							+ "WHERE attivo = 1 AND id_utente="+id_utente+"; ";
					result = stmt.executeQuery(sql);				
					
					
					risultato *= 1;
					
					if(risultato == 0) {
						connDB.getConn().rollback();
					}
					else {
						connDB.getConn().commit();
					}																	
					connDB.getConn().close();
					
				}
				catch(Exception e) {
					errore = "Errore esecuzione Query Utente.";
					risultato *= 0;
				}
						
			}
			else {
				errore = connDB.getError();
				risultato *= 0;
			}		
			contenuto += "<input type='text' id='descrizione' class='descrizione adminFormField' name='descrizione' placeholder='Valuta il Documento...' />";
			   
	        contenuto += "<button id='confirmAggiungiRichiesta' class='adminButtonConfermaAggiungi'>Aggiungi</button>";
	        risultato *= 1;
	
	        				
			JSONObject res = new JSONObject();
			res.put("risultato", risultato);
			res.put("errore", errore);
			res.put("contenuto", contenuto);
			out.println(res);			
		}
	}

}
