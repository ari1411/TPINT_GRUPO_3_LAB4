package negocioImpl;

import java.util.ArrayList;

import dao.ProfesorDao;
import daoImpl.ProfesorDaoImpl;
import entidades.Profesor;
import negocio.ProfesorNegocio;

public class ProfesorNegocioImpl implements ProfesorNegocio {
	ProfesorDaoImpl profesorDao = new ProfesorDaoImpl();

	@Override
	public ArrayList<Profesor> listarProfe() {
		return profesorDao.listarProfesores();
	}
	
	public ArrayList<Profesor> listarProfesoresSinUsuarios(){
		return profesorDao.listarProfesoresSinUsuarios();
	}

	@Override
	public boolean agregarProfesor(Profesor profesorAgregado) {
		boolean estado=false;
		
		if(profesorAgregado.getNombre().trim().length()>0 &&
		   profesorAgregado.getApellido().trim().length()>0 &&
		   profesorAgregado.getDni().trim().length()>0 &&
		   profesorAgregado.getFechaNac()!= null &&
		   profesorAgregado.getDireccion().trim().length()>0 &&
		   profesorAgregado.getLocalidad().getId() !=0 &&
		   profesorAgregado.getTelefono().trim().length()>0 &&
		   profesorAgregado.getMail().trim().length()>0	)
		   {
			estado = profesorDao.agregarProfesor(profesorAgregado); 
			}
		return estado;
	}

}