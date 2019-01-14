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
import model.SystemInformation;
/**
 * Servlet implementation class GetAppunti
 */
@WebServlet("/GetAppunti")
public class GetAppunti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAppunti() {
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
	    
		//String idAppunto = request.getParameter("id");
		//System.out.print(idAppunto+" ");
		String value = request.getParameter("value");
		//System.out.println(value);

		Integer risultato = 0;
	    String errore = "";
	    String contenuto = "";
	    int i = 1;
        ConnessioneDB connDB = new ConnessioneDB();
		if(connDB.getConn() != null) {
			try {
				Statement stmt = connDB.getConn().createStatement();
				String sql = "";
				sql = ""
						+ "SELECT d.codice, d.titolo, d.pagine, d.universita, d.nome_materia, d.prezzo "
						+ "FROM documenti AS d "
						+ "WHERE d.tipo LIKE 'appunti' AND d.nome_materia LIKE '"+value+"';";
				//System.out.println(sql);
				ResultSet result = stmt.executeQuery(sql);	
				
				if(!result.wasNull()) {
					while(result.next()) {
						contenuto += "<tr>";
						contenuto += "<td>"+result.getString("titolo")+"</td>";		
						contenuto += "<td>"+result.getInt("pagine")+"</td>";
						contenuto += "<td>"+result.getString("universita")+"</td>";							
						contenuto += "<td>";
						contenuto += new SystemInformation().truncateDecimal(result.getFloat("prezzo"),2);							
						contenuto += "</td>";	

						contenuto += "<td><input type='submit' id='idAppuntoDett' data-id='"+result.getString("codice")+"' name='submitForm' class='campoForm submitForm' value='Dettaglio'></td>";

						contenuto += "</tr>";
						i++;
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