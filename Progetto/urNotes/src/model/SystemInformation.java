package model;

import java.math.BigDecimal;

public class SystemInformation {
	
	public SystemInformation() {
		this.setPathImmaginiDocumento("C:\\Users\\Francesco\\Documents\\urNotes\\Progetto\\urNotes\\WebContent\\images\\documenti\\");		
		this.setPathImmaginiDocumentoHTML("/urNotes/images/documenti/");		
		this.setPathImmaginiDocumentoDefault("/urNotes/images/documenti/documento_blank.png");
		this.setUrlRedirect("/accedi.jsp");				
		this.setLimiteDocumentiIndex(9);
	}

	public String getPathImmaginiDocumentoHTML() {		
		return pathImmaginiDocumentoHTML; 
	}	
	public void setPathImmaginiDocumentoHTML(String pathImmaginiDocumento) {
		this.pathImmaginiDocumentoHTML = pathImmaginiDocumento;
	}
	
	public String getPathImmaginiDocumento() {		
		return pathImmaginiDocumento; 
	}
	public void setPathImmaginiDocumento(String pathImmaginiDocumento) {
		this.pathImmaginiDocumento = pathImmaginiDocumento;
	}
	
	public String getPathImmaginiDocumentoDefault() {
		return pathImmaginiDocumentoDefault;
	}
	public void setPathImmaginiDocumentoDefault(String pathImmaginiDocumentoDefault) {
		this.pathImmaginiDocumentoDefault = pathImmaginiDocumentoDefault;
	}
	
	public String getUrlRedirect() {
		return urlRedirect;
	}
	public void setUrlRedirect(String urlRedirect) {
		this.urlRedirect = urlRedirect;
	}

	public Integer getLimiteDocumentiIndex() {
		return getLimiteDocumentiIndex();
	}
	public void setLimiteDocumentiIndex(Integer limite) {
		this.limiteDocumentiIndex = limite;
	}
	
	@SuppressWarnings("deprecation")
	public BigDecimal truncateDecimal(double x,int numberofDecimals){
	    if ( x > 0) {
	        return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_FLOOR);
	    } else {
	        return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_CEILING);
	    }
	}
	

	private String pathImmaginiDocumento;
	private String pathImmaginiDocumentoHTML;
	private String pathImmaginiDocumentoDefault;
	private String urlRedirect;
	private Integer limiteDocumentiIndex;
}
