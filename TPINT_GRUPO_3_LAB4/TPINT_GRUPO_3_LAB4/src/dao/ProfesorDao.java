package dao;

import java.util.ArrayList;

import entidades.Alumno;
import entidades.Profesor;

public interface ProfesorDao {
	
	public boolean agregarProfesor(Profesor profesorAgregado);
	public ArrayList<Profesor> listarProfesores();
	public Profesor ObtenerProfesor (int Legajo);
	public boolean modificarProfesor(Profesor profesorModificado);
	public boolean eliminarProfesor(int Legajo);
}
