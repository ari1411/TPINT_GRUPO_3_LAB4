package negocio;

import java.util.ArrayList;

import entidades.Curso;

public interface CursoNegocio {
	public Curso buscarCurso(int Id);

	public ArrayList<Curso> listarCursos();

	public ArrayList<Curso> listarCursos(int LegajoProf);

	public boolean eliminarCurso(int Id);

	public boolean GrabarCurso(Curso curso);

	public int UltimoId();
	
	public boolean InsertarAlumnoAlCurso(int idCurso, String legajoAlumno);
	
	public boolean ActualizarCurso(Curso curso);
	
	public boolean EliminarAlumnoDelCurso(int legajoAlumno, int idCurso);
	
	public int VerificarExisteCurso(Curso curso);
	
	public boolean VerificarAlumnoEstaInscripto(int idCurso, String legajoAlumno);
	
	public boolean EliminarCursosdesdeProfesor(int legajoProf);
	
	public ArrayList<Curso> filtroListarCursos(int legajoProfesor, Curso curso);
}
