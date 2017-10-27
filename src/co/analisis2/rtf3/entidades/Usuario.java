package co.analisis2.rtf3.entidades;

public class Usuario {
	private String idUsuario;
	private String usuario;
	private String clave;
	private String tipo;
	
	public Usuario(){
	}
	
	public Usuario(String idUsuario, String usuario, String clave, String tipo) {
		this.idUsuario = idUsuario;
		this.usuario = usuario;
		this.clave = clave;
		this.tipo = tipo;
	}
	
	public String getTipo(){
		return tipo;
	}
	
	public void setTipo(String tipo){
		this.tipo = tipo;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
}