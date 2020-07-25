package daoImpl;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.TipoUsuarioDao;
import entidades.TipoUsuario;

public class TipoUsuarioDaoImpl implements TipoUsuarioDao {
	private static final String obtenerTodos = "SELECT * FROM tipousuario";
	
	public ArrayList<TipoUsuario> obtenerTodos()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<TipoUsuario> tipoUsuario = new ArrayList<TipoUsuario>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(obtenerTodos);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				tipoUsuario.add(getTipoUsuario(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return tipoUsuario;
	}
	
	private TipoUsuario getTipoUsuario(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt("idtipoUsuario");
		String tipo = resultSet.getString("tipo");
		return new TipoUsuario(id, tipo);
	}
}
