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
 * Servlet implementation class GetDocumenti
 */
@WebServlet("/GetMaterieAdmin")
public class GetMaterieAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMaterieAdmin() {
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
	    

		Integer risultato = 0;
	    String errore = "";
	    String contenuto = "";
        ConnessioneDB connDB = new ConnessioneDB();
		if(connDB.getConn() != null) {
			try {
				Statement stmt = connDB.getConn().createStatement();
				String sql = "";
				sql = ""
						+ "SELECT m.nome,(SELECT COUNT(d.codice) FROM documenti AS d WHERE d.nome_materia=m.nome AND d.flag=1 AND d.tipo='appunti') AS quantitaAppunti, "
						+ "(SELECT COUNT(d.codice) FROM documenti AS d WHERE d.nome_materia=m.nome AND d.flag=1 AND d.tipo='dispense') AS quantitaDispense "
						+ "FROM materie AS m "
						+ "WHERE m.flag = 1 "
						+ "ORDER BY m.nome ASC;";
				
				//System.out.println(sql);
				ResultSet result = stmt.executeQuery(sql);	
				if(!result.wasNull()) {
					while(result.next()) {
						contenuto += "<tr  class='materie'>";
						contenuto += "<td>"+result.getString("nome")+"</td>";	
						contenuto += "<td>"+result.getInt("quantitaAppunti")+"</td>";	
						contenuto += "<td>"+result.getString("quantitaDispense")+"</td>";	
						contenuto += "<td>";
						contenuto += "	<i class='eliminaMateria fas fa-times' style='cursor: pointer;' data-nome='"+result.getString("nome")+"' title='Elimina Materia'></i>";
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