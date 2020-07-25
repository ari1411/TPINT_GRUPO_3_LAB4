package dao;

import java.util.ArrayList;
import java.util.List;
import entidades.Localidad;

public interface LocalidadDao {
	
	public ArrayList<Localidad> obtenerListLocalidad();

	public ArrayList<Localidad> ObtenerLocalidadPorProvincia(int IdProv);

}
