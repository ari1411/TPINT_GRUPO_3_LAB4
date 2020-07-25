package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.UsuarioDao;
import entidades.TipoUsuario;
import entidades.Usuario;

public class UsuarioDaoImpl implements UsuarioDao {
	private static final String obtenerTodos = "SELECT * FROM usuario a  inner join tipousuario b on b.idtipoUsuario = a.idTipoUsuario Where estado=1";
	private static final String obtenerUsuario = "SELECT * FROM tpint_grupo_3_lab4.usuario a  inner join tpint_grupo_3_lab4.tipousuario b on b.idtipoUsuario = a.idTipoUsuario Where a.nombreUsuario= ? and a.Contrasenia= ? and a.estado=1";
	private static final String agregarUsuario = "INSERT INTO usuario(nombreUsuario, Contrasenia, idTipoUsuario, legajo_Pro, estado) values(?,?,?,?,?)";
	private static final String validarUserName = "SELECT * FROM tpint_grupo_3_lab4.usuario Where nombreUsuario= ? and estado=1";
	private static final String eliminarUsuario = "UPDATE usuario set estado=0 where idusuario= ?";
	private static final String actualizarClave = "UPDATE usuario set Contrasenia=? where idusuario= ?";
	private static final String eliminarUsuPro = "UPDATE usuario set estado=0 where legajo_Pro= ?";
	private static final String validarLegajoDelUsuario = "SELECT * FROM tpint_grupo_3_lab4.usuario Where legajo_Pro= ? and estado=1";
	

	public ArrayList<Usuario> obtenerTodos() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<Usuario> usuario = new ArrayList<Usuario>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(obtenerTodos);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				usuario.add(getUsuario(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}

	public Usuario obtenerUsuario(String user, String pass) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		Usuario usuario = new Usuario();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(obtenerUsuario);
			statement.setString(1, user);
			statement.setString(2, pass);
			resultSet = statement.executeQuery();
			System.out.println(resultSet);
			while (resultSet.next()) {
				usuario = getUsuario(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}

	private Usuario getUsuario(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("idusuario");
		String username = resultSet.getString("nombreUsuario");
		String pass = resultSet.getString("Contrasenia");

		TipoUsuario tipoUsuario = new TipoUsuario();
		tipoUsuario.setId(resultSet.getInt("idTipoUsuario"));
		tipoUsuario.setTipo(resultSet.getString("tipo"));

		int legajo = resultSet.getInt("legajo_Pro");
		int estado = resultSet.getInt("estado");
		return new Usuario(id, username, pass, tipoUsuario, legajo, estado);
	}

	public boolean agregarUsuario(Usuario usuario) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(agregarUsuario);
			statement.setString(1, usuario.getUsername());
			statement.setString(2, usuario.getPass());
			statement.setInt(3, usuario.getTipoUsuario().getId());
			statement.setInt(4, usuario.getLegajo());
			statement.setInt(5, usuario.getEstado());
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isInsertExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return isInsertExitoso;
	}

	public boolean validarUserName(String user) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		boolean isUser = false;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(validarUserName);
			statement.setString(1, user);
			resultSet = statement.executeQuery();
			System.out.println(resultSet);
			while (resultSet.next()) {
				isUser = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isUser;
	}

	public boolean eliminarUsuario(int idUsuario) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isDeleteExitoso = false;
		try {

			statement = conexion.prepareStatement(eliminarUsuario);
			statement.setInt(1, idUsuario);
			// statement.setInt(2, alum1.getEstado());

			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isDeleteExitoso = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				conexion.rollback();
			} catch (Exception ex1) {
				ex1.printStackTrace();
			}
		}
		return isDeleteExitoso;
	}
	
	public boolean eliminarUsuPro(int legajo) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isDeleteExitoso = false;
		try {

			statement = conexion.prepareStatement(eliminarUsuPro);
			statement.setInt(1, legajo);
			

			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isDeleteExitoso = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				conexion.rollback();
			} catch (Exception ex1) {
				ex1.printStackTrace();
			}
		}
		return isDeleteExitoso;
	}
	
	
	public boolean actualizarClave(String clave,int idUsuario) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isChangeExitoso = false;
		try {

			statement = conexion.prepareStatement(actualizarClave);
			statement.setString(1, clave);
			statement.setInt(2, idUsuario);
			
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isChangeExitoso = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				conexion.rollback();
			} catch (Exception ex1) {
				ex1.printStackTrace();
			}
		}
		return isChangeExitoso;
	}

	@Override
	public boolean validarLegajo(int legajoUsuario) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		boolean isUser = false;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(validarLegajoDelUsuario);
			statement.setInt(1, legajoUsuario);
			resultSet = statement.executeQuery();
			System.out.println(resultSet);
			while (resultSet.next()) {
				isUser = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isUser;
	}
}
