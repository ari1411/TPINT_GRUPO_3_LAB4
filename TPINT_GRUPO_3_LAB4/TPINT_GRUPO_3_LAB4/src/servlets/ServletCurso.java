package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Alumno;
import entidades.Curso;
import entidades.Materia;
import entidades.Profesor;
import entidades.Turno;
import negocio.AlumnoNegocio;
import negocio.CursoNegocio;
import negocioImpl.AlumnoNegocioImpl;
import negocioImpl.CursoNegocioImpl;
import negocio.MateriaNegocio;
import negocio.ProfesorNegocio;
import negocio.TurnoNegocio;
import negocioImpl.MateriaNegocioImpl;
import negocioImpl.ProfesorNegocioImpl;
import negocioImpl.TurnoNegocioImpl;

@WebServlet("/ServletCurso")
public class ServletCurso extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletCurso() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CursoNegocio cursoNeg = new CursoNegocioImpl();
		AlumnoNegocio alumnoNeg = new AlumnoNegocioImpl();
		ProfesorNegocio profesorNeg = new ProfesorNegocioImpl();
		MateriaNegocio materiaNeg = new MateriaNegocioImpl();
		TurnoNegocio turnoNeg = new TurnoNegocioImpl();

		// System.out.println(request.getParameter("listCoursesProfessor"));

		// BOTON CURSOS DEL MENU, LISTA LOS CURSOS
		if (request.getParameter("listCourses") != null) {
			String msj;
			ArrayList<Curso> lCursos = (ArrayList<Curso>) cursoNeg.listarCursos();

			if (request.getAttribute("Mensaje") != null) {
				msj = request.getAttribute("Mensaje").toString();
				request.setAttribute("Mensaje", msj);
			}

			request.setAttribute("listaCursos", lCursos);
			RequestDispatcher rd = request.getRequestDispatcher("/listarCurso.jsp");
			rd.forward(request, response);
		}

		// BOTON AGREGAR CURSO (SOLICITA LOS DATOS DEL CURSO)
		if (request.getParameter("AddCourses") != null) {
			ArrayList<Materia> lMateria = materiaNeg.listarMaterias();
			ArrayList<Turno> lTurno = turnoNeg.listarTurnos();
			ArrayList<Alumno> lAlum = alumnoNeg.readAll();
			ArrayList<Profesor> lProfesor = profesorNeg.listarProfe();

			request.setAttribute("listaMaterias", lMateria);
			request.setAttribute("ListaTurnos", lTurno);
			request.setAttribute("ListaAlumnos", lAlum);
			request.setAttribute("listaProfes", lProfesor);
			RequestDispatcher rd = request.getRequestDispatcher("/agregarCurso.jsp");
			rd.forward(request, response);
		}

		// BOTON EDITAR CURSO, MUESTRA LA INFORMACION A EDITAR
		if (request.getParameter("editCourse") != null) {
			Curso curso = cursoNeg.buscarCurso(Integer.parseInt(request.getParameter("editCourse")));
			ArrayList<Materia> lMateria = (ArrayList<Materia>) materiaNeg.listarMaterias();
			ArrayList<Turno> lTurno = turnoNeg.listarTurnos();
			ArrayList<Alumno> lAlumInsc = alumnoNeg
					.getAlumnosInscriptos(Integer.parseInt(request.getParameter("editCourse")));
			ArrayList<Alumno> lAlum = alumnoNeg.readAll();
			ArrayList<Profesor> lProfesor = profesorNeg.listarProfe();

			request.setAttribute("CursoModif", curso);
			request.setAttribute("listaMaterias", lMateria);
			request.setAttribute("ListaTurnos", lTurno);
			request.setAttribute("ListaAlumnosInsc", lAlumInsc);
			request.setAttribute("ListaAlumnos", lAlum);
			request.setAttribute("listaProfes", lProfesor);
			RequestDispatcher rd = request.getRequestDispatcher("/modificarCurso.jsp");
			rd.forward(request, response);
		}

		// BOTON ELIMINAR CURSO, RECIBE EL ID PARA MOSTRAR LA INFORMACION Y CONFIRMAR LA
		// ELIMINACION
		if (request.getParameter("deleteCourse") != null) {
			Curso curso = cursoNeg.buscarCurso(Integer.parseInt(request.getParameter("deleteCourse")));
			ArrayList<Alumno> alum = alumnoNeg
					.getAlumnosInscriptos(Integer.parseInt(request.getParameter("deleteCourse")));

			request.setAttribute("CursoElim", curso);
			request.setAttribute("ListaAlumnos", alum);
			RequestDispatcher rd = request.getRequestDispatcher("/eliminarCurso.jsp");
			rd.forward(request, response);
		}

		// BOTON CONFIRMA ELIMINAR, CAMBIA EL ESTADO DEL CURSO
		if (request.getParameter("deleteConfirmedCourse") != null) {
			String Msj = "ERROR: No se pudo eliminar el curso";
			if (cursoNeg.eliminarCurso(Integer.parseInt(request.getParameter("deleteConfirmedCourse")))) {
				Msj = "Curso eliminado correctamente.";
			}
			ArrayList<Curso> lCursos = (ArrayList<Curso>) cursoNeg.listarCursos();

			request.setAttribute("Mensaje", Msj);
			request.setAttribute("listaCursos", lCursos);
			RequestDispatcher rd = request.getRequestDispatcher("/listarCurso.jsp");
			rd.forward(request, response);
		}

		// Listar Cursos del usuario tipo profesor
		if (request.getParameter("listCoursesProfessor") != null) {
			String msj;
			ArrayList<Materia> lMateria = materiaNeg.listarMaterias();
			ArrayList<Turno> lTurno = turnoNeg.listarTurnos();
			HttpSession session = request.getSession();

			int LegajoProf = Integer.parseInt(session.getAttribute("Session_Legajo").toString());
			ArrayList<Curso> lCursos = (ArrayList<Curso>) cursoNeg.listarCursos(LegajoProf);

			if (request.getAttribute("Mensaje") != null) {
				msj = request.getAttribute("Mensaje").toString();
				request.setAttribute("Mensaje", msj);
			}

			request.setAttribute("listaMaterias", lMateria);
			request.setAttribute("ListaTurnos", lTurno);
			request.setAttribute("listaCursos", lCursos);
			RequestDispatcher rd = request.getRequestDispatcher("/listarCursoProfesor.jsp");
			rd.forward(request, response);
		}

		if (request.getParameter("btn-filtrarProf") != null) {
			HttpSession session = request.getSession();
			ArrayList<Materia> lMateria = materiaNeg.listarMaterias();
			ArrayList<Turno> lTurno = turnoNeg.listarTurnos();
			Curso curs = new Curso();
			String aux = "";

			if (request.getParameter("cmbMateria") != null) {
				aux = request.getParameter("cmbMateria");
				curs.setIdMateria(Integer.parseInt(aux));
			}
			if (request.getParameter("cmbTurno") != null) {
				aux = request.getParameter("cmbTurno");
				curs.setIdTurno(Integer.parseInt(aux));
			}
			if (request.getParameter("cmbCuatrimestre") != null) {
				aux = request.getParameter("cmbCuatrimestre");
				curs.setCuatrimestre(Integer.parseInt(aux));
			}
			if (request.getParameter("cmbAnio") != null) {
				aux = request.getParameter("cmbAnio");
				curs.setAnio(Integer.parseInt(aux));
			}

			int LegajoProf = Integer.parseInt(session.getAttribute("Session_Legajo").toString());
			ArrayList<Curso> lCursos = (ArrayList<Curso>) cursoNeg.filtroListarCursos(LegajoProf, curs);
			if (curs.getIdMateria() > 0) {
				Materia mat = materiaNeg.buscarMateria(curs.getIdMateria());
				curs.setMateria(mat.getNombre());
			}

			if (curs.getIdTurno() > 0) {
				Turno turn = turnoNeg.buscarTurno(curs.getIdTurno());
				curs.setTurno(turn.getTurno());
			}

			request.setAttribute("CursoAux", curs);
			request.setAttribute("listaMaterias", lMateria);
			request.setAttribute("ListaTurnos", lTurno);
			request.setAttribute("listaCursos", lCursos);
			RequestDispatcher rd = request.getRequestDispatcher("/listarCursoProfesor.jsp");
			rd.forward(request, response);
		}

		// Mostrar datos y alumnos del curso seleccionado por el profesor
		if (request.getParameter("showCourse-professor") != null) {
			Curso curso = cursoNeg.buscarCurso(Integer.parseInt(request.getParameter("showCourse-professor")));
			ArrayList<Alumno> alum = alumnoNeg
					.getAlumnosInscriptos(Integer.parseInt(request.getParameter("showCourse-professor")));

			request.setAttribute("CursoElim", curso);
			request.setAttribute("ListaAlumnos", alum);
			RequestDispatcher rd = request.getRequestDispatcher("/mostrarCursoProfesor.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CursoNegocio cursoNeg = new CursoNegocioImpl();
		AlumnoNegocio alumnoNeg = new AlumnoNegocioImpl();
		ProfesorNegocio profesorNeg = new ProfesorNegocioImpl();
		MateriaNegocio materiaNeg = new MateriaNegocioImpl();
		TurnoNegocio turnoNeg = new TurnoNegocioImpl();

		// BOTON GRABAR CURSO (GRABA EN LA BD)
		if (request.getParameter("btn-GrabarCurso") != null) {
			Boolean CancelarGrabado = false;
			int CantidadAlumSelec = 0;
			String Msj = "";
			Alumno A = null;
			String[] lAlumnosInscriptos = null;
			int IdCurso;
			Curso curs = new Curso();
			String aux = new String();
			aux = request.getParameter("cmbMateria");
			curs.setIdMateria(Integer.parseInt(aux));
			aux = request.getParameter("cmbTurno");
			curs.setIdTurno(Integer.parseInt(aux));
			aux = request.getParameter("cmbCuatrimestre");
			curs.setCuatrimestre(Integer.parseInt(aux));
			aux = request.getParameter("cmbAnio");
			curs.setAnio(Integer.parseInt(aux));
			aux = request.getParameter("cmbProfesor");
			curs.setLegajoProf(Integer.parseInt(aux));
			curs.setId(0);
			if (request.getParameterValues("cboxAlumno") != null) {
				lAlumnosInscriptos = request.getParameterValues("cboxAlumno");
				CantidadAlumSelec = lAlumnosInscriptos.length;
			}
			int VerificaCurso = cursoNeg.VerificarExisteCurso(curs);
			if (VerificaCurso == 0) {
				if (CantidadAlumSelec > 0) {

					if (cursoNeg.GrabarCurso(curs)) { // SI GRABARCURSO DEVUELVE TRUE, LA GRABACION DEL CURSO FUE
														// EXITOSA Y
														// PROCEDE A GRABAR LA LISTA DE ALUMNOS INSCRIPTOS
						IdCurso = cursoNeg.UltimoId(); // Obtengo el Id del curso Grabado
						for (int x = 0; x < CantidadAlumSelec; x++) {
							if (!cursoNeg.VerificarAlumnoEstaInscripto(IdCurso, lAlumnosInscriptos[x])) {
								if (!alumnoNeg.verifEstaCursandoMateria(lAlumnosInscriptos[x], curs.getIdMateria(),
										curs.getIdTurno(), curs.getCuatrimestre(), curs.getAnio())) {
									if (!cursoNeg.InsertarAlumnoAlCurso(curs.getId(), lAlumnosInscriptos[x])) {
										CancelarGrabado = true;
										Msj = "ERROR: Hubo un error al agregar uno o varios alumno/s.";
									}
								} else {
									CancelarGrabado = true;
									Msj += "ERROR: El alumno con legajo " + lAlumnosInscriptos[x]
											+ " ya esta cursando la materia al mismo tiempo con otro profesor.";
								}
							}
						}
					} else {
						CancelarGrabado = true;
						Msj = "ERROR: Hubo un error al crear curso.";
					}
				} else {
					CancelarGrabado = true;
					Msj = "ERROR: No selecciono ningún Alumno.";
				}
			} else {
				CancelarGrabado = true;
				Msj = "ERROR: Ya existe este curso con el mismo profesor.";
			}

			if (CancelarGrabado) {
				curs = cursoNeg.buscarCurso(VerificaCurso);
				ArrayList<Alumno> lAlumAux = new ArrayList<Alumno>();
				for (int x = 0; x < CantidadAlumSelec; x++) {
					A = new Alumno();
					A.setLegajo(Integer.parseInt(lAlumnosInscriptos[x].toString()));
					lAlumAux.add(A);
				}
				ArrayList<Materia> lMateria = (ArrayList<Materia>) materiaNeg.listarMaterias();
				ArrayList<Turno> lTurno = turnoNeg.listarTurnos();
				ArrayList<Alumno> lAlum = alumnoNeg.readAll();
				ArrayList<Profesor> lProfesor = profesorNeg.listarProfe();

				if (VerificaCurso > 0)
					request.setAttribute("CursoAux", curs);
				request.setAttribute("listaMaterias", lMateria);
				request.setAttribute("ListaTurnos", lTurno);
				request.setAttribute("ListaAlumnosAux", lAlumAux);
				request.setAttribute("ListaAlumnos", lAlum);
				request.setAttribute("listaProfes", lProfesor);
				request.setAttribute("Mensaje", Msj);
				RequestDispatcher rd = request.getRequestDispatcher("/agregarCurso.jsp");
				rd.forward(request, response);

			} else {
				Msj = "Curso creado correctamente.";
				ArrayList<Curso> lCursos = (ArrayList<Curso>) cursoNeg.listarCursos();

				request.setAttribute("listaCursos", lCursos);
				request.setAttribute("Mensaje", Msj);
				RequestDispatcher rd = request.getRequestDispatcher("/listarCurso.jsp");
				rd.forward(request, response);
			}
		}

		// BOTON GUARDAR EDICION
		if (request.getParameter("btn-EditarCurso") != null) {
			String Msj = "";
			String[] lNvaAlumnosInscriptos = null;
			int CantidadAlumSelec = 0;
			List<Alumno> lVjaAlumnosInscriptos;
			ArrayList<Alumno> lAlumAux = new ArrayList<Alumno>();
			Alumno A = null;
			Curso curs = new Curso();
			String aux = new String();
			Boolean CancelarGrabado = false;
			Boolean VerifAlumnosEstaOk = false;
			aux = request.getParameter("txtIdCurso");
			curs.setId(Integer.parseInt(aux));
			aux = request.getParameter("cmbMateria");
			curs.setIdMateria(Integer.parseInt(aux));
			aux = request.getParameter("cmbTurno");
			curs.setIdTurno(Integer.parseInt(aux));
			aux = request.getParameter("cmbCuatrimestre");
			curs.setCuatrimestre(Integer.parseInt(aux));
			aux = request.getParameter("cmbAnio");
			curs.setAnio(Integer.parseInt(aux));
			aux = request.getParameter("cmbProfesor");
			curs.setLegajoProf(Integer.parseInt(aux));
			if (request.getParameterValues("cboxAlumno") != null) {
				lNvaAlumnosInscriptos = request.getParameterValues("cboxAlumno");
				CantidadAlumSelec = lNvaAlumnosInscriptos.length;
			}
			int VerificaCurso = cursoNeg.VerificarExisteCurso(curs);
			if (VerificaCurso == 0) {
				if (CantidadAlumSelec > 0) {
					if (cursoNeg.ActualizarCurso(curs)) { // SI GRABARCURSO DEVUELVE TRUE, LA GRABACION DEL CURSO FUE
															// EXITOSA Y
															// PROCEDE A GRABAR LA LISTA DE ALUMNOS INSCRIPTOS
						lVjaAlumnosInscriptos = alumnoNeg.getAlumnosInscriptos(curs.getId());
						for (int x = 0; x < CantidadAlumSelec; x++) {
							if (!alumnoNeg.verifEstaInscripto(lNvaAlumnosInscriptos[x], curs.getId())) {
								if (!alumnoNeg.verifEstaCursandoMateria(lNvaAlumnosInscriptos[x], curs.getIdMateria(),
										curs.getIdTurno(), curs.getCuatrimestre(), curs.getAnio())) {
									if (!cursoNeg.InsertarAlumnoAlCurso(curs.getId(), lNvaAlumnosInscriptos[x])) {
										CancelarGrabado = true;
										Msj = "ERROR: Hubo un error al agregar uno o varios alumno/s.";
									}
								} else {
									CancelarGrabado = true;
									Msj += "ERROR: El alumno con legajo " + lNvaAlumnosInscriptos[x]
											+ " ya esta cursando la materia al mismo tiempo con otro profesor.";
								}
							}
						}

						for (Alumno alum : lVjaAlumnosInscriptos) {
							boolean existe = false;
							for (int i = 0; i < lNvaAlumnosInscriptos.length; i++) {
								if (alum.getLegajo() == Integer.parseInt(lNvaAlumnosInscriptos[i])) {
									existe = true;
								}
							}
							if (existe == false) {
								if (!cursoNeg.EliminarAlumnoDelCurso(alum.getLegajo(), curs.getId())) {
									CancelarGrabado = true;
									Msj = "ERROR: Hubo un error al eliminar uno o varios alumno/s.";
								}
							}
						}
					} else {
						CancelarGrabado = true;
						Msj = "ERROR: Hubo un error al modificar el curso.";
					}
				} else {
					CancelarGrabado = true;
					Msj = "ERROR: No selecciono ningún Alumno.";
				}
			} else {
				CancelarGrabado = true;
				Msj = "ERROR: Ya existe este curso con el mismo profesor.";
			}

			if (CancelarGrabado) {
				Curso curso = null;
				if (VerificaCurso == 0 || CantidadAlumSelec == 0) {
					curso = cursoNeg.buscarCurso(curs.getId());
				} else {
					curso = cursoNeg.buscarCurso(VerificaCurso);
				}
				curso.setId(curs.getId());
				for (int x = 0; x < CantidadAlumSelec; x++) {
					A = new Alumno();
					A.setLegajo(Integer.parseInt(lNvaAlumnosInscriptos[x].toString()));
					lAlumAux.add(A);
				}
				ArrayList<Materia> lMateria = (ArrayList<Materia>) materiaNeg.listarMaterias();
				ArrayList<Turno> lTurno = turnoNeg.listarTurnos();
				ArrayList<Alumno> lAlum = alumnoNeg.readAll();
				ArrayList<Profesor> lProfesor = profesorNeg.listarProfe();

				request.setAttribute("ListaAlumnosInsc", lAlumAux);
				request.setAttribute("CursoModif", curso);
				request.setAttribute("listaMaterias", lMateria);
				request.setAttribute("ListaTurnos", lTurno);
				request.setAttribute("ListaAlumnos", lAlum);
				request.setAttribute("listaProfes", lProfesor);
				request.setAttribute("Mensaje", Msj);
				RequestDispatcher rd = request.getRequestDispatcher("/modificarCurso.jsp");
				rd.forward(request, response);
			} else {
				Msj = "Curso Editado correctamente.";
				ArrayList<Curso> lCursos = (ArrayList<Curso>) cursoNeg.listarCursos();

				request.setAttribute("listaCursos", lCursos);
				request.setAttribute("Mensaje", Msj);
				RequestDispatcher rd = request.getRequestDispatcher("/listarCurso.jsp");
				rd.forward(request, response);
			}
		}

		doGet(request, response);
	}
}