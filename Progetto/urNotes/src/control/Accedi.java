package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.json.simple.JSONObject;

import model.Carrello;
import model.ConnessioneDB;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings({ "unchecked" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Username = request.getParameter("Username");
        String Password = request.getParameter("Paswd");
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
						+ "SELECT u.id_utente, u.tipo_utente, u.Nome, u.Cognome "
						+ "FROM utente u "
						+ "WHERE u.id_utente = 0 AND TRIM(u.Email) = TRIM('"+Username+"') AND u.Paswd = '"+MD5.crypt(Password)+"';";
				ResultSet result = stmt.executeQuery(sql);
				if(result.wasNull()) {
					errore = "Errore esecuzione Query.";
					risultato = 0;
				}
				else {
					int rowCount = result.last() ? result.getRow() : 0;
					if(rowCount == 1) {												
						session.setAttribute("Username", result.getString("Username"));
						session.setAttribute("Nome", result.getString("Nome"));
						session.setAttribute("Cognome", result.getString("Cognome"));
						session.setAttribute("Email", result.getString("Email"));
						session.setAttribute("Paswd", result.getString("Paswd"));
						session.setAttribute("tipo_utente", Integer.parseInt(result.getString("tipo_utente")));
						session.setAttribute("id_utente", Integer.parseInt(result.getString("id_utente")));
						session.setAttribute("carrello", new Carrello(Integer.parseInt(result.getString("id_utente"))));
						
						if((int) session.getAttribute("tipo_utente") == 1) {
							redirect = request.getContextPath()+"/_GestoreArea/profilo_gestore.jsp";
						}
						if((int) session.getAttribute("tipo_utente") == 2) {
							redirect = request.getContextPath()+"/_StudenteArea/profilo_studente.jsp"; 														
						}	
						
						contenuto = "Utente Trovato";						
						risultato = 1;
					}
					else {
						errore = "Utente NON Trovato";
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
