
package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImpl.AlumnoDaoImpl;
import daoImpl.AlumnoPorCursoDaoImpl;
import entidades.Alumno;
import entidades.AlumnosPorCursos;
import entidades.Curso;
import entidades.EstadoAcademico;
import negocio.AlumnoPorCursoNegocio;
import negocio.CursoNegocio;
import negocioImpl.AlumnoPorCursoNegocioImpl;
import negocioImpl.CursoNegocioImpl;

/**
 * Servlet implementation class ServletAlumXcurso
 */
@WebServlet("/ServletAlumXcurso")
public class ServletAlumXcurso extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletAlumXcurso() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AlumnoPorCursoNegocio alumCurNeg = new AlumnoPorCursoNegocioImpl();
		CursoNegocio cursoNeg = new CursoNegocioImpl();

		// Lista los alumnos con las notas
		if (request.getParameter("uploadNotes") != null) {
			int CursoId = Integer.parseInt(request.getParameter("uploadNotes").toString());
			ArrayList<AlumnosPorCursos> listaAlum = alumCurNeg.ObtenerCalificacionesAlumnos(CursoId);
			Curso curso = cursoNeg.buscarCurso(CursoId);
			
			request.setAttribute("listaAlumNotas", listaAlum);
			request.setAttribute("InfoCurso", curso);
			request.setAttribute("CursoId", CursoId);
			RequestDispatcher rd = request.getRequestDispatcher("/cargarNota.jsp");
			rd.forward(request, response);
		}

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Guarda los alumnos con las notas
		if (request.getParameter("btnActualizarNotas") != null) {
			AlumnosPorCursos aux = null;
			Alumno alumno = null;
			Curso curso = null;
			EstadoAcademico EstadoAc = null;
			AlumnoPorCursoNegocio alumCurNeg = new AlumnoPorCursoNegocioImpl();
			String Msj = "";
			boolean FalloGrabacion = false;
			String Algo = request.getParameter("txtIdCurso");
			int IdCurso = Integer.parseInt(Algo);
			String[] ListaLegajoAlum = request.getParameterValues("LegAlumno");
			String[] ListaParcial1 = request.getParameterValues("notaParcial1");
			String[] ListaParcial2 = request.getParameterValues("notaParcial2");
			String[] ListaRecuperatorio1 = request.getParameterValues("Recuperatorio1");
			String[] ListaRecuperatorio2 = request.getParameterValues("Recuperatorio2");
			String[] ListaEstadoAcademico = request.getParameterValues("cmbEstadoAc");
			int CantidadRegistros = ListaEstadoAcademico.length;
			for (int x = 0; x < CantidadRegistros; x++) {
				aux = new AlumnosPorCursos();
				EstadoAc = new EstadoAcademico();
				alumno = new Alumno();
				curso = new Curso();
				alumno.setLegajo(Integer.parseInt(ListaLegajoAlum[x].toString()));
				aux.setAlumno(alumno);
				curso.setId(IdCurso);
				aux.setCurso(curso);
				if (ListaParcial1[x].toString() == "")
					aux.setParcial1(0);
				else
					aux.setParcial1(Integer.parseInt(ListaParcial1[x].toString()));

				if (ListaParcial2[x].toString() == "")
					aux.setParcial2(0);
				else
					aux.setParcial2(Integer.parseInt(ListaParcial2[x].toString()));

				if (ListaRecuperatorio1[x].toString() == "")
					aux.setRecuperatorio1(0);
				else
					aux.setRecuperatorio1(Integer.parseInt(ListaRecuperatorio1[x].toString()));

				if (ListaRecuperatorio2[x].toString() == "")
					aux.setRecuperatorio2(0);
				else
					aux.setRecuperatorio2(Integer.parseInt(ListaRecuperatorio2[x].toString()));

				EstadoAc.setId(Integer.parseInt(ListaEstadoAcademico[x].toString()));
				aux.setEstadoAca(EstadoAc);

				if (!alumCurNeg.cargarNotaAlumno(aux)) {
					FalloGrabacion = true;
					break;
				}
			}
			if (FalloGrabacion) {
				Msj = "Error: Fallo al actualizar las notas de uno o varios alumnos.";
				ArrayList<AlumnosPorCursos> listaAlum = alumCurNeg.ObtenerCalificacionesAlumnos(
						Integer.parseInt(request.getParameterValues("txtIdCurso").toString()));
				request.setAttribute("listaAlumNotas", listaAlum);
				request.setAttribute("Mensaje", Msj);
				RequestDispatcher rd = request.getRequestDispatcher("/cargarNota.jsp");
				rd.forward(request, response);
			} else {
				Msj = "La grabación se realizó correctamente.";
				request.setAttribute("Mensaje", Msj);
				RequestDispatcher rd = request.getRequestDispatcher("ServletCurso?listCoursesProfessor=0");
				rd.forward(request, response);
			}
		}

		doGet(request, response);
	}

}
