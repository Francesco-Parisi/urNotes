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
 * Servlet implementation class GetRecensioneAdmin
 */
@WebServlet("/GetRecensioneAdmin")
public class GetRecensioneAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetRecensioneAdmin() {
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
	    
	    //Integer id_recensione = Integer.parseInt(request.getParameter("id_recensione"));
	    //System.out.println(id_recensione);
	    
        ConnessioneDB connDB = new ConnessioneDB();
		if(connDB.getConn() != null) {
			
			try {
				Statement stmt = connDB.getConn().createStatement();
				String sql = "";
				sql = ""
						+ "SELECT r.username, r.descrizione, r.id_recensione, d.titolo "
						+ "FROM recensioni  AS r, documenti AS d "
						+ "WHERE r.flag = 1 AND r.codice = d.codice;";
				
				ResultSet result = stmt.executeQuery(sql);				
				if(!result.wasNull()) {
					while(result.next()) {
							contenuto += "<tr class='prova1'>";
							contenuto += "<td>"+result.getString("username")+"</td>";
							contenuto += "<td>"+result.getString("descrizione")+"</td>";
							contenuto += "<td>"+result.getString("titolo")+"</td>";
							contenuto += "<td>";
							contenuto += "	<i class='eliminaRecensione fas fa-times' style='cursor: pointer;' data-id_recensione='"+result.getInt("id_recensione")+"' title='Elimina Recensione'></i>";
							contenuto += "</td>";
							contenuto += "</tr>";
					}
				}
				else {
					contenuto += "<tr id=container_documenti'>";
					contenuto += "<td>Non ci sono recensioni</td>";
					contenuto += "</tr>";
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

