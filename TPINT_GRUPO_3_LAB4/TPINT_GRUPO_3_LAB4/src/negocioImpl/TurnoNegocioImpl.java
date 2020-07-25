package negocioImpl;

import java.util.ArrayList;

import dao.TurnoDao;
import daoImpl.TurnoDaoImpl;
import entidades.Turno;
import negocio.TurnoNegocio;

public class TurnoNegocioImpl implements TurnoNegocio{
	private TurnoDao turnoDao = new TurnoDaoImpl();

	@Override
	public Turno buscarTurno(int Id) {
		Turno turno= turnoDao.buscarTurno(Id);
		return turno;
	}

	@Override
	public ArrayList<Turno> listarTurnos() {
		ArrayList<Turno> turno = turnoDao.listarTurnos();
		return turno;
	}

}
