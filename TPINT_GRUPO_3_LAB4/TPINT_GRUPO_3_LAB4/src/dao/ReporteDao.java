package dao;

import java.util.ArrayList;

import entidades.Reporte;

public interface ReporteDao {
	public ArrayList<Reporte> obtenerDatosNotas(int idmateria, int cuatrimestre, int anio,int tipo);
}
