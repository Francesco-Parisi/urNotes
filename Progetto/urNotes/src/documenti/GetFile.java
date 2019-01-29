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
 * Servlet implementation class GetFile
 */
@WebServlet("/GetFile")
public class GetFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFile() {
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
		Integer risultato = 0;
	    String errore = "";
	    String contenuto = "";
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

	    Integer id_richiesta = Integer.parseInt(request.getParameter("id_richiesta"));	
	    request.getSession().setAttribute("id_richiesta", id_richiesta);
	    
		if(id_richiesta > 0){
	        ConnessioneDB connDB = new ConnessioneDB();
			if(connDB.getConn() != null) {
				
				try {
					Statement stmt = connDB.getConn().createStatement();
					String sql = "";
					sql = ""
							+ "SELECT id_file, filename "
							+ "FROM richieste_file "
							+ "WHERE attivo = 1 AND id_richiesta = "+id_richiesta+"; ";
					//System.out.println(sql);
					ResultSet result = stmt.executeQuery(sql);				
					if(!result.wasNull()) {
						while(result.next()) {
							contenuto += "<tr>";
								contenuto += "<td><img class='showImmagineDocumento' src='"+request.getContextPath()+"/images/richieste/"+id_richiesta+"/"+result.getString("filename")+"' alt='"+result.getString("filename")+"' /></td>";
								contenuto += "<td>";
								contenuto += "	<i class='scaricaFile fas fa-download' style='cursor: pointer;' data-id_file='"+result.getInt("id_file")+"' title='Apri File'></i>";
								contenuto += "	<i class='elimina eliminaFile fas fa-times' style='cursor: pointer;' data-id_file='"+result.getInt("id_file")+"' title='Elimina File'></i>";
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
			
		}
		else {
			errore = "Errore Parametri Servlet.";
		}
		
	    
	    
	
		
		JSONObject res = new JSONObject();
		res.put("risultato", risultato);
		res.put("errore", errore);
		res.put("contenuto", contenuto);
		out.println(res);		
	}

}
