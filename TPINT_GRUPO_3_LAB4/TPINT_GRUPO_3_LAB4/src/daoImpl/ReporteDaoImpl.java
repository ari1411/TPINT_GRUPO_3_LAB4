package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ReporteDao;
import entidades.Curso;
import entidades.Materia;
import entidades.Profesor;
import entidades.Reporte;
import entidades.Turno;

public class ReporteDaoImpl implements ReporteDao {
	private static final String obtenerDatosNotas = "SELECT * FROM tpint_grupo_3_lab4.datos_reporte where idcurso >= 0 ";

	public ArrayList<Reporte> obtenerDatosNotas(int idmateria, int cuatrimestre, int anio, int tipo) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String consulta = obtenerDatosNotas;
		int cont = 0;

		if (idmateria != 0) {
			consulta = consulta + " and idMateria= " + idmateria;
			cont++;
		}
		if (cuatrimestre != 0) {
			consulta = consulta + " and cuatrimestre= " + cuatrimestre;
			cont++;
		}
		if (anio != 0) {
			consulta = consulta + " and anio= " + anio;
			cont++;
		}
		if (cont == 0) {
			consulta = obtenerDatosNotas;
		}

		ArrayList<Reporte> listReporte = new ArrayList<Reporte>();
		PreparedStatement statement;
		ResultSet resultSet;

		if (tipo == 1) {
			Conexion conexion = Conexion.getConexion();
			try {
				statement = conexion.getSQLConexion().prepareStatement(consulta);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					Reporte reporte = new Reporte();
					Curso curso = new Curso();
					Materia materia = new Materia();
					Turno turno = new Turno();
					Profesor profesor = new Profesor();

					reporte.setTotal_alumnos_libres(resultSet.getInt("total_alumnos_libres")); 					
					reporte.setTotal_alumnos_en_curso(resultSet.getInt("total_alumnos_en_curso")); 					
					reporte.setTotal_alumnos_regularizados(resultSet.getInt("total_alumnos_regularizados")); 					
					reporte.setTotal_aprobados(resultSet.getInt("total_alumnos_promocionados"));

					reporte.setTotal_alumnos(resultSet.getInt("total_alumnos"));

					curso.setCuatrimestre(resultSet.getInt("cuatrimestre"));
					curso.setAnio(resultSet.getInt("anio"));
					reporte.setCurso(curso);

					materia.setId(resultSet.getInt("idMateria"));
					materia.setNombre(resultSet.getString("Materia"));
					reporte.setMateria(materia);
					
					turno.setIdTurno(resultSet.getInt("idturno"));
					turno.setTurno(resultSet.getString("turno"));
					reporte.setTurno(turno);
					
					profesor.setLegajo(resultSet.getInt("legajo_profesor"));
					profesor.setNombre(resultSet.getString("nombre_profesor"));
					profesor.setApellido(resultSet.getString("apellido_profesor"));
					reporte.setProfesor(profesor);

					listReporte.add(reporte);
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			return listReporte;

		} else {
			Conexion conexion = Conexion.getConexion();
			try {
				statement = conexion.getSQLConexion().prepareStatement(consulta);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					Reporte reporte = new Reporte();
					Curso curso = new Curso();
					Materia materia = new Materia();
					Turno turno = new Turno();
					Profesor profesor = new Profesor();

					reporte.setTotal_alumnos_libres(resultSet.getInt("total_alumnos_libres"));
					reporte.setTotal_alumnos_en_curso(resultSet.getInt("total_alumnos_en_curso"));
					reporte.setTotal_alumnos_regularizados(resultSet.getInt("total_alumnos_regularizados"));
					reporte.setTotal_aprobados(resultSet.getInt("total_alumnos_promocionados"));

					reporte.setTotal_alumnos(resultSet.getInt("total_alumnos"));

					curso.setCuatrimestre(resultSet.getInt("cuatrimestre"));
					curso.setAnio(resultSet.getInt("anio"));
					reporte.setCurso(curso);

					materia.setId(resultSet.getInt("idMateria"));
					materia.setNombre(resultSet.getString("MAteria"));
					reporte.setMateria(materia);
					
					turno.setIdTurno(resultSet.getInt("idturno"));
					turno.setTurno(resultSet.getString("turno"));
					reporte.setTurno(turno);
					
					profesor.setLegajo(resultSet.getInt("legajo_profesor"));
					profesor.setNombre(resultSet.getString("nombre_profesor"));
					profesor.setApellido(resultSet.getString("apellido_profesor"));
					reporte.setProfesor(profesor);

					listReporte.add(reporte);
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			return listReporte;

		}

	}
}

