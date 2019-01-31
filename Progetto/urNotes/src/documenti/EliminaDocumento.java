package documenti;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.json.simple.JSONObject;

import connection.ConnessioneDB;
import model.SystemInformation;
/**
 * Servlet implementation class EliminaProdotto
 */
@WebServlet("/EliminaDocumento")
public class EliminaDocumento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaDocumento() {
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
		
		int codice = Integer.parseInt(request.getParameter("codice"));
		
        Integer risultato = 0;
        String errore = "";
        String contenuto = "";
        
        ConnessioneDB connDB = new ConnessioneDB();
		if(connDB.getConn() != null) {
			try {
				Integer continua = 1;
				
				//Cancello Le immagini sia in DB che fisicamente
				Statement stmt0 = connDB.getConn().createStatement();
				String sql = "";
				sql = ""
						+ "SELECT id_immagine "
						+ "FROM documenti_immagini "
						+ "WHERE attivo = 1 AND codice = "+codice+";";
				ResultSet result = stmt0.executeQuery(sql);				
								
				if(!result.wasNull()) {
					int rowCount = result.last() ? result.getRow() : 0;
					if(rowCount > 0) {
						String filePath = new SystemInformation().getPathImmaginiDocumento()+codice+"\\";
						System.out.println(filePath);
						File file = new File(filePath);					    
						File[] contents = file.listFiles();
					    if (contents != null) {
					        for (File f : contents) {
							    if(!f.delete()){
									errore = "Errore Cancellazione File Immagini.";
									continua *= 0;
									return;
					    		}
					        }
					    }
					    if(file.delete()){
			    			continua *= 1;
			    		}
			    		else{
							errore = "Errore Cancellazione File Immagini.";
							continua *= 0;
							return;
			    		}				

						
						if(continua == 1) {
							Statement stmt1 = connDB.getConn().createStatement();
							sql = "UPDATE documenti_immagini SET attivo = 0 WHERE attivo = 1 AND codice = "+codice+";";
							if(stmt1.executeUpdate(sql) == 1) {									
								continua *= 1;
							}
							else {
								errore = "Errore Cancellazione Immagini.";
								risultato *= 0;					
							}												
						}
						
					}
					else {
						continua *= 1;
					}
				}					
				
				
				if(continua == 1) {
					//Cancello il documento
					Statement stmt2 = connDB.getConn().createStatement();
					sql = "UPDATE documenti SET flag = 0 WHERE codice = "+codice+";";
					if(stmt2.executeUpdate(sql) == 1) {					
						contenuto = "Documento Eliminato con Successo";
						risultato = 1;			
					}
					else {
						errore = "Errore Cancellazione Documento.";
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
		out.println(res);
	}

}
