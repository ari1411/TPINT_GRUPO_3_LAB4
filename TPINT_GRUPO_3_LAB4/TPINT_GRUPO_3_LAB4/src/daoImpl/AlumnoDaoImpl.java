package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dao.AlumnoDao;
import entidades.Alumno;
import entidades.Localidad;
import entidades.Provincia;

import java.util.List;

import daoImpl.Conexion;

public class AlumnoDaoImpl implements AlumnoDao {

	private static final String agregarAlumno = "INSERT INTO alumno(nombre, apellido, dni, fecha_nac, direccion, idlocalidad, telefono, mail, estado) values(?,?,?,?,?,?,?,?,?)";
	private static final String readAll ="SELECT a.legajo_alum,a.nombre as Alumno,a.apellido,a.dni,a.fecha_nac,a.direccion,loc.idlocalidad,loc.nombre as Localidad,prov.idprovincia,prov.nombre as Provincia,a.telefono,a.mail from alumno as a inner join localidad as loc on a.idlocalidad=loc.idlocalidad inner join provincia as prov on prov.idprovincia=loc.idprovincia where a.estado=1";
	private static final String modificarAlumno = "UPDATE alumno set nombre=?,apellido=?, dni=?, fecha_nac=?, direccion=?, Idlocalidad=?, telefono=?, mail=? where legajo_alum= ?";
	private static final String obtenerAlumnosInscriptos = "SELECT a.legajo_alum,a.nombre as Alumno,a.apellido,a.dni,a.fecha_nac,a.direccion,loc.idlocalidad,loc.nombre as Localidad,prov.idprovincia,prov.nombre as Provincia,a.telefono,a.mail from alumno as a inner join localidad as loc on a.idlocalidad=loc.idlocalidad inner join provincia as prov on prov.idprovincia=loc.idprovincia inner join alumnoxcurso as alXcu on legajo_alum=legajoalumno where a.estado=1 and alXcu.estado=1 and idCurso=";
	private static final String leerAlumno = "SELECT a.legajo_alum,a.nombre as Alumno,a.apellido,a.dni,a.fecha_nac,a.direccion,loc.idlocalidad,loc.nombre as Localidad,prov.idprovincia,prov.nombre as Provincia,a.telefono,a.mail from alumno as a inner join localidad as loc on a.idlocalidad=loc.idlocalidad inner join provincia as prov on prov.idprovincia=loc.idprovincia  where estado=1 and legajo_alum=?;";
	private static final String eliminarAlumno = "UPDATE alumno set estado=0 where legajo_alum= ?";
    private static final String filtrar="SELECT distinct a.legajo_alum,a.nombre as Alumno,a.apellido,a.dni,a.fecha_nac,a.direccion,loc.idlocalidad,loc.nombre as Localidad,prov.idprovincia,prov.nombre as Provincia,a.telefono,a.mail from alumno as a inner join localidad as loc on a.idlocalidad=loc.idlocalidad inner join provincia as prov on prov.idprovincia=loc.idprovincia inner join alumnoXcurso as AC on AC.legajoAlumno=a.legajo_alum inner join curso as C on C.idcurso=AC.idcurso where a.estado=1";
    private static final String existeTablaAxC = "SELECT count(*) as total FROM tpint_grupo_3_lab4.alumnoxcurso where legajoAlumno= ? and idCurso= ? and estado = 1";
    private static final String filtrarPorProfesor= "SELECT a.legajo_alum,a.nombre as Alumno,a.apellido,a.dni,a.fecha_nac,a.direccion,loc.idlocalidad,loc.nombre as Localidad,prov.idprovincia,prov.nombre as Provincia,a.telefono,a.mail from alumno as a inner join localidad as loc on a.idlocalidad=loc.idlocalidad inner join provincia as prov on prov.idprovincia=loc.idprovincia inner join alumnoxcurso as AC on AC.legajoAlumno=a.legajo_alum inner join curso as C on C.idcurso= AC.idCurso where a.estado=1";
    private static final String VerificarDNI="SELECT * FROM tpint_grupo_3_lab4.alumno where estado=1 ";
    private static final String verificarCursandoMateria = "SELECT count(*) as total FROM tpint_grupo_3_lab4.alumnoxcurso AC inner join tpint_grupo_3_lab4.curso C on c.idCurso=AC.idCurso where ac.legajoAlumno=? and c.idmateria=? and c.idturno=? and c.cuatrimestre=? and c.anio=? and c.estado=1 and ac.estado=1";
    
