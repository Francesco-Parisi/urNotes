package documenti;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import connection.ConnessioneDB;

/**
 * Servlet implementation class AggiungiIndirizzo
 */
@WebServlet("/AggiungiIndirizzo")
public class AggiungiIndirizzo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiIndirizzo() {
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

		//indirizzo,cap, citta,provincia,telefono,cellulare,id_utente
		
		Integer capIndirizzo = Integer.parseInt(request.getParameter("cap"));
		String cittaIndirizzo = request.getParameter("citta");
		String provinciaIndirizzo = request.getParameter("provincia");
		Integer idUtente = (Integer)request.getSession().getAttribute("id_utente");
		
		String nomeIndirizzo = request.getParameter("nome");
		String cognomeIndirizzo = request.getParameter("cognome");
		String indirizzoIndirizzo = request.getParameter("indirizzo");
		String telefonoIndirizzo = request.getParameter("telefono");
		String cellulareIndirizzo = request.getParameter("cellulare");
		
        Integer risultato = 0;
        String errore = "";
        String contenuto = "";
        
        
        ConnessioneDB connDB = new ConnessioneDB();
		if(connDB.getConn() != null) {
			try {				
				String sql = "INSERT INTO indirizzi(nome,cognome,indirizzo,cap,citta,provincia,telefono,cellulare,id_utente,attivo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ;";
				PreparedStatement  stmt = connDB.getConn().prepareStatement(sql);
				stmt.setString(1, nomeIndirizzo);				
				stmt.setString(2, cognomeIndirizzo);				
				stmt.setString(3, indirizzoIndirizzo);		
				stmt.setInt(4, capIndirizzo);
				stmt.setString(5, cittaIndirizzo);
				stmt.setString(6, provinciaIndirizzo);
				stmt.setString(7, telefonoIndirizzo);				
				stmt.setString(8, cellulareIndirizzo);				
				stmt.setInt(9, idUtente);
				stmt.setInt(10, 1);			
				if(stmt.executeUpdate() == 1) {
					contenuto = "Indirizzo Inserito con Successo";
					risultato = 1;					
				}
				else {
					errore = "Errore Inserimento Indirizzo.";
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
