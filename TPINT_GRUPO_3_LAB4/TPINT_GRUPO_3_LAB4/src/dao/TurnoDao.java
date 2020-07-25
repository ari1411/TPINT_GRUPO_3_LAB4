package dao;

import java.util.ArrayList;

import entidades.Turno;

public interface TurnoDao {
	
	public Turno buscarTurno(int Id);

	public ArrayList<Turno> listarTurnos();
}
