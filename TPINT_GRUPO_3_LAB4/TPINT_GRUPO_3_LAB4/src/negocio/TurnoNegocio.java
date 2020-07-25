package negocio;

import java.util.ArrayList;

import entidades.Turno;

public interface TurnoNegocio {
	public Turno buscarTurno(int Id);

	public ArrayList<Turno> listarTurnos();
}
