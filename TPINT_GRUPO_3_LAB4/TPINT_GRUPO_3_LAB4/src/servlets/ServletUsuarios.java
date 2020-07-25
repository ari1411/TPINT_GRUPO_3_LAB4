package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daoImpl.UsuarioDaoImpl;
import entidades.Alumno;
import entidades.Curso;
import entidades.Profesor;
import entidades.TipoUsuario;
import entidades.Usuario;
import negocio.ProfesorNegocio;
import negocio.TipoUsuarioNegocio;
import negocioImpl.ProfesorNegocioImpl;
import negocioImpl.TipoUsuarioNegocioImpl;

/**
 * Servlet implementation class ServletUsuarios
 */
@WebServlet("/ServletUsuarios")
public class ServletUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UsuarioDaoImpl UsuarioDao = new UsuarioDaoImpl();
	ProfesorNegocio profesorNeg = new ProfesorNegocioImpl();
	TipoUsuarioNegocio tipoUsuarioNeg = new TipoUsuarioNegocioImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletUsuarios() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		
		//Listar Usuarios
		if (request.getParameter("Param") != null) {
			// Entra por haber echo click en el hyperlink mostrar usuarios
			UsuarioDaoImpl UsuarioDao = new UsuarioDaoImpl();
			ArrayList<Usuario> lista = UsuarioDao.obtenerTodos();

			request.setAttribute("listaUsuarios", lista);

			RequestDispatcher rd = request.getRequestDispatcher("/ListarUsuarios.jsp");
			rd.forward(request, response);
		}

		//Carga pantalla AgregarUsuario
		if (request.getParameter("AddUser") != null) {
			ArrayList<Profesor> lProfesor = profesorNeg.listarProfesoresSinUsuarios();
			ArrayList<TipoUsuario> listaTipoUsuario = tipoUsuarioNeg.obtenerTodos();

			request.setAttribute("listaProfes", lProfesor);
			request.setAttribute("listaTipoUsuario", listaTipoUsuario);
			RequestDispatcher rd = request.getRequestDispatcher("/agregarUsuario.jsp");
			rd.forward(request, response);
		}
		
		// PARA ELIMINAR UN USUARIO
		if (request.getParameter("deleteUser") != null) {
			UsuarioDao.eliminarUsuario(Integer.parseInt(request.getParameter("deleteUser")));
			int cant = 1;

			ArrayList<Usuario> lista = UsuarioDao.obtenerTodos();
			request.setAttribute("listaUsuarios", lista);
			request.setAttribute("EliminadoUsuario", cant);

			RequestDispatcher rd = request.getRequestDispatcher("/ListarUsuarios.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		// Agregar Usuario
		int filas = 0;
		if (request.getParameter("btnGuardar") != null) {
			Usuario user = new Usuario();
			TipoUsuario tipoUser = new TipoUsuario();
//			String a = request.getParameter("cmbTipoUsuario");
			tipoUser.setId(2);

			user.setUsername(request.getParameter("txtUsuario"));
			user.setPass(request.getParameter("txtClave"));
			user.setTipoUsuario(tipoUser);
			user.setLegajo(Integer.parseInt(request.getParameter("cmbProfesor")));
			user.setEstado(1);

			/* VALIDA SI EL USERNAME EXISTE */
			String message = "";
			
			boolean existeLegajo = UsuarioDao.validarLegajo(Integer.parseInt(request.getParameter("cmbProfesor").toString()));
			System.out.println(existeLegajo);

			if (existeLegajo == true) {
				message = "Ya existe un usuario para ese profesor. Por favor ingrese otro";
				request.setAttribute("msj", message);
				ArrayList<Profesor> lProfesor = profesorNeg.listarProfesoresSinUsuarios();
				ArrayList<TipoUsuario> listaTipoUsuario = tipoUsuarioNeg.obtenerTodos();

				request.setAttribute("listaProfes", lProfesor);
				request.setAttribute("listaTipoUsuario", listaTipoUsuario);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/agregarUsuario.jsp");
				dispatcher.forward(request, response);
			}else {
			boolean existe = UsuarioDao.validarUserName(request.getParameter("txtUsuario"));
			System.out.println(existe);

			if (existe != true) {
				if (UsuarioDao.agregarUsuario(user) != false) {
					filas = 1;
				}
			} else {
				message = "Ya existe ese usuario. Por favor ingrese otro";
				request.setAttribute("msj", message);
				ArrayList<Profesor> lProfesor = profesorNeg.listarProfesoresSinUsuarios();
				ArrayList<TipoUsuario> listaTipoUsuario = tipoUsuarioNeg.obtenerTodos();

				request.setAttribute("listaProfes", lProfesor);
				request.setAttribute("listaTipoUsuario", listaTipoUsuario);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/agregarUsuario.jsp");
				dispatcher.forward(request, response);
			}}
			
			

			if (filas == 1) {

				request.setAttribute("cantFilas", filas);
				UsuarioDaoImpl UsuarioDao = new UsuarioDaoImpl();
				ArrayList<Usuario> lista = UsuarioDao.obtenerTodos();

				request.setAttribute("listaUsuarios", lista);

				RequestDispatcher rd = request.getRequestDispatcher("/ListarUsuarios.jsp");
				rd.forward(request, response);
			}
		}
		// Ingresar
		if (request.getParameter("btnIngresar") != null) {
			// Entra por haber echo click en el hyperlink mostrar usuarios
			UsuarioDaoImpl UsuarioDao = new UsuarioDaoImpl();
			Usuario usuario = UsuarioDao.obtenerUsuario(request.getParameter("txtUsuario"),
					request.getParameter("txtClave"));
			System.out.println(usuario);
			session.setAttribute("Usuario2", usuario);
			if (usuario.getId() != 0) {
				session.setAttribute("Session_user", usuario.getUsername());
				session.setAttribute("Session_type", usuario.getTipoUsuario().getTipo());
				session.setAttribute("Session_Legajo", usuario.getLegajo());
				session.setAttribute("Session_Id", usuario.getId());
				// session.setAttribute("Session_user", request.getParameter("txtUsuario"));
				if (usuario.getTipoUsuario().getId() == 1) {
					request.getRequestDispatcher("Home.jsp").forward(request, response);
				} else if (usuario.getTipoUsuario().getId() != 1) {
					request.getRequestDispatcher("ServletCurso?listCoursesProfessor=0").forward(request, response);

				}
			} else {

				
				  int error=1;
				  request.setAttribute("UsuarioYaExiste", error);
				  request.getRequestDispatcher("login.jsp").forward(request, response);
				 

			}
		}

		// PARA CAMBIAR LA CLAVE DEL USUARIO
		if (request.getParameter("BtnLegajo") != null) {

			int cont = 0;
			String clave = request.getParameter("contraseñaModificar");
			int idUsuario = Integer.parseInt(request.getParameter("iduserAModificar"));

			if (UsuarioDao.actualizarClave(clave, idUsuario) == true) {
				cont = 1;
				request.setAttribute("contraseñaU", cont);

				UsuarioDaoImpl UsuarioDao = new UsuarioDaoImpl();
				ArrayList<Usuario> lista = UsuarioDao.obtenerTodos();

				request.setAttribute("listaUsuarios", lista);

				RequestDispatcher rd = request.getRequestDispatcher("/ListarUsuarios.jsp");
				rd.forward(request, response);
			}

		}

	}
}
