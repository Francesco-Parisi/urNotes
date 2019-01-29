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
/**
 * Servlet implementation class GetFormAggiungiRichiesta
 */ 
@WebServlet("/GetFormAggiungiRichiesta")
public class GetFormAggiungiRichiesta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFormAggiungiRichiesta() {
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
	        Integer risultato = 1;
	        String errore = "";
	        String contenuto = "";
	        
	        
	        /*PRELEVO LE MATERIE*/
	        String materie = "";
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
							+ "SELECT codice, nome "
							+ "FROM materie "
							+ "WHERE flag = 1; ";
					result = stmt.executeQuery(sql);				
					if(!result.wasNull()) {
						int rowCount = result.last() ? result.getRow() : 0;
						if(rowCount > 0) {
							materie += "<option value='0'>Selezionare una Materia</option>";
							result.beforeFirst();
							while(result.next()) {
								materie += "<option value='"+result.getString("nome")+"'>"+result.getString("nome")+"</option>";
							}												
						}
						else {
							materie += "<option value='0'>Nessuna Materia Esistente</option>";
						}
					}		
					else {
						risultato *= 0;
					}
					
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
					errore = "Errore esecuzione Query Materie.";
					risultato *= 0;
				}
						
			}
			else {
				errore = connDB.getError();
				risultato *= 0;
			}						        
			contenuto += "<input type='text' id='titolo' class='titolo adminFormField' name='titolo' placeholder='Titolo' />";
			contenuto += "<input type='number' id='pagine' class='pagine adminFormField' name='pagine' placeholder='Pagine' />";
	        contenuto += "<select id='nome_materia' class='nomeMateria adminFormField' name='materie'>";
	        contenuto += materie;
	        contenuto += "</select>";	
			contenuto += "<input type='text' id='universita' class='universita adminFormField' name='universita' placeholder='Universit&agrave;' />";
	        contenuto += "<input type='text' id='descrizione' class='descrizione adminFormField' name='descrizione' placeholder='Descrizione' />";	     	        
	        contenuto += "<select id='tipo' class='tipo adminFormField' name='tipo'>";
	        contenuto += "<option value='0'>Selezionare un tipo</option>";
	        contenuto += "<option value='1'>Appunti</option>";
	        contenuto += "<option value='2'>Dispense</option>";
	        contenuto += "</select>";		        
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
