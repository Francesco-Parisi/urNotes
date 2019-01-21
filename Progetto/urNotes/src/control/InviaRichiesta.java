package control;

import java.io.IOException; 
import java.io.PrintWriter;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.json.simple.JSONObject;

import model.ConnessioneDB;
/**
 * Servlet implementation class InviaRichiesta
 */
@WebServlet("/InviaRichiesta")
public class InviaRichiesta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InviaRichiesta() {
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
		
		String titolo = request.getParameter("titolo");
		Integer pagine = Integer.parseInt(request.getParameter("pagine"));
		String nome_materia = request.getParameter("nome_materia");
		String universita = request.getParameter("universita");
		String descrizione = request.getParameter("descrizione");
		String tipo = request.getParameter("tipo");

		Integer id_utente = Integer.parseInt(request.getParameter("id_utente"));

        Integer risultato = 0;
        String errore = "";
        String contenuto = "";
        
        ConnessioneDB connDB = new ConnessioneDB();
		if(connDB.getConn() != null) {
			try {				
				String sql = "INSERT INTO richieste (id_utente, titolo, pagine, nome_materia, universita, descrizione, tipo, data_richiesta, letta, attivo) VALUES (?, ?, ?, ?, ?, ?, ?,  DATE(NOW()), ?, ?);";
				PreparedStatement  stmt = connDB.getConn().prepareStatement(sql);				
				
				if(id_utente == 0) stmt.setNull(1, 1); else stmt.setInt(1, id_utente); 
				stmt.setString(2, titolo);			
				stmt.setInt(3, pagine);
				stmt.setString(4, nome_materia);
				stmt.setString(5, universita);				
				stmt.setString(6, descrizione);				
				stmt.setString(7, tipo);
				stmt.setInt(8, 0);				
				stmt.setInt(9, 1);
				if(stmt.executeUpdate() == 1) {
					contenuto = "Grazie per aver condiviso i tuoi Documenti";
					risultato = 1;					
				}
				else {
					errore = "Errore Inserimento Richiesta.";
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
