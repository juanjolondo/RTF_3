package com.capiro.appWeb.controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capiro.appWeb.dominio.PedidoClienteService;
import com.capiro.appWeb.entidades.FacturaDTO;
import com.capiro.appWeb.entidades.PedidoClienteDTO;

/**
 * Servlet implementation class ConfirmarPedido
 */
@WebServlet("/ConfirmacionPedido")
public class ConfirmacionPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PedidoClienteService pcn;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmacionPedido() {
        super();
        pcn = new PedidoClienteService();
   
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PedidoClienteDTO pc = (PedidoClienteDTO) request.getAttribute("pedido");
		FacturaDTO fac = (FacturaDTO) request.getAttribute("factura");
		
		if(pcn.guardarPedidoCliente(pc) || pcn.guardarFactura(fac)){
			response.getWriter().append("El pedido ha sido registrado exitosamente.");		
		}
		
	}
	
	
	

}
