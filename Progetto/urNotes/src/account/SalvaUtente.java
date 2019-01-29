package account;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import connection.ConnessioneDB;
import model.MD5;

/**
 * Servlet implementation class SalvaUtente
 */
@WebServlet("/SalvaUtente")
public class SalvaUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalvaUtente() {
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
		
		String username = request.getParameter("username");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
        Integer risultato = 0;
        String errore = "";
        String contenuto = "";
        String redirect = "";
        
        ConnessioneDB connDB = new ConnessioneDB();
		if(connDB.getConn() != null) {
			try {				
				String sql;
				Statement stmt0 = connDB.getConn().createStatement();
				sql = ""
						+ "SELECT id_utente "
						+ "FROM utenti "
						+ "WHERE attivo = 1 AND (TRIM(email) = TRIM('"+email+"') OR TRIM(username) = TRIM('"+username+"')); ";
				ResultSet result = stmt0.executeQuery(sql);				
				if(!result.wasNull()) {
					int rowCount = result.last() ? result.getRow() : 0;
					if(rowCount > 0) {
						errore = "Esiste gi&agrave; un utente con questa email o con questa username.";
						risultato = 0;
					}					
					else {
						sql = "INSERT INTO utenti(username, nome, cognome,email, paswd, tipo_utente, attivo) VALUES (?, ?, ?, ?, ?, 2, 1);";
						PreparedStatement  stmt = connDB.getConn().prepareStatement(sql);
						stmt.setString(1, username);
						stmt.setString(2, nome);
						stmt.setString(3, cognome);
						stmt.setString(4, email.trim());
						stmt.setString(5, MD5.crypt(password));
						//stmt.setInt(6, 2);				
						//stmt.setInt(7, 1);				
						if(stmt.executeUpdate() == 1) {
							redirect = request.getContextPath()+"/accedi.jsp";
							contenuto = "Registrazione Effettuata con Successo";
							risultato = 1;		
						}
						else {
							errore = "Errore registrazione.";
							risultato = 0;					
						}				
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
		res.put("redirect", redirect);
		out.println(res);
		
	}


}
