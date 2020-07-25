package negocio;

import java.util.ArrayList;

import entidades.Materia;

public interface MateriaNegocio {
	
	public Materia buscarMateria(int Id);
	public ArrayList<Materia> listarMaterias();

}