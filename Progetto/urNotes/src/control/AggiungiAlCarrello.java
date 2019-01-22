package control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import model.Carrello;
import model.Documento;
import model.SystemInformation;

/**
 * Servlet implementation class AggiungiAlCarrello
 */
@WebServlet("/AggiungiAlCarrello")
public class AggiungiAlCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiAlCarrello() {
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
        
		Integer risultato = 0;
        Integer redirect = 0;
        String errore = "";
        String contenuto = "";
        String urlRedirect = "";

		Integer codice = Integer.parseInt(request.getParameter("codice"));
		Integer quantita = Integer.parseInt(request.getParameter("quantita"));
		
		Integer id_utente = 0;			
		if(request.getSession().getAttribute("id_utente") != null) {
			id_utente = (Integer) request.getSession().getAttribute("id_utente");
		}		
			

        if(id_utente > 0) {
        	Carrello cart = (Carrello) request.getSession().getAttribute("carrello");
        	if(cart != null) {
	        	Documento d = cart.getDocumentoByCodiceDocumento(codice);
	        	if(d != null) {
	        		d.setQuantita(d.getQuantita()+1);
	        	}
	        	else {
	        		cart.setDocumento(codice, quantita);
	        	}
	        	request.getSession().setAttribute("carrello", cart);
	        	contenuto = "Documento Aggiunto con Successo";
	        	risultato = 1;
        	}
        	else {
        		risultato = 0;
        		errore = "Carrello Non Trovato";
        	}
        }
        else {
        	risultato = 1;
        	redirect = 1;
        	contenuto = "Utente Non Loggato";
        	urlRedirect = new SystemInformation().getUrlRedirect();
        }
       
           
        				
		JSONObject res = new JSONObject();
		res.put("risultato", risultato);
		res.put("errore", errore);
		res.put("contenuto", contenuto);
		res.put("redirect", redirect);
		res.put("urlRedirect", urlRedirect);
		out.println(res);
	}

}
