package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImpl.TipoUsuarioDaoImpl;
import entidades.TipoUsuario;

/**
 * Servlet implementation class ServletTipoUsuario
 */
@WebServlet("/ServletTipoUsuario")
public class ServletTipoUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletTipoUsuario() {
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
		if (request.getParameter("Param") != null) {
			// Entra por haber echo click en el hyperlink mostrar usuarios
			TipoUsuarioDaoImpl tipoUsuarioDao = new TipoUsuarioDaoImpl();
			ArrayList<TipoUsuario> lista = tipoUsuarioDao.obtenerTodos();

			request.setAttribute("listaU", lista);

			RequestDispatcher rd = request.getRequestDispatcher("/ListarTipoUsuario.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
