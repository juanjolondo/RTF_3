package co.analisis2.rtf3.controlador;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.analisis2.rtf3.entidades.Factura;
import co.analisis2.rtf3.entidades.PedidoCliente;

/**
 * Servlet implementation class RegistroPedidos
 */
@WebServlet("/RegistroPedidos")
public class RegistroPedidos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistroPedidos() {
        super();
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
	   
	   String idCliente = request.getParameter("txtIdCliente");
	   String descripcion = request.getParameter("txtDescripcion");
	   String tipoFlor = request.getParameter("txtTipoFlor");
	   String formaPago = request.getParameter("txtFormaPago");
	   String consecutivo = Math.round(Math.random()*987698978)+"";
	   double totalValor = Double.parseDouble(request.getParameter("txtCantidadFlor")) * 12000.0;
	   List<Integer> cantidades = new ArrayList<Integer>();
	   cantidades.add(Integer.parseInt(request.getParameter("txtCantidad")));
	   List<String> productos = new ArrayList<String>();
	   productos.add(tipoFlor);
	   LocalDate fecha = LocalDate.now();
	   char estado = 'N';
	   boolean pagado = false;
	   PedidoCliente pc = new PedidoCliente(idCliente, descripcion, formaPago, totalValor, pagado, consecutivo, cantidades, estado, fecha, productos);
	   Factura fac = new Factura(consecutivo, idCliente, idCliente);
	   
	   if(validarCampos(pc,fac, response)){
		   request.setAttribute("pedido", pc);
		   request.setAttribute("factura", fac);
	   }
	   
	}
		
	private boolean validarCampos(PedidoCliente pc, Factura fac, HttpServletResponse response) throws IOException{
		String mensaje = "";
		if("".equals(pc.getIdCliente()) || pc.getIdCliente().length() == 0){
			mensaje = mensaje + "Campo identificación vacío\n";
		}
		if("".equals(pc.getMedioPago()) || pc.getMedioPago().length() == 0){
			mensaje = mensaje + "Campo medio de pago vacío\n";
		}
		if("".equals(pc.getDescripcion()) || pc.getDescripcion().length() == 0){
			mensaje = mensaje + "Campo descripción vacío\n";
		}
		
		if("".equals(mensaje) || mensaje.length() == 0){
			return true;
		}
		
		response.getWriter().append(mensaje);
		return false;
	}
	
}
