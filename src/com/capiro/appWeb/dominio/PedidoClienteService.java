package com.capiro.appWeb.dominio;

import com.capiro.appWeb.DAO.ArchivoFacturasDAO;
import com.capiro.appWeb.DAO.ArchivoPedidoClientesDAO;
import com.capiro.appWeb.entidades.FacturaDTO;
import com.capiro.appWeb.entidades.PedidoClienteDTO;

public class PedidoClienteService {
	
	private ArchivoFacturasDAO facturasDAO;
	private ArchivoPedidoClientesDAO pcDAO;
	
	public PedidoClienteService(){
		this.facturasDAO = new ArchivoFacturasDAO();
		this.pcDAO = new ArchivoPedidoClientesDAO();
	}

	public boolean guardarPedidoCliente(PedidoClienteDTO pc) {
		return this.pcDAO.guardar(pc);
	}

	public boolean guardarFactura(FacturaDTO fac) {
		return this.facturasDAO.guardar(fac);
	}

}
