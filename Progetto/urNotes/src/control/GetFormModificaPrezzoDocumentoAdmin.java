package control;

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

import model.ConnessioneDB;
import model.SystemInformation;

/**
 * Servlet implementation class GetFormModificaPrezzoDocumentoAdmin
 */
@WebServlet("/GetFormModificaPrezzoDocumentoAdmin")
public class GetFormModificaPrezzoDocumentoAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFormModificaPrezzoDocumentoAdmin() {
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
        
		Integer codice = Integer.parseInt(request.getParameter("codice"));
        Integer risultato = 0;
        String errore = "";
        String contenuto = "";
        
		if(codice > 0) {		
	        /*PRELEVO il prezzo ATTUALE*/
			Float prezzoDocumento = (float) 0;
	        ConnessioneDB connDB = new ConnessioneDB();
			if(connDB.getConn() != null) {
				
				try {
					Statement stmt = connDB.getConn().createStatement();
					String sql = "";
					sql = ""
							+ "SELECT prezzo "
							+ "FROM documenti "
							+ "WHERE codice = "+codice+"; ";
					ResultSet result = stmt.executeQuery(sql);				
					if(!result.wasNull()) {
						while(result.next()) {
							prezzoDocumento = result.getFloat("prezzo");
						}												
					}	
					
					if(risultato == 0) {
						connDB.getConn().rollback();
					}
					else {
						connDB.getConn().commit();
					}																						
					connDB.getConn().close();
					risultato = 1;
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
	        	        	        	       
			contenuto += "<input type='number' step='0.01' id='prezzoDocumento' class='prezzoDocumento adminFormField' name='prezzoDocumento' value='"+new SystemInformation().truncateDecimal(prezzoDocumento,2)+"' />";	        	        	        
	        contenuto += "<input type='hidden' id='codice' value='"+codice+"' />";
	        contenuto += "<button id='confirmAggiungiPrezzoDocumento' class='adminButtonConfermaAggiungi'>Aggiorna Prezzo</button>";
	        risultato = 1;

	        				
		}
		else {
			errore = "Errore Parametri";
			risultato = 0;			
		}

		JSONObject res = new JSONObject();
		res.put("risultato", risultato);
		res.put("errore", errore);
		res.put("contenuto", contenuto);
		out.println(res);			
	}

}
