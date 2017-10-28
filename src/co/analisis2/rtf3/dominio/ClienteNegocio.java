package co.analisis2.rtf3.dominio;

import co.analisis2.rtf3.DAO.ArchivoClientes;
import co.analisis2.rtf3.entidades.Cliente;

public class ClienteNegocio {
	
	private ArchivoClientes clientesDAO;
	
	public ClienteNegocio(){
		this.clientesDAO = new ArchivoClientes();
	}

	public boolean existe(String idUsuario) {
		return this.clientesDAO.existe(idUsuario);
	}

	public boolean guardar(Cliente cliente) {
		return this.guardar(cliente);
	}

}
