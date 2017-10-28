package co.analisis2.rtf3.dominio;

import co.analisis2.rtf3.DAO.ArchivoFacturas;
import co.analisis2.rtf3.DAO.ArchivoPedidoClientes;
import co.analisis2.rtf3.entidades.Factura;
import co.analisis2.rtf3.entidades.PedidoCliente;

public class PedidoClienteNegocio {
	
	private ArchivoFacturas facturasDAO;
	private ArchivoPedidoClientes pcDAO;
	
	public PedidoClienteNegocio(){
		this.facturasDAO = new ArchivoFacturas();
		this.pcDAO = new ArchivoPedidoClientes();
	}

	public boolean guardarPedidoCliente(PedidoCliente pc) {
		return this.pcDAO.guardar(pc);
	}

	public boolean guardarFactura(Factura fac) {
		return this.facturasDAO.guardar(fac);
	}

}
