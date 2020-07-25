
package dao;
import java.util.ArrayList;
import entidades.Alumno;

public interface AlumnoDao {

	public boolean agregarAlumno(Alumno alumnoAgregado);
	public ArrayList<Alumno> readAll();
	public Alumno ObtenerAlumno (int Legajo);
	public boolean modificarAlumno(Alumno alumnoModificado);
	public boolean eliminarAlumno(int Legajo);
	public ArrayList<Alumno> getAlumnosInscriptos(int IdCurso);
	public boolean verifEstaInscripto(String legajoAlumno, int idCurso);
	public boolean verifEstaCursandoMateria(String legajoAlumno, int idMateria, int idTurno, int cuatrimestre, int anio);
}


