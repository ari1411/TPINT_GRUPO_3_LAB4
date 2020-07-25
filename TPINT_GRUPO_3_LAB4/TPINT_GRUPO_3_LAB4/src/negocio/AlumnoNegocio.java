package negocio;
import java.util.ArrayList;
import java.util.List;

import entidades.Alumno;

public interface AlumnoNegocio  {

	public boolean agregarAlumno(Alumno alumnoAgregado);
	
	public ArrayList<Alumno> readAll();
	
	public ArrayList<Alumno> getAlumnosInscriptos(int IdCurso);
	
	public boolean verifEstaInscripto(String legajoAlumno, int idCurso);
	
	public boolean verifEstaCursandoMateria(String legajoAlumno, int idMateria, int idTurno, int cuatrimestre, int anio);
}
