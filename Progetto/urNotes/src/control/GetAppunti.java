package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.json.simple.JSONObject;

import model.ConnessioneDB;
import model.SystemInformation;
/**
 * Servlet implementation class GetProdotti
 */
@WebServlet("/GetAppunti")
public class GetAppunti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAppunti() {
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
	    
<<<<<<< HEAD
		String idAppunto = request.getParameter("id");
		System.out.println(idAppunto);
		String value = request.getParameter("value");
		System.out.println(value);
		
=======
>>>>>>> 62b4ada905a4c6103062b44722bc87b5593ca3b0
		Integer risultato = 0;
	    String errore = "";
	    String contenuto = "";
	    
        ConnessioneDB connDB = new ConnessioneDB();
		if(connDB.getConn() != null) {
			
			try {
				Statement stmt = connDB.getConn().createStatement();
				String sql = "";
				sql = ""
						+ "SELECT d.codice, d.foto, d.titolo, d.pagine, d.universita, d.descrizione, d.prezzo, "
						+ "FROM documenti AS d "
						+ "WHERE d.tipo = appunti; ";
				//System.out.println(sql);
				ResultSet result = stmt.executeQuery(sql);				
				if(!result.wasNull()) {
					while(result.next()) {
						contenuto += "<tr>";
							contenuto += "<td>"+result.getInt("codice")+"</td>";	
							contenuto += "<td>";
							contenuto += "	<i class='foto fotoProdotto fas fa-camera' style='cursor: pointer;' data-idprodotto='"+result.getInt("codice")+"' title='Gestisci Foto'></i>";
							contenuto += "	<i class='elimina eliminaProdotto fas fa-times' style='cursor: pointer;' data-idprodotto='"+result.getInt("codice")+"' title='Elimina Prodotto'></i>";
							contenuto += "</td>";							
							contenuto += "<td>"+result.getString("titolo")+"</td>";		
							contenuto += result.getInt("pagine");
							contenuto += "<td>"+result.getString("universita")+"</td>";							
							contenuto += "<td>"+result.getString("descrizione")+"</td>";							
							contenuto += "<td>";
							contenuto += new SystemInformation().truncateDecimal(result.getFloat("prezzo"),2);
							contenuto += "&nbsp;<i class='modificaPrezzo fas fa-edit' style='cursor: pointer;' data-idprodotto='"+result.getInt("id_prodotto")+"' title='Modifica Prezzo Prodotto'></i>";
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
	
		
		JSONObject res = new JSONObject();
		res.put("risultato", risultato);
		res.put("errore", errore);
		res.put("contenuto", contenuto);
		out.println(res);		
	}

}