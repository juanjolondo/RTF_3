package co.analisis2.rtf3.controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.analisis2.rtf3.dominio.UsuarioNegocio;
import co.analisis2.rtf3.dominio.ClienteNegocio;
import co.analisis2.rtf3.entidades.Cliente;
import co.analisis2.rtf3.entidades.Usuario;

/**
 * Servlet implementation class RegistrarUsuario
 */
@WebServlet("/RegistrarUsuario")
public class RegistrarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioNegocio usuarioNeg;
	private ClienteNegocio clienteNeg;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrarUsuario() {
        super();
        usuarioNeg = new UsuarioNegocio();
        clienteNeg = new ClienteNegocio();
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
		// TODO Auto-generated method stub
		
		String usuario = request.getParameter("txtUsuario");
		String nombre = request.getParameter("txtNombre");
		String apellido = request.getParameter("txtApellido");
		char genero = request.getParameter("txtGenero").charAt(0);
		String tipoId = request.getParameter("txtTipoId");
		String direccion = request.getParameter("txtDireccion");
		String idUsuario = request.getParameter("txtIdUsuario");
		String email = request.getParameter("txtEmail");
		String clave = request.getParameter("txtClave");
		String telefono = request.getParameter("txtTelefono");
		
		Cliente cliente = new Cliente(nombre, apellido, idUsuario, genero, tipoId, telefono, direccion, email);
		Usuario usu = new Usuario(idUsuario, usuario, clave, "1");
		
		if(validarCampos(cliente,usu, response)){
			
			if(usuarioNeg.existe(usuario) && clienteNeg.existe(idUsuario)){
				response.getWriter().append("El cliente o el usuario ya se encuentra registrados.");
			}
			else if(usuarioNeg.guardar(usu) && clienteNeg.guardar(cliente)){
				response.getWriter().append("El usuario ha sido registrado.");
				getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			}
		}
		
	}
	
	private boolean validarCampos(Cliente cliente, Usuario usuario, HttpServletResponse response) throws IOException{
		String mensaje = "";
		if("".equals(cliente.getNombre()) || cliente.getNombre().length() == 0){
			mensaje = mensaje + "Campo nombre vac�o.\n";
		}
		if("".equals(cliente.getApellido()) || cliente.getApellido().length() == 0){
			mensaje = mensaje + "Campo apellido vac�o.\n";
		}
		if("".equals(cliente.getId()) || cliente.getId().length() == 0){
			mensaje = mensaje + "Campo identificaci�n vac�o.\n";
		}
		if("".equals(cliente.getGenero())){
			mensaje = mensaje + "Campo g�nero vac�o.\n";
		}
		if("".equals(cliente.getTipoId()) || cliente.getTipoId().length() == 0){
			mensaje = mensaje + "Campo tipo de identificaci�n vac�o.\n";
		}
		if("".equals(cliente.getTelefono()) || cliente.getTelefono().length() == 0){
			mensaje = mensaje + "Campo tel�fono vac�o.\n";
		}
		if("".equals(cliente.getDireccion()) || cliente.getDireccion().length() == 0){
			mensaje = mensaje + "Campo direcci�n vac�o.\n";
		}
		if("".equals(cliente.getCorreo()) || cliente.getCorreo().length() == 0){
			mensaje = mensaje + "Campo email vac�o.\n";
		}
		if("".equals(usuario.getUsuario()) || usuario.getUsuario().length() == 0){
			mensaje = mensaje + "Campo usuario vac�o.\n";
		}
		if("".equals(usuario.getClave()) || usuario.getClave().length() == 0){
			mensaje = mensaje + "Campo contrase�a vac�o.\n";
		}
		
		if("".equals(mensaje) || mensaje.length() == 0){
			return true;
		}
		response.getWriter().append(mensaje);
		return false;
	}

}
