package documenti;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import connection.ConnessioneDB;

/**
 * Servlet implementation class GetFormAggiungiIndirizzo
 */
@WebServlet("/GetFormAggiungiIndirizzo")
public class GetFormAggiungiIndirizzo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFormAggiungiIndirizzo() {
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
			
			String username = (String)request.getSession().getAttribute("nome_utente");
			Integer idUtente = (Integer) request.getSession().getAttribute("id_utente");
			
	        Integer risultato = 0;
	        String errore = "";
	        String contenuto = "";
	        
	        ConnessioneDB connDB = new ConnessioneDB();
			if(connDB.getConn() != null) {
				try {
					
					
					
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
			contenuto += "<input type='text' id='nome' class='nome adminFormField' name='nome' placeholder='Nome' />";
	        contenuto += "<input type='text' id='cognome' class='cognome adminFormField' name='cognome' placeholder='Cognome' />";
	        contenuto += "<input type='text' id='indirizzo' class='indirizzo adminFormField' name='indirizzo' placeholder='Indirizzo' />";
	        contenuto += "<input type='text' id='cap' class='cap adminFormField' name='cap' placeholder='Cap' />";	    
	        contenuto += "<input type='text' id='citta' class='citta adminFormField' name='citta' placeholder='Citt&agrave;' />";	   	        	        
	        contenuto += "<input type='text' id='provincia' class='provincia adminFormField' name='provincia' placeholder='Provincia' />";        	        
	        contenuto += "<input type='text' id='telefono' class='telefono adminFormField' name='telefono' placeholder='Telefono' />";
	        contenuto += "<input type='text' id='cellulare' class='cellulare adminFormField' name='cellulare' placeholder='Cellulare' />";

	        contenuto += "<button id='confirmAggiungiIndirizzo' class='adminButtonConfermaAggiungi'>Aggiungi</button>";
	        				
			JSONObject res = new JSONObject();
			res.put("risultato", risultato);
			res.put("errore", errore);
			res.put("contenuto", contenuto);
			out.println(res);			
		}
	}


}
