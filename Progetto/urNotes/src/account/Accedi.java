package account;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.json.simple.JSONObject;

import connection.ConnessioneDB;
import model.Carrello;
import model.MD5;
/**
 * Servlet implementation class Accedi
 */
@WebServlet("/Accedi")
public class Accedi extends HttpServlet {
	private static final long serialVersionUID = 1L;
          
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Accedi() {
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
	@SuppressWarnings({ "unchecked" })
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
        String password = request.getParameter("password");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		HttpSession session = request.getSession();
        
        Integer risultato = 0;
        String errore = "";
        String contenuto = "";
        String redirect = "";
        
        ConnessioneDB connDB = new ConnessioneDB();
		if(connDB.getConn() != null) {
			try {
				Statement stmt = connDB.getConn().createStatement();
				String sql = "";
				new MD5();
				sql = ""
						+ "SELECT id_utente, tipo_utente, nome, cognome "
						+ "FROM utenti AS u "
						+ "WHERE u.attivo = 1 AND TRIM(u.username) = TRIM('"+username+"') AND u.paswd = '"+MD5.crypt(password)+"';";
				ResultSet result = stmt.executeQuery(sql);
				if(result.wasNull()) {
					errore = "Errore esecuzione Query.";
					risultato = 0;
				}
				else {
					int rowCount = result.last() ? result.getRow() : 0;
					if(rowCount == 1) {			
						session.setAttribute("id_utente", Integer.parseInt(result.getString("id_utente")));
						session.setAttribute("nome", result.getString("nome"));
						session.setAttribute("cognome", result.getString("cognome"));
						session.setAttribute("tipo_utente", Integer.parseInt(result.getString("tipo_utente")));
						session.setAttribute("nome_utente", username);	
						session.setAttribute("carrello", new Carrello(Integer.parseInt(result.getString("id_utente"))));
						
						if((int) session.getAttribute("tipo_utente") == 1) {
							redirect = request.getContextPath()+"/_GestoreArea/profilo_gestore.jsp";
						}
						if((int) session.getAttribute("tipo_utente") == 2) {
							redirect = request.getContextPath()+"/index.jsp"; 														
						}	
						
						contenuto = "Utente Trovato";						
						risultato = 1;
					}
					else {
						errore = "Utente non Trovato. Riprova!";
						risultato = 0;
					}						
				}
				
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
		res.put("redirect", redirect);
		out.println(res);
	}

}
