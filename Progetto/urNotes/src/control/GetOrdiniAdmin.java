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
 * Servlet implementation class GetOrdiniAdmin
 */
@WebServlet("/GetOrdiniAdmin")
public class GetOrdiniAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOrdiniAdmin() {
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
						+ "SELECT o.serial_id, o.data_ordine, "
						+ "(SELECT SUM(quantita) FROM ordini_documenti WHERE serial_id = o.serial_id) AS quantita_documenti, "
						+ "o.totale_ordine "
						+ "FROM ordini  AS o "
						+ "WHERE o.attivo = 1 "
						+ "ORDER BY o.data_ordine DESC, o.serial_id DESC; ";
				ResultSet result = stmt.executeQuery(sql);				
				if(!result.wasNull()) {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					while(result.next()) {
						contenuto += "<tr>";
						 contenuto += "<tr class='ordini'>";
						    contenuto += "<td>"+result.getInt("serial_id")+"</td>";	
							contenuto += "<td>"+sdf.format(result.getDate("data_ordine"))+"</td>";
							contenuto += "<td>"+result.getString("quantita_documenti")+"</td>";		
							contenuto += "<td>&euro;"+new SystemInformation().truncateDecimal(result.getFloat("totale_ordine"),2)+"</td>";
									
							contenuto += "<td>";
							contenuto += "	<i class='foto dettaglioOrdine fas fa-search' style='cursor: pointer;' data-serial_id='"+result.getInt("serial_id")+"' title='Dettaglio Ordine'></i>";
							contenuto += "	<i class='eliminaOrdine fas fa-times' style='cursor: pointer;' data-serial_id='"+result.getInt("serial_id")+"' title='Elimina Ordine'></i>";
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
				errore = "Errore esecuzione Query."+e.getMessage();
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
