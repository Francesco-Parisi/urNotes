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
 * Servlet implementation class AggiungiDocumento
 */
@WebServlet("/AggiungiDocumento")
public class AggiungiDocumento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiDocumento() {
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
		String universita = request.getParameter("universita");
		String nome_materia = request.getParameter("nome_materia");
		String descrizione = request.getParameter("descrizione");
		Float prezzoDocumento = Float.parseFloat(request.getParameter("prezzoDocumento"));
		Integer tipo = Integer.parseInt(request.getParameter("tipo"));
		
		
		System.out.println(titolo);
		System.out.println(pagine);
		System.out.println(universita);
		System.out.println(nome_materia);
		System.out.println(descrizione);
		System.out.println(prezzoDocumento);
		System.out.println(tipo);
		
		
        Integer risultato = 0;
        String errore = "";
        String contenuto = "";
        
        ConnessioneDB connDB = new ConnessioneDB();
		if(connDB.getConn() != null) {
			try {				
				String sql = "INSERT INTO documenti (titolo,pagine,universita,nome_materia,descrizione,prezzo,tipo,flag) VALUES (?,?,?,?,?,?,?,1);";
				PreparedStatement  stmt = connDB.getConn().prepareStatement(sql);
				stmt.setString(1, titolo);
				stmt.setInt(2, pagine);	
				stmt.setString(3, universita);
				stmt.setString(4, nome_materia);
				stmt.setString(5, descrizione);
				stmt.setFloat(6, prezzoDocumento);
				stmt.setString(7, (tipo == 1)? "Appunti":"Dispense");			
				if(stmt.executeUpdate() == 1) {
					contenuto = "Documento Inserito con Successo";
					risultato = 1;					
				}
				else {
					errore = "Errore Inserimento Documento.";
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
