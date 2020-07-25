package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImpl.ProvinciaDaoImpl;
import entidades.Provincia;

/**
 * Servlet implementation class ServletProvincia
 */
@WebServlet("/ServletProvincia")
public class ServletProvincia extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public ServletProvincia() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("Param")!=null) 
		{
			ProvinciaDaoImpl provDao=new ProvinciaDaoImpl();
			ArrayList<Provincia> listProvi=(ArrayList<Provincia>)provDao.listarProvincia();
			
			request.setAttribute("listaProvDao",listProvi);
			RequestDispatcher rd= request.getRequestDispatcher("ServletsLocalidad");
			rd.forward(request, response);
		}
		/*response.getWriter().append("Served at: ").append(request.getContextPath());*/
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
