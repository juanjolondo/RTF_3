package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		Cliente cliente = new Cliente();
		Usuario usu = new Usuario();
		
		if(validarCampos(cliente,usu)){
			
			if(usuarioNeg.existe(usuario) || clienteNeg.existe(idUsuario)){
				response.getWriter().append("El cliente o el usuario ya se encuentra registrados.");
			}
			else if(usuarioNeg.guardar(usu,1) || clienteNeg.guardar(cliente)){
				response.getWriter().append("El usuario ha sido registrado.");
				getServletContext().getRequestDispatcher("/IniciarSesion.jsp").forward(request, response);
			}
		}
		
	}
	
	private boolean validarCampos(Cliente cliente, Usuario usuario){
		return true;
	}

}
