package com.capiro.appWeb.dominio;

import com.capiro.appWeb.DAO.ArchivoUsuariosDAO;
import com.capiro.appWeb.entidades.UsuarioDTO;

public class UsuarioService {
	
	private ArchivoUsuariosDAO usuariosDAO;
	
	public UsuarioService(){
		this.usuariosDAO = new ArchivoUsuariosDAO();
	}

	public boolean existe(String usuario) {
		return this.usuariosDAO.existe(usuario);
	}

	public boolean guardar(UsuarioDTO usu) {
		return this.usuariosDAO.guardar(usu);
	}

}
