package co.analisis2.rtf3.dominio;

import co.analisis2.rtf3.DAO.ArchivoUsuarios;
import co.analisis2.rtf3.entidades.Usuario;

public class UsuarioNegocio {
	
	private ArchivoUsuarios usuariosDAO;
	
	public UsuarioNegocio(){
		this.usuariosDAO = new ArchivoUsuarios();
	}

	public boolean existe(String usuario) {
		return this.usuariosDAO.existe(usuario);
	}

	public boolean guardar(Usuario usu) {
		return this.usuariosDAO.guardar(usu);
	}

}
