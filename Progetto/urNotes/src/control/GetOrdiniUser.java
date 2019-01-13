package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import model.ConnessioneDB;
import model.SystemInformation;

/**
 * Servlet implementation class GetOrdiniUser
 */
@WebServlet("/GetOrdiniUser")
public class GetOrdiniUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOrdiniUser() {
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
	    
    	if(request.getSession() != null){
    		Integer idUtente = (Integer) request.getSession().getAttribute("id_utente");    		
    		if(idUtente != null){
		        ConnessioneDB connDB = new ConnessioneDB();
				if(connDB.getConn() != null) {
					
					try {
						Statement stmt = connDB.getConn().createStatement();
						String sql = "";
						sql = ""
								+ "SELECT o.serial_id, o.id_vettore, o.data_ordine, (SELECT SUM(quantita) FROM ordini_documenti WHERE serial_id = o.serial_id) AS numero_documenti, o.totale_ordine "
								+ "FROM ordini  AS o "
								+ "WHERE o.attivo = 1 AND o.id_utente = "+idUtente+" "
								+ "ORDER BY o.data_ordine DESC, o.serial_id DESC; ";
						ResultSet result = stmt.executeQuery(sql);				
						if(!result.wasNull()) {
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							while(result.next()) {
								    contenuto += "<tr>";
								    contenuto += "<td>"+result.getInt("serial_id")+"</td>";	
								    contenuto += "<td>"+result.getInt("id_vettore")+"</td>";	
									contenuto += "<td>"+sdf.format(result.getDate("data_ordine"))+"</td>";
									contenuto += "<td>"+result.getString("numero_documenti")+"</td>";							
									contenuto += "<td>&euro;"+new SystemInformation().truncateDecimal(result.getFloat("totale_ordine"),2)+"</td>";
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
						errore = "Errore esecuzione Query."+e.getMessage();
						risultato = 0;
					}
				}
				else {
					errore = connDB.getError();
					risultato = 0;
				}			

    		}
    		else {
        		errore = "Errore Parametri.";
        		risultato = 0;    			
    		}
    	}
    	else{
    		errore = "Errore Parametri.";
    		risultato = 0;
    	}
			
		
		JSONObject res = new JSONObject();
		res.put("risultato", risultato);
		res.put("errore", errore);
		res.put("contenuto", contenuto);
		out.println(res);		
	}


}
