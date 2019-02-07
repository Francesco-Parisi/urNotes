package documenti;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import bean.Documento;
import connection.ConnessioneDB;
import model.Carrello;
import model.SystemInformation;

/**
 * Servlet implementation class GetCarrello
 */
@WebServlet("/GetCarrello")
public class GetCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCarrello() {
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
	    
		Integer risultato = 1;
	    String errore = "";
	    String contenuto = "";

    	if(request.getSession() != null){
    		Integer id_utente = (Integer) request.getSession().getAttribute("id_utente");    		
    		Carrello cart = (Carrello) request.getSession().getAttribute("carrello");
    		if(id_utente != null && cart != null){
    			String sql;
    			Statement stmt;
    			ResultSet result;
    			Integer codice;
    			Integer quantitaDocumento;
    			
    	        ConnessioneDB connDB = new ConnessioneDB();
    			if(connDB.getConn() != null) {
	    			for(Documento documento: cart.getDocumenti()) { //Per ogni documento del carrello
	    				try {
		    				sql = "";
		    				stmt = null;
		    				result = null;		    				
		    				codice = documento.getCodice();
		    				quantitaDocumento = documento.getQuantita();
	    					stmt = connDB.getConn().createStatement();
		    				sql = ""
									+ "SELECT d.titolo, d.prezzo, "
									+ "(SELECT filename FROM documenti_immagini WHERE codice = d.codice AND is_default = 1 AND attivo = 1) AS filename "
									+ "FROM documenti  AS d "
									+ "WHERE d.flag = 1 AND d.codice = "+codice+"; ";
	    					//System.out.println(sql);
	    					result = stmt.executeQuery(sql);				
	    					if(!result.wasNull()) {
	    						while(result.next()) {
	    							String filename;
	    							if(result.getString("filename") != null){
	    								filename = new SystemInformation().getPathImmaginiDocumentoHTML()+codice+"/"+result.getString("filename");												
	    							}
	    							else{
	    								filename = new SystemInformation().getPathImmaginiDocumentoDefault();												
	    							}	    							
	    							
	    							contenuto += "<tr id=container_carrello>";
	    								contenuto += "<td><img class='showImmagineDocumento' src='"+filename+"' alt='"+filename+"' /></td>";					
	    								contenuto += "<td>"+result.getString("titolo")+"</td>";		
	    								contenuto += "<td>";
	    								contenuto += new SystemInformation().truncateDecimal(result.getFloat("prezzo"),2);							
	    								contenuto += "€"+"</td>";	
	    								contenuto += "<td><i class='fas fa-minus rimuoviQuantitaDocumentoCarrello' data-codice='"+codice+"'></i>&nbsp;&nbsp;"+quantitaDocumento+"&nbsp;&nbsp;<i class='fas fa-plus aggiungiQuantitaDocumentoCarrello' data-codice='"+codice+"'></i></td>";  								    							
	    								contenuto += "<td>";
	    								contenuto += "	<i class='elimina eliminaDocumentoCarrello fas fa-times' style='cursor: pointer;' data-codice='"+codice+"' title='Elimina Documento'></i>";
	    								contenuto += "</td>";
	    							contenuto += "</tr>";
	    						}					
	    					}				 	    						    		
	    					risultato *= 1;
	    				}
	    				catch(Exception e) {
	    					errore += "Errore esecuzione Query."+e.getMessage();
	    					risultato *= 0;	    					
	    				}	    				    				
	    			}	    	
	    			
	    			try {
	    				if(risultato == 0) {
	    					connDB.getConn().rollback();
	    				}
	    				else {
	    					connDB.getConn().commit();
	    				}													    				
						connDB.getConn().close();
					} catch (SQLException e) {
						errore = connDB.getError();
		    			risultato *= 0;
					}
	    			
	    			risultato *= 1;    			
    			}
	    		else {
	    			errore = connDB.getError();
	    			risultato *= 0;
	    		}	    			
    		}
    		else{
    			risultato *= 0;
    			errore = "Errore Parametri";
    		}
    	}
		else{
			risultato *= 0;
			errore = "Errore Parametri";
		}	    
	   
		
		JSONObject res = new JSONObject();
		res.put("risultato", risultato);
		res.put("errore", errore);
		res.put("contenuto", contenuto);
		out.println(res);		
	}


}
