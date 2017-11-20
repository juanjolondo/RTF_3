package com.capiro.appWeb.dominio;

import com.capiro.appWeb.DAO.ArchivoClientesDAO;
import com.capiro.appWeb.entidades.ClienteDTO;

public class ClienteService {
	
	private ArchivoClientesDAO clientesDAO;
	
	public ClienteService(){
		this.clientesDAO = new ArchivoClientesDAO();
	}

	public boolean existe(String idUsuario) {
		return this.clientesDAO.existe(idUsuario);
	}

	public boolean guardar(ClienteDTO cliente) {
		return this.guardar(cliente);
	}

}
