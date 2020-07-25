package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import daoImpl.Conexion;
import dao.CursoDao;
import entidades.Curso;

public class CursoDaoImpl implements CursoDao {

	String buscarxId = "SELECT * FROM tpint_grupo_3_lab4.listar_cursos where idcurso=";
	String listarTodos = "SELECT * FROM tpint_grupo_3_lab4.listar_cursos";
	String listarCursosProf = "SELECT * FROM tpint_grupo_3_lab4.listar_cursos where Legajo_pro=";
	String eliminar_Curso = "UPDATE tpint_grupo_3_lab4.curso SET estado = 0 WHERE idcurso = ";
	String agregarCurso = "insert into tpint_grupo_3_lab4.curso (IdMateria, idturno, Cuatrimestre, Anio, Legajo_pro, Estado) VALUES(?, ?, ?, ?, ?, ?)";
	String ultimoIdCurso = "SELECT idcurso FROM tpint_grupo_3_lab4.curso order by idCurso desc LIMIT 1";
	String InsertarAlumnoalCurso = "insert into tpint_grupo_3_lab4.alumnoxcurso (IdCurso, LegajoAlumno, idEstadoAcademico, estado) VALUES (?, ? , 2, 1)";
	String actualizarCurso = "update tpint_grupo_3_lab4.curso set idMateria= ? , idturno=?, Cuatrimestre= ?, Anio= ? , Legajo_pro= ?  where IdCurso= ?";
	String quitarAlumnodelcurso = "update tpint_grupo_3_lab4.alumnoxcurso set estado = 0 where legajoAlumno= ? and idcurso= ?";
	String existeCursoActivo = "SELECT count(idmateria) as Cantidad, idcurso from curso where idMateria=? and idturno=? and Cuatrimestre=? and anio=? and legajo_Pro=? and estado=true and idcurso<>?";
	String AlumnoEstaInscripto = "SELECT count(*) as Cantidad from alumnoxcurso where idCurso=? and legajoAlumno=? and estado=true;";

	@Override
	public Curso buscarCurso(int Id) {
		Curso curso = new Curso();
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();

		try {
			statement = conexion.getSQLConexion().prepareStatement(buscarxId + Id);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				curso = new Curso();

				curso.setId(resultSet.getInt("idcurso"));
				curso.setIdMateria(resultSet.getInt("IdMateria"));
				curso.setIdTurno(resultSet.getInt("idturno"));
				curso.setCuatrimestre(resultSet.getInt("cuatrimestre"));
				curso.setAnio(resultSet.getInt("anio"));
				curso.setLegajoProf(resultSet.getInt("legajo_Pro"));
				curso.setEstado(resultSet.getInt("estado"));
				curso.setMateria(resultSet.getString("materia"));
				curso.setTurno(resultSet.getString("turno"));
				curso.setProfesor(resultSet.getString("apeNomProf"));
				curso.setCantAlum(resultSet.getInt("cantAlum"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return curso;
	}

	@Override
	public ArrayList<Curso> listarCursos() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Curso curso = null;
		ArrayList<Curso> lCursos = new ArrayList<Curso>();
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(listarTodos);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				curso = new Curso();

				curso.setId(resultSet.getInt("idcurso"));
				curso.setIdMateria(resultSet.getInt("IdMateria"));
				curso.setIdTurno(resultSet.getInt("idturno"));
				curso.setCuatrimestre(resultSet.getInt("cuatrimestre"));
				curso.setAnio(resultSet.getInt("anio"));
				curso.setLegajoProf(resultSet.getInt("legajo_Pro"));
				curso.setEstado(resultSet.getInt("estado"));
				curso.setMateria(resultSet.getString("materia"));
				curso.setTurno(resultSet.getString("turno"));
				curso.setProfesor(resultSet.getString("apeNomProf"));
				curso.setCantAlum(resultSet.getInt("cantAlum"));

				lCursos.add(curso);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lCursos;
	}

	@Override
	public ArrayList<Curso> listarCursos(int LegajoProf) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Curso curso = null;
		ArrayList<Curso> lCursos = new ArrayList<Curso>();
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(listarCursosProf + LegajoProf);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				curso = new Curso();

				curso.setId(resultSet.getInt("idcurso"));
				curso.setIdMateria(resultSet.getInt("IdMateria"));
				curso.setIdTurno(resultSet.getInt("idturno"));
				curso.setCuatrimestre(resultSet.getInt("cuatrimestre"));
				curso.setAnio(resultSet.getInt("anio"));
				curso.setLegajoProf(resultSet.getInt("legajo_Pro"));
				curso.setEstado(resultSet.getInt("estado"));
				curso.setMateria(resultSet.getString("materia"));
				curso.setTurno(resultSet.getString("turno"));
				curso.setProfesor(resultSet.getString("apeNomProf"));
				curso.setCantAlum(resultSet.getInt("cantAlum"));

				lCursos.add(curso);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lCursos;
	}

	@Override
	public boolean eliminarCurso(int Id) {
		boolean Eliminado = false;
		int Elim = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		try {
			statement = conexion.prepareStatement(eliminar_Curso + Id);
			Elim = statement.executeUpdate();
			conexion.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (Elim > 0) {
			Eliminado = true;
		}
		return Eliminado;
	}

	@Override
	public boolean GrabarCurso(Curso curso) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean Grabado = false;
		try {
			statement = conexion.prepareStatement(agregarCurso);
			statement.setInt(1, curso.getIdMateria());
			statement.setInt(2, curso.getIdTurno());
			statement.setInt(3, curso.getCuatrimestre());
			statement.setInt(4, curso.getAnio());
			statement.setInt(5, curso.getLegajoProf());
			statement.setBoolean(6, true);
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				Grabado = true;
			}
		} catch (SQLException e) {

			try {
				conexion.rollback();
			} catch (SQLException e1) {

				return Grabado;
			}
			return Grabado;
		}
		return Grabado;
	}

