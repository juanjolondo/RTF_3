package co.analisis2.rtf3.controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.analisis2.rtf3.dominio.PedidoClienteNegocio;
import co.analisis2.rtf3.entidades.Factura;
import co.analisis2.rtf3.entidades.PedidoCliente;

/**
 * Servlet implementation class ConfirmarPedido
 */
@WebServlet("/ConfirmarPedido")
public class ConfirmarPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PedidoClienteNegocio pcn;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmarPedido() {
        super();
        pcn = new PedidoClienteNegocio();
   
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
	
		PedidoCliente pc = (PedidoCliente) request.getAttribute("pedido");
		Factura fac = (Factura) request.getAttribute("factura");
		
		if(pcn.guardarPedidoCliente(pc) || pcn.guardarFactura(fac)){
			response.getWriter().append("El pedido ha sido registrado exitosamente.");		
		}
		
	}
	
	
	

}
