package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImpl.ReporteDaoImpl;
import entidades.Reporte;

/**
 * Servlet implementation class ServletReporte
 */
@WebServlet("/ServletReporte")
public class ServletReporte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletReporte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//reporte
		ReporteDaoImpl reporteDao = new ReporteDaoImpl();
		
		  if(request.getParameter("btn-reporte") != null) {
		  
			  int materia=0;
			  int Cuatri=0;
			  int anio=0;
			  int reporte=0;
			  
			  if(request.getParameter("cboTipo")!=null) { reporte= Integer.parseInt(request.getParameter("cboTipo"));}
			  if(request.getParameter("cboMateria")!=null) { materia= Integer.parseInt(request.getParameter("cboMateria"));}
			  if(request.getParameter("cboCuatrimestre")!=null) {Cuatri= Integer.parseInt(request.getParameter("cboCuatrimestre"));}
			  if(request.getParameter("cdoAnio")!=null) {anio= Integer.parseInt(request.getParameter("cdoAnio"));}
			  
			  
		  ArrayList<Reporte> listReporte = reporteDao.obtenerDatosNotas(materia,Cuatri,anio,reporte);
//		  System.out.println(listReporte);
		  request.setAttribute("tipoReporte", reporte);
		  request.setAttribute("listaReporte", listReporte);
		  RequestDispatcher rd = request.getRequestDispatcher("/reporte.jsp");
	      rd.forward(request, response);
	      }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
