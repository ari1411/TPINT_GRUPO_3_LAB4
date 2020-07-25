package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dao.AlumnoPorCursoDao;
import entidades.Alumno;
import entidades.AlumnosPorCursos;
import entidades.Curso;
import entidades.EstadoAcademico;

public class AlumnoPorCursoDaoImpl implements AlumnoPorCursoDao {

	private static final String readAll = "SELECT * FROM tpint_grupo_3_lab4.listar_notas";
	private static final String cargarNotaAlumno = "UPDATE `tpint_grupo_3_lab4`.`alumnoxcurso` SET `notaParcial1` = ?, `notaParcial2` = ?, `notaRecuperatorio1` = ?, `notaRecuperatorio2` = ?, `idEstadoAcademico` = ? WHERE `legajoAlumno` = ? and `idCurso` = ? and estado=1";
	private static final String mostrarCalifAlumnos = "SELECT * FROM tpint_grupo_3_lab4.listar_cargarnotas where idcurso = ? order by legajoAlumno asc";

	public boolean cargarNotaAlumno(AlumnosPorCursos NotaAlum) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(cargarNotaAlumno);

			statement.setInt(1, NotaAlum.getParcial1());
			statement.setInt(2, NotaAlum.getParcial2());
			statement.setInt(3, NotaAlum.getRecuperatorio1());
			statement.setInt(4, NotaAlum.getRecuperatorio2());
			statement.setInt(5, NotaAlum.getEstadoAca().getId());
			statement.setInt(6, NotaAlum.getAlumno().getLegajo());
			statement.setInt(7, NotaAlum.getCurso().getId());

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
	public ArrayList<AlumnosPorCursos> readAll() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		ArrayList<AlumnosPorCursos> listAlumnoConNotas = new ArrayList<AlumnosPorCursos>();
		PreparedStatement statement;
		ResultSet resultSet;

		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readAll);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Alumno alum = new Alumno();
				EstadoAcademico EstadoA = new EstadoAcademico();
				Curso curso = new Curso();
				AlumnosPorCursos notas = new AlumnosPorCursos();
				alum.setLegajo(resultSet.getInt("legajoAlumno"));
				curso.setId(resultSet.getInt("idcurso"));
				alum.setNombre(resultSet.getString("Alumno"));
				notas.setParcial1(resultSet.getInt("notaParcial1"));
				notas.setParcial2(resultSet.getInt("notaParcial2"));
				notas.setRecuperatorio1(resultSet.getInt("notaRecuperatorio1"));
				notas.setRecuperatorio2(resultSet.getInt("notaRecuperatorio2"));
				EstadoA.setId(resultSet.getInt("idEstadoAcademico"));
				EstadoA.setNombres(resultSet.getString("Estado_Academico"));
				notas.setEstadoAca(EstadoA);
				notas.setEstado(resultSet.getBoolean("estado"));
				notas.setAlumno(alum);
				notas.setCurso(curso);

				listAlumnoConNotas.add(notas);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return listAlumnoConNotas;
	}

	public ArrayList<AlumnosPorCursos> filtrarPorProfesor(int legajo, int idcurso) {

		String consulta = readAll;

		if (legajo != 0) {
			consulta = consulta + " where idProfesor= " + legajo + " and " + "idcurso = " + idcurso;

		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		ArrayList<AlumnosPorCursos> listAlumnoConNotas = new ArrayList<AlumnosPorCursos>();
		PreparedStatement statement;
		ResultSet resultSet;

		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(consulta);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Alumno alum = new Alumno();
				Curso curso = new Curso();
				EstadoAcademico EstadoA = new EstadoAcademico();
				AlumnosPorCursos notas = new AlumnosPorCursos();
				alum.setLegajo(resultSet.getInt("legajoAlumno"));
				alum.setNombre(resultSet.getString("Alumno"));
				notas.setParcial1(resultSet.getInt("notaParcial1"));
				notas.setParcial2(resultSet.getInt("notaParcial2"));
				notas.setRecuperatorio1(resultSet.getInt("notaRecuperatorio1"));
				notas.setRecuperatorio2(resultSet.getInt("notaRecuperatorio2"));
				EstadoA.setId(resultSet.getInt("idEstadoAcademico"));
				EstadoA.setNombres(resultSet.getString("Estado_Academico"));
				notas.setEstadoAca(EstadoA);
				notas.setEstado(resultSet.getBoolean("estado"));
				curso.setId(resultSet.getInt("idcurso"));
				notas.setAlumno(alum);

				listAlumnoConNotas.add(notas);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return listAlumnoConNotas;
	}

	@Override
	public ArrayList<AlumnosPorCursos> ObtenerCalificacionesAlumnos(int IdCurso) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<AlumnosPorCursos> listAlumnoConNotas = new ArrayList<AlumnosPorCursos>();
		PreparedStatement statement;
		ResultSet resultSet;
		AlumnosPorCursos ACaux = null;
		Alumno alumAux = null;
		Curso cursoAux = null;
		EstadoAcademico EAaux = null;

		Connection conexion = Conexion.getConexion().getSQLConexion();
		try {
			statement = conexion.prepareStatement(mostrarCalifAlumnos);
			statement.setInt(1, IdCurso);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				ACaux = new AlumnosPorCursos();
				alumAux = new Alumno();
				cursoAux = new Curso();
				EAaux = new EstadoAcademico();
				ACaux.setId(resultSet.getInt("idAlumnoXCurso"));
				alumAux.setLegajo(resultSet.getInt("legajoAlumno"));
				alumAux.setNombre(resultSet.getString("alumno"));
				ACaux.setAlumno(alumAux);
				cursoAux.setId(resultSet.getInt("idcurso"));
				ACaux.setCurso(cursoAux);
				ACaux.setParcial1(resultSet.getInt("notaParcial1"));
				ACaux.setParcial2(resultSet.getInt("notaParcial2"));
				ACaux.setRecuperatorio1(resultSet.getInt("notaRecuperatorio1"));
				ACaux.setRecuperatorio2(resultSet.getInt("notaRecuperatorio2"));
				EAaux.setId(resultSet.getInt("idestadoacademico"));
				EAaux.setNombres(resultSet.getString("estadoacademico"));
				ACaux.setEstadoAca(EAaux);
				ACaux.setEstado(true);

				listAlumnoConNotas.add(ACaux);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listAlumnoConNotas;
	}

	@Override
	public boolean agregarAlumno(AlumnosPorCursos NotaDelAgregado) {
		// TODO Auto-generated method stub
		return false;
	}
}
