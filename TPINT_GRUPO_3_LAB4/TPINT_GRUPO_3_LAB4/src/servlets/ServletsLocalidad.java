package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entidades.Localidad;
import entidades.Provincia;
import dao.LocalidadDao;
import daoImpl.LocalidadDaoImpl;

/**
 * Servlet implementation class ServletsLocalidad
 */
@WebServlet("/ServletsLocalidad")
public class ServletsLocalidad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ServletsLocalidad() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//esto funciona para listar todas las localidades 
		if(request.getParameter("Param")!=null) {
     	LocalidadDaoImpl LocDao=new LocalidadDaoImpl();
		ArrayList<Localidad> listaLoc=(ArrayList<Localidad>) LocDao.obtenerListLocalidad();
		request.setAttribute("listaLocDao",listaLoc);
		
		//prueba para listar localidades por provincia
		//if(request.getParameter("Param")!=null) {
			//LocalidadDaoImpl LocDaoImpl=new LocalidadDaoImpl();
			//Provincia prov=new Provincia();
		//	int provincia=Integer.parseInt(request.getParameter("cmbProvincia"));
			//prov.getId();
			//ArrayList<Localidad> listaLoc = (ArrayList<Localidad>) LocDaoImpl.ObtenerLocalidadPorProvincia(provincia);
		//	request.setAttribute("listaLocDao", listaLoc);
		
		
		if("Profesor".equals(request.getParameter("Param"))) {
		RequestDispatcher rdProf= request.getRequestDispatcher("/agregarProfesor.jsp");
		rdProf.forward(request, response);
		}
		else if("Alumno".equals(request.getParameter("Param"))) {
		RequestDispatcher rd= request.getRequestDispatcher("/agregarAlumno.jsp");
		rd.forward(request, response);
		}
	 }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
		LocalidadDaoImpl locDaoImpl = new LocalidadDaoImpl();
		
		String Provinciaid = request.getParameter("Provinciaid");
		if (Provinciaid != null) {
			
			  response.setContentType("application/json");
			  response.setCharacterEncoding("UTF-8");
			 
			
			List<Localidad> listalocalidades = (List<Localidad>) locDaoImpl.ObtenerLocalidadPorProvincia(Integer.parseInt(Provinciaid));
			
			
			Gson gson = new Gson();
		String json = gson.toJson(listalocalidades);
		PrintWriter out = response.getWriter();
			out.print(json);
			out.flush();			
			
			 
		}
	}

}
