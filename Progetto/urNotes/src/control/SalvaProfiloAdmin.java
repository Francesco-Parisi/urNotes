package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import model.ConnessioneDB;
import model.MD5;

/**
 * Servlet implementation class SalvaProfiloAdmin
 */
@WebServlet("/SalvaProfiloAdmin")
public class SalvaProfiloAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalvaProfiloAdmin() {
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

		Integer id_utente = Integer.parseInt(request.getParameter("id_utente"));
		Integer tipo_utente = Integer.parseInt(request.getParameter("tipo_utente"));		

		String password = "";
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String username = request.getParameter("username");
		if(tipo_utente == 1){ //Salvo anche la pwd
			password = request.getParameter("password");
		}
		
        Integer risultato = 0;
        String errore = "";
        String contenuto = "";
		
        ConnessioneDB connDB = new ConnessioneDB();
		if(connDB.getConn() != null) {
			try {				
				String sql = "";
				PreparedStatement  stmt = null;
				if(tipo_utente == 2){ //Salvo solo i dati
					sql = "UPDATE utenti SET nome = ?, cognome = ?, username = ? WHERE id_utente = ?;";
					stmt = connDB.getConn().prepareStatement(sql);
					stmt.setString(1, nome);
					stmt.setString(2, cognome);
					stmt.setString(3, username);
					stmt.setInt(4, id_utente);	
				}
				else if(tipo_utente == 1){ //Salvo anche la pwd			
					sql = "UPDATE utenti SET nome = ?, cognome = ?, username = ?, paswd = ? WHERE id_utente = ?;";
					stmt = connDB.getConn().prepareStatement(sql);
					stmt.setString(1, nome);
					stmt.setString(2, cognome);
					stmt.setString(3, username);
					stmt.setString(4, password);
					stmt.setInt(5, id_utente);	
				}						
			
				if(stmt.executeUpdate() == 1) {
					contenuto = "Utenza Aggiornata con Successo";
					risultato = 1;					
				}
				else {
					errore = "Errore Aggiornamento Password.";
					risultato = 0;					
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
		out.println(res);
		
	}


}
