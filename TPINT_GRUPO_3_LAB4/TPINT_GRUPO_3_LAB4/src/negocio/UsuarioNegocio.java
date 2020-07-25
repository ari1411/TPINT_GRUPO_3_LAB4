package negocio;

import java.util.ArrayList;

import entidades.Usuario;

public interface UsuarioNegocio {
	public ArrayList<Usuario> obtenerTodos();
	public Usuario obtenerUsuario(String user,String clave);
	public boolean agregarUsuario(Usuario usuarioAgregado);
	public boolean validarUserName(String user);
	public boolean eliminarUsuario(int idUsuario);
	public boolean actualizarClave(String clave,int idUsuario);
	public boolean validarLegajo(int legajoUsuario);
}
