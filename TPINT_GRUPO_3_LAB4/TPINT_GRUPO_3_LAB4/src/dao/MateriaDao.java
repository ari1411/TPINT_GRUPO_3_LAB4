package dao;

import java.util.ArrayList;

import entidades.Materia;

public interface MateriaDao {
	
	public Materia buscarMateria(int Id);
	
	public ArrayList<Materia> listarMaterias();
	
}
