package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidades.Provincia;

public class ProvinciaDaoImpl {

	private static final String obtenerListProvincia="SELECT idprovincia, Nombre as NProvincia FROM  tpint_grupo_3_lab4.provincia;";
	
	public ArrayList<Provincia> listarProvincia()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Provincia> provincias=new ArrayList<Provincia>();
		Conexion conexion=Conexion.getConexion();
		try 
		{
			statement=conexion.getSQLConexion().prepareStatement(obtenerListProvincia);
			resultSet=statement.executeQuery();
			while(resultSet.next()) 
			{
				provincias.add(getProvincia(resultSet));
			}
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		return provincias;
		
	}
	
	private Provincia getProvincia(ResultSet resultSet) throws SQLException
	{
		return new Provincia(resultSet.getInt("idprovincia"),resultSet.getString("NProvincia"));
	}
	
	public ProvinciaDaoImpl() {
			
	}

}
