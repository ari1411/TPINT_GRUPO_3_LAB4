package negocio;

import java.util.ArrayList;

import entidades.Profesor;

public interface ProfesorNegocio {
	
	public ArrayList<Profesor> listarProfe();
	
	public ArrayList<Profesor> listarProfesoresSinUsuarios();
	
	public boolean agregarProfesor(Profesor profesorAgregado);
	
}
