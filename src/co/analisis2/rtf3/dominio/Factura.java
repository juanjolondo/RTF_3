package co.analisis2.rtf3.dominio;

public class Factura {
	private String consecutivo;
	private String nit;
	private String idCliente;
	
	public Factura(){
		
	}
	
	public Factura(String consecutivo, String nit, String idCliente) {
		this.consecutivo = consecutivo;
		this.nit = nit;
		this.idCliente = idCliente;
	}
	
	public String getConsecutivo() {
		return consecutivo;
	}
	
	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
	}
	
	public String getNit() {
		return nit;
	}
	
	public void setNit(String nit) {
		this.nit = nit;
	}
	
	public String getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	
}