	@Override
	public int UltimoId() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		PreparedStatement statement;
		ResultSet resultSet;
		int ultimoID = 0;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(ultimoIdCurso);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				ultimoID = (resultSet.getInt("idcurso"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ultimoID;
	}

	@Override
	public boolean InsertarAlumnoAlCurso(int idCurso, String legajoAlumno) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(InsertarAlumnoalCurso);
			statement.setInt(1, idCurso);
			statement.setInt(2, Integer.parseInt(legajoAlumno.toString()));
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isInsertExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				return isInsertExitoso;
			}
		}
		return isInsertExitoso;
	}

	@Override
	public boolean ActualizarCurso(Curso curso) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean Actualizado = false;
		try {
			statement = conexion.prepareStatement(actualizarCurso);
			statement.setInt(1, curso.getIdMateria());
			statement.setInt(2, curso.getIdTurno());
			statement.setInt(3, curso.getCuatrimestre());
			statement.setInt(4, curso.getAnio());
			statement.setInt(5, curso.getLegajoProf());
			statement.setInt(6, curso.getId());

			if (statement.executeUpdate() > 0) {
				conexion.commit();
				Actualizado = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Actualizado;

	}

	@Override
	public boolean EliminarAlumnoDelCurso(int legajoAlumno, int idCurso) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(quitarAlumnodelcurso);
			statement.setInt(1, legajoAlumno);
			statement.setInt(2, idCurso);
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isdeleteExitoso = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isdeleteExitoso;
	}

	@Override
	public int VerificarExisteCurso(Curso curso) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		ResultSet resultSet;
		int Cantidad = 0;
		int Id = 0;
		boolean existeCurso = true;
		try {
			statement = conexion.prepareStatement(existeCursoActivo);
			statement.setInt(1, curso.getIdMateria());
			statement.setInt(2, curso.getIdTurno());
			statement.setInt(3, curso.getCuatrimestre());
			statement.setInt(4, curso.getAnio());
			statement.setInt(5, curso.getLegajoProf());
			statement.setInt(6, curso.getId());
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Cantidad = (resultSet.getInt("Cantidad"));
				if (Cantidad == 0) {
					existeCurso = false;
					Id = 0;
				} else {
					existeCurso = true;
					Id = (resultSet.getInt("IdCurso"));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Id;
	}

	@Override
	public boolean VerificarAlumnoEstaInscripto(int idCurso, String legajoAlumno) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		ResultSet resultSet;
		int Cantidad = 0;
		boolean alumnoEstaInscripto = true;
		try {
			statement = conexion.prepareStatement(AlumnoEstaInscripto);
			statement.setInt(1, idCurso);
			statement.setInt(2, Integer.parseInt(legajoAlumno.toString()));
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Cantidad = (resultSet.getInt("Cantidad"));
			}

			if (Cantidad == 0)
				alumnoEstaInscripto = false;
			else
				alumnoEstaInscripto = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alumnoEstaInscripto;
	}

	public boolean EliminarCursosdesdeProfesor(int legajoProf) {

		boolean isExtoso = false;
		try {

			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		ArrayList<Curso> lCursos = new ArrayList<Curso>();

		lCursos = this.listarCursos(legajoProf);

		for (int i = 0; i < lCursos.size(); i++) {
			this.eliminarCurso(lCursos.get(i).getId());
			isExtoso = true;
		}

		return isExtoso;

	}

	@Override
	public ArrayList<Curso> filtroListarCursos(int legajoProfesor, Curso cur) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Curso curso = null;
		ArrayList<Curso> lCursos = new ArrayList<Curso>();
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		String query = listarCursosProf + legajoProfesor;

		if (cur.getIdMateria() > 0)
			query += " and idmateria=" + cur.getIdMateria();
		if (cur.getIdTurno() > 0)
			query += " and idturno=" + cur.getIdTurno();
		if (cur.getCuatrimestre() > 0)
			query += " and cuatrimestre=" + cur.getCuatrimestre();
		if (cur.getAnio() > 0)
			query += " and anio=" + cur.getAnio();

		try {
			statement = conexion.getSQLConexion().prepareStatement(query);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				curso = new Curso();

				curso.setId(resultSet.getInt("idcurso"));
				curso.setIdMateria(resultSet.getInt("IdMateria"));
				curso.setIdTurno(resultSet.getInt("idturno"));
				curso.setCuatrimestre(resultSet.getInt("cuatrimestre"));
				curso.setAnio(resultSet.getInt("anio"));
				curso.setLegajoProf(resultSet.getInt("legajo_Pro"));
				curso.setEstado(resultSet.getInt("estado"));
				curso.setMateria(resultSet.getString("materia"));
				curso.setTurno(resultSet.getString("turno"));
				curso.setProfesor(resultSet.getString("apeNomProf"));
				curso.setCantAlum(resultSet.getInt("cantAlum"));

				lCursos.add(curso);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lCursos;
	}

}