	public boolean agregarAlumno(Alumno alumno) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(agregarAlumno);
			statement.setString(1, alumno.getNombre());
			statement.setString(2, alumno.getApellido());
			statement.setString(3, alumno.getDni());
			statement.setDate(4, (Date) alumno.getFechaNac());
			statement.setString(5, alumno.getDireccion());
			statement.setInt(6, alumno.getLocalidad().getId());
			statement.setString(7, alumno.getTelefono());
			statement.setString(8, alumno.getMail());
			statement.setBoolean(9, alumno.getEstado());
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isInsertExitoso = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				conexion.rollback();
			} catch (Exception ex1) {
				ex1.printStackTrace();
			}
		}
		return isInsertExitoso;
	}

	public ArrayList<Alumno> readAll() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		ArrayList<Alumno> listAlumno = new ArrayList<Alumno>();
		PreparedStatement statement;
		ResultSet resultSet;

		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readAll);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Localidad loc = new Localidad();
				Provincia provincia = new Provincia();
				Alumno alum = new Alumno();
				alum.setLegajo(resultSet.getInt("legajo_alum"));
				alum.setNombre(resultSet.getString("Alumno"));
				alum.setApellido(resultSet.getString("Apellido"));
				alum.setDni(resultSet.getString("Dni"));
				alum.setFechaNac(resultSet.getDate("fecha_nac"));
				alum.setDireccion(resultSet.getString("direccion"));
				loc.setId(resultSet.getInt("idlocalidad"));
				loc.setNombreLoc(resultSet.getString("Localidad"));
				provincia.setId(resultSet.getInt("idprovincia"));
				provincia.setNombreProv(resultSet.getString("Provincia"));
				loc.setProvincia(provincia);
				alum.setLocalidad(loc);
				alum.setTelefono(resultSet.getString("telefono"));
				alum.setMail(resultSet.getString("mail"));
				listAlumno.add(alum);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return listAlumno;
	}

	public Alumno ObtenerAlumno(int Legajo) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Alumno alum = new Alumno();
		PreparedStatement statement;
		ResultSet resultSet;

		Conexion conexion = Conexion.getConexion();
		try {

			statement = conexion.getSQLConexion().prepareStatement(leerAlumno);
			statement.setInt((1), Legajo);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Localidad loc = new Localidad();
				Provincia provincia = new Provincia();
				alum.setLegajo(resultSet.getInt("legajo_alum"));
				alum.setNombre(resultSet.getString("Alumno"));
				alum.setApellido(resultSet.getString("Apellido"));
				alum.setDni(resultSet.getString("Dni"));
				alum.setFechaNac(resultSet.getDate("fecha_nac"));
				alum.setDireccion(resultSet.getString("direccion"));
				loc.setId(resultSet.getInt("idlocalidad"));
				loc.setNombreLoc(resultSet.getString("Localidad"));
				provincia.setId(resultSet.getInt("idprovincia"));
				provincia.setNombreProv(resultSet.getString("Provincia"));
				loc.setProvincia(provincia);
				alum.setLocalidad(loc);
				alum.setTelefono(resultSet.getString("telefono"));
				alum.setMail(resultSet.getString("mail"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return alum;
	}

	public boolean eliminarAlumno(int Legajo) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {

			statement = conexion.prepareStatement(eliminarAlumno);
			statement.setInt(1, Legajo);
			// statement.setInt(2, alum1.getEstado());

			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isInsertExitoso = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				conexion.rollback();
			} catch (Exception ex1) {
				ex1.printStackTrace();
			}
		}
		return isInsertExitoso;
	}

	public boolean modificarAlumno(Alumno alum1) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {

			statement = conexion.prepareStatement(modificarAlumno);
			statement.setString(1, alum1.getNombre());
			statement.setString(2, alum1.getApellido());
			statement.setString(3, alum1.getDni());
			statement.setDate(4, (Date) alum1.getFechaNac());
			statement.setString(5, alum1.getDireccion());
			statement.setInt(6, alum1.getLocalidad().getId());
			statement.setString(7, alum1.getTelefono());
			statement.setString(8, alum1.getMail());
			statement.setInt(9, alum1.getLegajo());

			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isInsertExitoso = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				conexion.rollback();
			} catch (Exception ex1) {
				ex1.printStackTrace();
			}
		}
		return isInsertExitoso;
	}

	@Override
	public ArrayList<Alumno> getAlumnosInscriptos(int IdCurso) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		ArrayList<Alumno> listAlumno = new ArrayList<Alumno>();
		Alumno alumno = null;
		Localidad localidad = null;
		Provincia provincia = null;
		PreparedStatement statement;
		ResultSet resultSet;

		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(obtenerAlumnosInscriptos + IdCurso);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				localidad = new Localidad();
				provincia = new Provincia();
				alumno = new Alumno();
				alumno.setLegajo(resultSet.getInt("legajo_alum"));
				alumno.setNombre(resultSet.getString("Alumno"));
				alumno.setApellido(resultSet.getString("Apellido"));
				alumno.setDni(resultSet.getString("Dni"));
				alumno.setFechaNac(resultSet.getDate("fecha_nac"));
				alumno.setDireccion(resultSet.getString("direccion"));
				localidad.setId(resultSet.getInt("idlocalidad"));
				localidad.setNombreLoc(resultSet.getString("Localidad"));
				provincia.setId(resultSet.getInt("idprovincia"));
				provincia.setNombreProv(resultSet.getString("Provincia"));
				localidad.setProvincia(provincia);
				alumno.setLocalidad(localidad);
				alumno.setTelefono(resultSet.getString("telefono"));
				alumno.setMail(resultSet.getString("mail"));
				listAlumno.add(alumno);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return listAlumno;
	}

	public ArrayList<Alumno> filtroDeAlumnos(int idmateria, int cuatrimestre, int anio) {

		String consulta = filtrar;
		int cont=0;

		if (idmateria != 0) 
		{
			consulta = consulta + " and idMateria= " + idmateria;
			cont++;
		}
		if (cuatrimestre != 0) 
		{
			consulta = consulta + " and C.cuatrimestre= " + cuatrimestre;
			cont++;
		}
		if (anio != 0) 
		{
			consulta = consulta + " and C.anio= " + anio;
			cont++;
		}
		if(cont==0) 
		{
			consulta=readAll;
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		ArrayList<Alumno> listAlumno = new ArrayList<Alumno>();
		PreparedStatement statement;
		ResultSet resultSet;

		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(consulta);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Localidad loc = new Localidad();
				Provincia provincia = new Provincia();
				Alumno alum = new Alumno();
				alum.setLegajo(resultSet.getInt("legajo_alum"));
				alum.setNombre(resultSet.getString("Alumno"));
				alum.setApellido(resultSet.getString("Apellido"));
				alum.setDni(resultSet.getString("Dni"));
				alum.setFechaNac(resultSet.getDate("fecha_nac"));
				alum.setDireccion(resultSet.getString("direccion"));
				loc.setId(resultSet.getInt("idlocalidad"));
				loc.setNombreLoc(resultSet.getString("Localidad"));
				provincia.setId(resultSet.getInt("idprovincia"));
				provincia.setNombreProv(resultSet.getString("Provincia"));
				loc.setProvincia(provincia);
				alum.setLocalidad(loc);
				alum.setTelefono(resultSet.getString("telefono"));
				alum.setMail(resultSet.getString("mail"));
				listAlumno.add(alum);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return listAlumno;


	}
	
	
	public ArrayList<Alumno> filtroProProfesor(int idProfesor) {

		String consulta = filtrarPorProfesor;
		

		if (idProfesor != 0) 
		{
			consulta = consulta + " and C.legajo_pro= " + idProfesor;

		}
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		ArrayList<Alumno> listAlumno = new ArrayList<Alumno>();
		PreparedStatement statement;
		ResultSet resultSet;

		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(consulta);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Localidad loc = new Localidad();
				Provincia provincia = new Provincia();
				Alumno alum = new Alumno();
				alum.setLegajo(resultSet.getInt("legajo_alum"));
				alum.setNombre(resultSet.getString("Alumno"));
				alum.setApellido(resultSet.getString("Apellido"));
				alum.setDni(resultSet.getString("Dni"));
				alum.setFechaNac(resultSet.getDate("fecha_nac"));
				alum.setDireccion(resultSet.getString("direccion"));
				loc.setId(resultSet.getInt("idlocalidad"));
				loc.setNombreLoc(resultSet.getString("Localidad"));
				provincia.setId(resultSet.getInt("idprovincia"));
				provincia.setNombreProv(resultSet.getString("Provincia"));
				loc.setProvincia(provincia);
				alum.setLocalidad(loc);
				alum.setTelefono(resultSet.getString("telefono"));
				alum.setMail(resultSet.getString("mail"));
				listAlumno.add(alum);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return listAlumno;


	}

	@Override
	public boolean verifEstaInscripto(String legajoAlumno, int idCurso) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		Conexion conexion = Conexion.getConexion();
		int cant=0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(existeTablaAxC);
			statement.setInt(1,Integer.parseInt(legajoAlumno));
			statement.setInt(2,idCurso);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				cant=resultSet.getInt("total");
				if(cant>0)
				{
					return true;
				}
				else return false; 
					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		return false;

	}

	public boolean VerificarAlumno(String DNI,int Legajo) {
		boolean resultado=false;
		
		
		String consulta= VerificarDNI +"and dni= "+ DNI +" and"+" legajo_alum <> " + Legajo;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		
		PreparedStatement statement;
		ResultSet resultSet;

		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(consulta);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				return resultado=true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return resultado;
		
		
	}
	
	@Override
	public boolean verifEstaCursandoMateria(String legajoAlumno, int idMateria, int idTurno, int cuatrimestre, int anio) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		Conexion conexion = Conexion.getConexion();
		int cant=0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(verificarCursandoMateria);
			statement.setInt(1,Integer.parseInt(legajoAlumno));
			statement.setInt(2,idMateria);
			statement.setInt(3,idTurno);
			statement.setInt(4,cuatrimestre);
			statement.setInt(5,anio);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				cant=resultSet.getInt("total");
				if(cant>0)
				{
					return true;
				}
				else return false; 
					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		return false;

	}
}