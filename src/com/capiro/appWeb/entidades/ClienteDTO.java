package com.capiro.appWeb.entidades;

public class ClienteDTO extends FacadePersonaDTO {
	
	public ClienteDTO() { 
	}
	
	public ClienteDTO(String nombre, String apellido, String id, char genero, String tipoId, String telefono,
			String direccion, String correo) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.id = id;
		this.genero = genero;
		this.tipoId = tipoId;
		this.telefono = telefono;
		this.direccion = direccion;
		this.correo = correo;
	}	

	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public char getGenero() {
		return genero;
	}
	
	public void setGenero(char genero) {
		this.genero = genero;
	}
	
	public String getTipoId() {
		return tipoId;
	}
	
	public void setTipoId(String tipoId) {
		this.tipoId = tipoId;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
}
