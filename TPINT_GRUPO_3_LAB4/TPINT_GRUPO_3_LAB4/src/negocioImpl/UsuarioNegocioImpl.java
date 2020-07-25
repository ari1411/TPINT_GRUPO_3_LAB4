package negocioImpl;

import java.util.ArrayList;

import dao.UsuarioDao;
import daoImpl.UsuarioDaoImpl;
import entidades.Usuario;
import negocio.UsuarioNegocio;

public class UsuarioNegocioImpl implements UsuarioNegocio {
	UsuarioDao usuarioDao = new UsuarioDaoImpl();

	@Override
	public Usuario obtenerUsuario(String user, String clave) {
		Usuario usuario = usuarioDao.obtenerUsuario(user, clave);
		return usuario;
	}

	@Override
	public ArrayList<Usuario> obtenerTodos() {
		ArrayList<Usuario> listUsuarios = usuarioDao.obtenerTodos();
		return listUsuarios;
	}

	@Override
	public boolean agregarUsuario(Usuario usuarioAgregado) {
		boolean estado = false;
		if (usuarioAgregado.getUsername().trim().length() > 0 && usuarioAgregado.getPass().trim().length() > 0
				&& usuarioAgregado.getTipoUsuario().getId() > 0 && usuarioAgregado.getLegajo() > 0) {
			estado = usuarioDao.agregarUsuario(usuarioAgregado);
		}
		return estado;
	}
	
	@Override
	public boolean validarUserName(String user) {
		boolean isUser = usuarioDao.validarUserName(user);
		return isUser;
	}
	
	@Override
	public boolean eliminarUsuario(int idUsuario) {
		boolean isDeleteExitoso = usuarioDao.eliminarUsuario(idUsuario);
		return isDeleteExitoso;
	}
	
	@Override
	public boolean actualizarClave(String clave,int idUsuario) {
		boolean isChangeExitoso = usuarioDao.actualizarClave(clave,idUsuario);
		return isChangeExitoso;
	}

	@Override
	public boolean validarLegajo(int legajoUsuario) {
		boolean existe = usuarioDao.validarLegajo(legajoUsuario);
		return existe;
	}
}
