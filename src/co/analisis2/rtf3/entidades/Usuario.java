package co.analisis2.rtf3.entidades;

public class Usuario {
	private String idUsuario;
	private String usuario;
	private String clave;
	
	public Usuario(){
	}
	
	public Usuario(String idUsuario, String usuario, String clave) {
		this.idUsuario = idUsuario;
		this.usuario = usuario;
		this.clave = clave;
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