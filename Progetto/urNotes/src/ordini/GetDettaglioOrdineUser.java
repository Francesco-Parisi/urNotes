package ordini;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import connection.ConnessioneDB;
import model.SystemInformation;

/**
 * Servlet implementation class GetDettaglioOrdineUser
 */
@WebServlet("/GetDettaglioOrdineUser")
public class GetDettaglioOrdineUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDettaglioOrdineUser() {
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
	    
		Integer risultato = 0;
	    String errore = "";
	    String contenuto = "";
	    
	    Integer serial_id = Integer.parseInt(request.getParameter("serial_id"));
	    if(serial_id > 0) {
			String spedizione = "";
			String vettore = "";
			String metodoPagamento = "";
			String totali = "";
			String body = "";					

			String sql;
			Statement stmt;
			Statement stmt1;
			ResultSet result;
			ResultSet result1;
	    	
	        ConnessioneDB connDB = new ConnessioneDB();	        
			if(connDB.getConn() != null) {				
				try {
    				sql = "";
    				stmt = null;
    				result = null;		    				

					stmt = connDB.getConn().createStatement();
    				sql = ""
							+ "SELECT * "
							+ "FROM ordini AS p "
							+ "WHERE attivo = 1 AND serial_id = "+serial_id+"; ";
					result = stmt.executeQuery(sql);
					if(!result.wasNull()) {

						while(result.next()) {

		    				sql = "";
		    				stmt1 = null;
		    				result1 = null;	
		    				stmt1 = connDB.getConn().createStatement();
							sql = "SELECT i.nome, i.cognome, i.indirizzo, i.cap, i.citta, i.provincia, i.telefono, i.cellulare "
								+ "FROM indirizzi AS i "
								+ "WHERE i.id_indirizzo = "+result.getInt("id_indirizzo")+"; ";
							result1 = stmt1.executeQuery(sql);
	    					if(!result1.wasNull()) {
	    						while(result1.next()){
	    							spedizione = result1.getString("nome")+" "+result1.getString("cognome")+" <br/> "+result1.getString("indirizzo")+" <br/> "+result1.getInt("cap")+" "+result1.getString("citta")+" ("+result1.getString("provincia")+")";
	    						}
	    					}	
	    					
		 						
	    	 				sql = "";
		    				stmt1 = null;
		    				result1 = null;	
		    				stmt1 = connDB.getConn().createStatement();
							sql = "SELECT nome, descrizione, costo "
								+ "FROM ordini_vettori "
								+ "WHERE id_vettore = "+result.getInt("id_vettore")+"; ";
							result1 = stmt1.executeQuery(sql);
	    					if(!result1.wasNull()) {
	    						while(result1.next()){
	    							vettore = result1.getString("nome")+" (&euro;"+new SystemInformation().truncateDecimal(result1.getFloat("costo"), 2)+")<br/>"+result1.getString("descrizione")+"";
	    						}
	    					}						    					

	    					
		    				sql = "";
		    				stmt1 = null;
		    				result1 = null;	
		    				stmt1 = connDB.getConn().createStatement();
							sql = "SELECT nome, descrizione "
								+ "FROM ordini_metodi_pagamento "
								+ "WHERE id_metodo = "+result.getInt("id_metodo")+"; ";
							result1 = stmt1.executeQuery(sql);
	    					if(!result1.wasNull()) {
	    						while(result1.next()){
	    							metodoPagamento = result1.getString("nome")+"<br/>"+result1.getString("descrizione")+"";
	    						}
	    					}						    					
	    					
	    					
	    					totali += "<p><b>Totale Documenti:</b> &euro;"+new SystemInformation().truncateDecimal(result.getFloat("totale_documenti"),2)+"</p>";
	    					totali += "<p><b>Totale Spedizione:</b> &euro;"+new SystemInformation().truncateDecimal(result.getFloat("totale_spedizione"), 2)+"</p>";
	    					totali += "<p class='totaleOrdine'><b>Totale Ordine:</b> &euro;"+new SystemInformation().truncateDecimal(result.getFloat("totale_ordine"), 2)+"</p>";
	    					
						}					
					}				 	    						

    				sql = "";
    				stmt = null;
    				result = null;					    				
    				stmt = connDB.getConn().createStatement();
    				sql = ""
							+ "SELECT *, " 
							+ "(SELECT filename FROM documenti_immagini WHERE codice = od.codice AND is_default = 1 AND attivo = 1) AS filename "											
							+ "FROM ordini_documenti AS od "
							+ "WHERE od.attivo = 1 AND od.serial_id = "+serial_id+"; ";
					//System.out.println(sql);
					result = stmt.executeQuery(sql);

					if(!result.wasNull()) {
						while(result.next()){
							String filename;
							if(result.getString("filename") != null){
								filename = new SystemInformation().getPathImmaginiDocumentoHTML()+result.getInt("codice")+"/"+result.getString("filename");		
							}
							else{
								filename = new SystemInformation().getPathImmaginiDocumentoDefault();

							}
														
							body += "<tr>";							
    							body += "<td>"+result.getInt("codice")+"</td>";	
    							body += "<td><img class='showImmagineDocumento' src='"+filename+"' alt='"+filename+"' /></td>";
    							body += "<td>"+result.getString("titolo")+"</td>";
    							body += "<td>"+result.getInt("quantita")+"</td>";
    							body += "<td>";	    					
    							body += "&euro; "+ new SystemInformation().truncateDecimal(result.getFloat("prezzo_totale"), 2);													
								body += "</td>";    								    							
							body += "</tr>";			    							
						}
					}
					
	       			contenuto += "<div id='content'>";
	       			contenuto += "<div id='content-content'>";
	       			contenuto += "<div id='dettaglioOrdine'>";
	       			contenuto += "<table id='prodottiTable'>";
	       				contenuto += "<thead class='userHeadDataTable'>";
	       					contenuto += "<tr>";
		       					contenuto += "<th class='row_title'>ID</th>";
		       					contenuto += "<th class='row_title'>Foto</th>";
		       					contenuto += "<th class='row_title'>Titolo</th>";
		       					contenuto += "<th class='row_title'>Quantità</th>";
		       					contenuto += "<th class='row_title'>Prezzo</th>";
	       					contenuto += "</tr>";
	   					contenuto += "</thead>";
	   					contenuto += "<tbody id='bodyDettaglioOrdine' class='userBodyDataTable'>";
	   					contenuto += body;
	   					contenuto +="</tbody>";
					contenuto += "</table>";
					
					contenuto += "<div class='left'>";
						contenuto += "<p>Indirizzo di Spedizione</p>";
						contenuto += spedizione;
					contenuto += "</div>";
					
					contenuto += "<div class='left'>";
						contenuto += "<p><b>Vettore</b></p>";
						contenuto += vettore;
					contenuto += "</div>";
					contenuto += "<div class='left'>";
						contenuto += "<p><b>Metodo di Pagamento</b></p>";
						contenuto += metodoPagamento;
					contenuto += "</div>";
			
					contenuto += totali;
					
					contenuto += "<script>";
					contenuto += "$('#dettaglioOrdineTable').DataTable( {";
					contenuto += "'order': [[ 0, 'desc' ]],";
					contenuto += "'language': {";
					contenuto += "'sEmptyTable':     'Nessun Prodotto Presente',";
					contenuto += "'sInfo':           'Vista da _START_ a _END_ di _TOTAL_ elementi',";
					contenuto += "'sInfoEmpty':      'Vista da 0 a 0 di 0 elementi',";
					contenuto += "'sInfoFiltered':   '(filtrati da _MAX_ elementi totali)',";
					contenuto += "'sInfoPostFix':    '',";
					contenuto += "'sInfoThousands':  '.',";
					contenuto += "'sLengthMenu':     'Visualizza _MENU_ elementi',";
					contenuto += "'sLoadingRecords': 'Caricamento...',";
					contenuto += "'sProcessing':     'Elaborazione...',";
					contenuto += "'sSearch':         'Cerca:',";
					contenuto += "'sZeroRecords':    'La ricerca non ha portato alcun risultato.',";
					contenuto += "'oPaginate': {";
					contenuto += "'sFirst':      'Inizio',";
					contenuto += "'sPrevious':   'Precedente',";
					contenuto += "'sNext':       'Successivo',";
					contenuto += "'sLast':       'Fine'";
					contenuto += "},";
					contenuto += "'oAria': {";
					contenuto += "'sSortAscending':  ': attiva per ordinare la colonna in ordine crescente',";
					contenuto += "'sSortDescending': ': attiva per ordinare la colonna in ordine decrescente'";
					contenuto += "}";
					contenuto += "}";        
					contenuto += "} );";	
					contenuto += "</script>";
					
	       			contenuto += "</div>";					
	       			contenuto += "</div>";					
	       			contenuto += "</div>";
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

	    }
	    else {
			errore = "Errore Parametri";
			risultato = 0;	    	
	    }	
		
		JSONObject res = new JSONObject();
		res.put("risultato", risultato);
		res.put("errore", errore);
		res.put("contenuto", contenuto);
		out.println(res);		
	}
}




