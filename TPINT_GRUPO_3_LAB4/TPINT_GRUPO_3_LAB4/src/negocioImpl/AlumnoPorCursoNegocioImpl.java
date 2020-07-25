package negocioImpl;

import java.util.ArrayList;

import dao.AlumnoPorCursoDao;
import daoImpl.AlumnoPorCursoDaoImpl;
import entidades.AlumnosPorCursos;
import negocio.AlumnoPorCursoNegocio;

public class AlumnoPorCursoNegocioImpl implements AlumnoPorCursoNegocio {

	AlumnoPorCursoDao AlumCursNeg = new AlumnoPorCursoDaoImpl();

	@Override
	public ArrayList<AlumnosPorCursos> ObtenerCalificacionesAlumnos(int IdCurso) {
		ArrayList<AlumnosPorCursos> lista = AlumCursNeg.ObtenerCalificacionesAlumnos(IdCurso);
		return lista;
	}

	@Override
	public boolean cargarNotaAlumno(AlumnosPorCursos estadoAlumno) {
		boolean notasActualizadas = AlumCursNeg.cargarNotaAlumno(estadoAlumno);
		return notasActualizadas;
	}

}
