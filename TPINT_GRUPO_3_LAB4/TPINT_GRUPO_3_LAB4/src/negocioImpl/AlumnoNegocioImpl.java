package negocioImpl;
import java.util.ArrayList;
import java.util.List;
import dao.AlumnoDao;
import daoImpl.AlumnoDaoImpl;
import entidades.Alumno;
import negocio.AlumnoNegocio;

public class AlumnoNegocioImpl implements AlumnoNegocio {
	
	AlumnoDao alumDao = new AlumnoDaoImpl();
	
	//@Override
	public boolean agregarAlumno(Alumno alumnoAgregado) {
		boolean estado = false;
		if(alumnoAgregado.getNombre().trim().length()>0 && 
		   alumnoAgregado.getApellido().trim().length()>0 &&
		   alumnoAgregado.getDni().trim().length()>0 &&
//		   alumnoAgregado.getLegajo().trim().length()>0 &&
		   alumnoAgregado.getFechaNac()!= null &&
		   alumnoAgregado.getDireccion().trim().length()>0 &&
		   alumnoAgregado.getLocalidad().getId() !=0 &&
		   alumnoAgregado.getTelefono().trim().length()>0 &&
		   alumnoAgregado.getMail().trim().length()>0
		  ){
			estado = alumDao.agregarAlumno(alumnoAgregado); 
			}
		return estado;
	}

	@Override
	public ArrayList<Alumno> getAlumnosInscriptos(int IdCurso) {
		return alumDao.getAlumnosInscriptos(IdCurso);
	}

	@Override
	public ArrayList<Alumno> readAll() {
		return alumDao.readAll();
	}

	@Override
	public boolean verifEstaInscripto(String legajoAlumno, int idCurso) {
		return alumDao.verifEstaInscripto(legajoAlumno, idCurso);
	}

	@Override
	public boolean verifEstaCursandoMateria(String legajoAlumno, int idMateria, int idTurno, int cuatrimestre,
			int anio) {
		return alumDao.verifEstaCursandoMateria(legajoAlumno, idMateria, idTurno, cuatrimestre, anio);
	}
}
