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
 * Servlet implementation class GetFormAggiungiMateria
 */ 
@WebServlet("/GetFormAggiungiMateria")
public class GetFormAggiungiMateria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFormAggiungiMateria() {
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
			contenuto += "<input type='text' id='nome' class='nome adminFormField' name='nome' placeholder='Materia' />";
			   
	        contenuto += "<button id='confirmAggiungiMateria' class='adminButtonConfermaAggiungi'>Aggiungi</button>";
	        risultato *= 1;
	
	        				
			JSONObject res = new JSONObject();
			res.put("risultato", risultato);
			res.put("errore", errore);
			res.put("contenuto", contenuto);
			out.println(res);			
		}
	}

}
