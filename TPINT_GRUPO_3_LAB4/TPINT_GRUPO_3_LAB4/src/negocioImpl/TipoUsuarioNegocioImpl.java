package negocioImpl;

import java.util.ArrayList;

import dao.TipoUsuarioDao;
import daoImpl.TipoUsuarioDaoImpl;
import entidades.TipoUsuario;
import negocio.TipoUsuarioNegocio;

public class TipoUsuarioNegocioImpl implements TipoUsuarioNegocio {

	TipoUsuarioDao tipoUsuarioDao = new TipoUsuarioDaoImpl();

	@Override
	public ArrayList<TipoUsuario> obtenerTodos() {
		ArrayList<TipoUsuario> listTipoUsuarios = tipoUsuarioDao.obtenerTodos();
		return listTipoUsuarios;
	}
}
