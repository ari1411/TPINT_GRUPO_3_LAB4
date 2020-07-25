package dao;

import java.util.ArrayList;

import entidades.Alumno;
import entidades.AlumnosPorCursos;

public interface AlumnoPorCursoDao {
	public ArrayList<AlumnosPorCursos> readAll();

	public boolean agregarAlumno(AlumnosPorCursos NotaDelAgregado);

	public ArrayList<AlumnosPorCursos> ObtenerCalificacionesAlumnos(int IdCurso);

	public boolean cargarNotaAlumno(AlumnosPorCursos estadoAlumno);

}
