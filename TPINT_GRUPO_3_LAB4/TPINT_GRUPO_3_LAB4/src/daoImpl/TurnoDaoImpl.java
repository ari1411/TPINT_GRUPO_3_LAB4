package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dao.TurnoDao;
import entidades.Materia;
import entidades.Turno;

public class TurnoDaoImpl implements TurnoDao{
	
	String listarTodo = "SELECT * FROM tpint_grupo_3_lab4.turno";
	String buscarxID = "SELECT * FROM tpint_grupo_3_lab4.turno where idturno=";

	@Override
	public Turno buscarTurno(int Id) {
		Turno turno = new Turno();
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(buscarxID + Id);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				turno.setIdTurno(resultSet.getInt("idturno"));
				turno.setTurno(resultSet.getString("Nom_Turno"));
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return turno;
	}

	@Override
	public ArrayList<Turno> listarTurnos() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Turno turno= null;
		ArrayList<Turno> lTurnos = new ArrayList<Turno>();
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(listarTodo);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				turno = new Turno();
				turno.setIdTurno(resultSet.getInt("idturno"));
				turno.setTurno(resultSet.getString("Nom_turno"));
				lTurnos.add(turno);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lTurnos;
	}

}
