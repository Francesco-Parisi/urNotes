package documenti;

import java.io.IOException; 
import java.io.PrintWriter;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.json.simple.JSONObject;

import connection.ConnessioneDB;
/**
 * Servlet implementation class AggiungiRichiesta
 */
@WebServlet("/AggiungiRichiesta")
public class AggiungiRichiesta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiRichiesta() {
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
		
		String titolo = request.getParameter("titolo");
		Integer pagine = Integer.parseInt(request.getParameter("pagine"));
		String universita = request.getParameter("universita");
		String nome_materia = request.getParameter("nome_materia");
		String descrizione = request.getParameter("descrizione");
		Integer tipo = Integer.parseInt(request.getParameter("tipo"));
		
		
        Integer risultato = 0;
        String errore = "";
        String contenuto = "";
        
        ConnessioneDB connDB = new ConnessioneDB();
		if(connDB.getConn() != null) {
			try {				
				String sql = "INSERT INTO richieste (titolo,pagine,nome_materia,universita,descrizione,tipo,data_richiesta,id_utente,attivo) VALUES (?,?,?,?,?,?,NOW(),'"+request.getSession().getAttribute("id_utente")+"',1);";
				PreparedStatement  stmt = connDB.getConn().prepareStatement(sql);
				stmt.setString(1, titolo);
				stmt.setInt(2, pagine);	
				stmt.setString(3, nome_materia);
				stmt.setString(4, universita);
				stmt.setString(5, descrizione);
				stmt.setString(6, (tipo == 1)? "Appunti":"Dispense");			
				if(stmt.executeUpdate() == 1) {
					contenuto = "Richiesta Inserito con Successo";
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
