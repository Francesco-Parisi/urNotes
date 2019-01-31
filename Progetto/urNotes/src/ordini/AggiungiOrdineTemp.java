package ordini;

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

import bean.Documento;
import connection.ConnessioneDB;
import model.Carrello;
import model.SystemInformation;


/**
 * Servlet implementation class AggiungiOrdineTemp
 */
@WebServlet("/AggiungiOrdineTemp")
public class AggiungiOrdineTemp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiOrdineTemp() {
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

		Integer spedizioneCheckout = Integer.parseInt(request.getParameter("spedizioneCheckout"));
		Integer vettoreCheckout = Integer.parseInt(request.getParameter("vettoreCheckout"));
		Integer metodoPagamentoCheckout = Integer.parseInt(request.getParameter("metodoPagamentoCheckout"));

        Integer risultato = 0;
        String errore = "";
        String contenuto = "";
		
    	if(request.getSession() != null){
    		Integer id_utente = (Integer) request.getSession().getAttribute("id_utente");    		
    		Carrello cart = (Carrello) request.getSession().getAttribute("carrello");
    		if(id_utente != null && cart != null){
    			if(cart.getNumeroDocumenti() > 0) {
    				
			        ConnessioneDB connDB = new ConnessioneDB();
					if(connDB.getConn() != null) {
						try {				
							Statement  stmt0;
							PreparedStatement  stmt1;
							ResultSet result;
							String sql;
							Integer contrassegno = null;	
							Float costo_vettore = null;
							Integer in_contanti = null;		

							stmt0 = connDB.getConn().createStatement();
		    				sql = ""
									+ "SELECT contrassegno, costo "
									+ "FROM ordini_vettori "
									+ "WHERE id_vettore = "+vettoreCheckout+"; ";
	    					result = stmt0.executeQuery(sql);				
	    					if(!result.wasNull()) {
	    						while(result.next()) {							
	    							contrassegno = result.getInt("contrassegno");
	    							costo_vettore = result.getFloat("costo");	    							
	    						}
	    					}
	    					
							stmt0 = connDB.getConn().createStatement();
		    				sql = ""
									+ "SELECT in_contanti "
									+ "FROM ordini_metodi_pagamento "
									+ "WHERE id_metodo = "+metodoPagamentoCheckout+"; ";
	    					result = stmt0.executeQuery(sql);				
	    					if(!result.wasNull()) {
	    						while(result.next()) {							
	    							in_contanti = result.getInt("in_contanti");
	    						}
	    					}

	    					if(contrassegno != null && in_contanti != null) {	    				
		    					if(in_contanti == 1 && contrassegno == 0){ //Se ho scelto un metodo di pagamento in contanti e un vettore che NON supporta il contrassegno
									errore = "Verifica che il vettore selezionato supporti il contrassegno.";
									risultato = 0;														
		    					}
		    					else {		    						
									sql = "INSERT INTO ordini (id_utente, id_vettore, id_metodo, id_indirizzo, data_ordine, attivo, totale_spedizione) VALUES (?, ?, ?, ?, NOW(), ?, ?) ;";
									PreparedStatement  stmt = connDB.getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
									stmt.setInt(1, id_utente);
									stmt.setInt(2, vettoreCheckout);
									stmt.setInt(3, metodoPagamentoCheckout);							
									stmt.setInt(4, spedizioneCheckout);
									stmt.setInt(5, 0);							
									stmt.setFloat(6, costo_vettore);							
									if(stmt.executeUpdate() == 1) {								
										Integer serial_id = 0;								
										ResultSet rs = stmt.getGeneratedKeys();
										if (rs.next()){
											serial_id = rs.getInt(1);
										}								
										
										if(serial_id > 0) {									
											Integer continua = 1;
											Float prezzo;

											for(Documento documento: cart.getDocumenti()) { //Per ogni documento del carrello
							    				stmt0 = null;
							    				result = null;					    				
						    					stmt0 = connDB.getConn().createStatement();
							    				sql = ""
														+ "SELECT d.titolo, d.nome_materia, d.prezzo "
														+ "FROM documenti  AS d "
														+ "WHERE d.flag = 1 AND d.codice = "+documento.getCodice()+"; ";
						    					//System.out.println(sql);
						    					result = stmt0.executeQuery(sql);				
						    					if(!result.wasNull()) {
						    						while(result.next()) {
						    							stmt1 = null;
						    							sql = "INSERT INTO ordini_documenti (serial_id, codice, titolo, quantita, prezzo_documenti, prezzo_totale, nome_materia, attivo) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
									    				stmt1 = connDB.getConn().prepareStatement(sql);
									    				stmt1.setInt(1, serial_id);												
									    				stmt1.setInt(2, documento.getCodice());
									    				stmt1.setString(3, result.getString("titolo"));
									    				stmt1.setInt(4, documento.getQuantita());	
									    				prezzo=result.getFloat("prezzo");
														stmt1.setFloat(5, prezzo);
														stmt1.setFloat(6, (prezzo*documento.getQuantita()));
														stmt1.setString(7, result.getString("nome_materia"));
														stmt1.setInt(8, 1);
														
														if(stmt1.executeUpdate() != 1) {
															errore += "Errore Inserimento Documento "+documento.getCodice();
															continua *= 0;
														}
						    						}
						    					}
						    					else {
													errore += "Errore Inserimento Documento "+documento.getCodice();
													continua *= 0;
						    					}
							    			}
											
											if(continua == 1) {
							    				stmt1 = null;
				    							sql = "UPDATE ordini SET "
				    								 +"totale_documenti =  (SELECT SUM(prezzo_totale) FROM ordini_documenti WHERE serial_id = ?), "
				    								 +"totale_ordine =  ((SELECT SUM(prezzo_totale) FROM ordini_documenti WHERE serial_id = ?) + totale_spedizione) "
				    								 +"WHERE serial_id = ?;";
				    							//System.out.println(sql);
							    				stmt1 = connDB.getConn().prepareStatement(sql);
							    				stmt1.setInt(1, serial_id);												
							    				stmt1.setInt(2, serial_id);												
							    				stmt1.setInt(3, serial_id);												
												if(stmt1.executeUpdate() == 1) {
								    				stmt0 = null;
								    				result = null;			
								    				Float totale_documenti = (float) 1;	
								    				stmt0 = connDB.getConn().createStatement();
								    				sql = ""
															+ "SELECT totale_documenti "
															+ "FROM ordini "
															+ "WHERE serial_id = "+serial_id+"; ";
							    					result = stmt0.executeQuery(sql);				
							    					if(!result.wasNull()) {
							    						while(result.next()) {
							    							totale_documenti = result.getFloat("totale_documenti");
							    						}													
							    					}
							    					
							    					continua = 1;
							    					
							    					if(continua == 1) {
							    						
															request.getSession().setAttribute("serial_id", serial_id);
															risultato = 1;					
															contenuto = "Ordine Inserito con Successo";						    							
							    													    						
							    					}
												}
												else {
													errore = "Errore Aggiornamento Prezzo Ordine.";
													risultato = 0;																											
												}							    				
							    			}
							    			else {
												risultato = 0;									    			
							    			}
											
										}
										else {
											errore = "Errore Generazione Nuovo Ordine Temporaneo.";
											risultato = 0;														
										}								
									}
									else {
										errore = "Errore Inserimento del Stato.";
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
	    					}
	    					else {
								errore = "Errore Verifica Metodo di Pagamento.";
								risultato = 0;														
	    					}	    																		
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
    				
    				
    			}
    			else {
    				errore = "Nessun prodotto trovato.";
    				risultato = 0;		    		    				
    			}
    		}
        	else {
    			errore = "Carrello Non Trovato.";
    			risultato = 0;		    		
        	}
    	}
    	else {
			errore = "Carrello Non Trovato.";
			risultato = 0;		    		
    	}

        				
		JSONObject res = new JSONObject();
		res.put("risultato", risultato);
		res.put("errore", errore);
		res.put("contenuto", contenuto);
		out.println(res);
	}


}