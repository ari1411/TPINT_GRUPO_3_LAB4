package dao;

import java.util.ArrayList;
import java.util.List;

import entidades.Alumno;
import entidades.Usuario;

public interface UsuarioDao {
	public ArrayList<Usuario> obtenerTodos();
	public Usuario obtenerUsuario(String user,String clave);
	public boolean agregarUsuario(Usuario usuarioAgregado);
	public boolean validarUserName(String user);
	public boolean eliminarUsuario(int idUsuario);
	public boolean actualizarClave(String clave,int idUsuario);
	public boolean validarLegajo(int legajoUsuario);
}
