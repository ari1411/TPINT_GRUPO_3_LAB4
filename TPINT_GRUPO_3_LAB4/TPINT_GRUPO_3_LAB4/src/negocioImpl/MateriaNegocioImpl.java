package negocioImpl;

import java.util.ArrayList;

import dao.MateriaDao;
import daoImpl.MateriaDaoImpl;
import entidades.Materia;
import negocio.MateriaNegocio;

public class MateriaNegocioImpl implements MateriaNegocio {
	
	private MateriaDao materiaDao=new MateriaDaoImpl();

	@Override
	public Materia buscarMateria(int Id) {
		Materia materia = new Materia();
		materia = materiaDao.buscarMateria(Id);
		return materia;
	}

	@Override
	public ArrayList<Materia> listarMaterias() {
		ArrayList<Materia> lMateria = materiaDao.listarMaterias();
		return lMateria;
	}

}
