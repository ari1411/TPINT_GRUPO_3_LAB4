package entidades;

public class Curso {

	private int Id;
	private int IdMateria;
	private int IdTurno;
	private int Cuatrimestre;
	private int Anio;
	private int LegajoProf;
	private int Estado;
	private String Materia;
	private String Profesor;
	private String Turno;
	private int CantAlum;

	public Curso() {
	}

	public Curso(int id, int idMateria, int idTurno, int cuatrimestre, int anio, int legajoProf, int estado,
			String materia, String profesor, String turno, int cantAlum) {
		super();
		Id = id;
		IdMateria = idMateria;
		IdTurno = idTurno;
		Cuatrimestre = cuatrimestre;
		Anio = anio;
		LegajoProf = legajoProf;
		Estado = estado;
		Materia = materia;
		Profesor = profesor;
		Turno = turno;
		CantAlum = cantAlum;
	}

	public int getIdTurno() {
		return IdTurno;
	}

	public void setIdTurno(int idTurno) {
		IdTurno = idTurno;
	}

	public String getTurno() {
		return Turno;
	}

	public void setTurno(String turno) {
		Turno = turno;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getIdMateria() {
		return IdMateria;
	}

	public void setIdMateria(int idMateria) {
		IdMateria = idMateria;
	}

	public int getCuatrimestre() {
		return Cuatrimestre;
	}

	public void setCuatrimestre(int cuatrimestre) {
		Cuatrimestre = cuatrimestre;
	}

	public int getAnio() {
		return Anio;
	}

	public void setAnio(int anio) {
		Anio = anio;
	}

	public int getLegajoProf() {
		return LegajoProf;
	}

	public void setLegajoProf(int legajoProf) {
		LegajoProf = legajoProf;
	}

	public int getEstado() {
		return Estado;
	}

	public void setEstado(int estado) {
		Estado = estado;
	}

	public String getMateria() {
		return Materia;
	}

	public void setMateria(String materia) {
		Materia = materia;
	}

	public String getProfesor() {
		return Profesor;
	}

	public void setProfesor(String profesor) {
		Profesor = profesor;
	}

	public int getCantAlum() {
		return CantAlum;
	}

	public void setCantAlum(int cantAlum) {
		CantAlum = cantAlum;
	}

	@Override
	public String toString() {
		return "Materia: " + Materia + " - Turno: " + Turno + " - Cuatrimestre: " + Cuatrimestre + " - Año: " + Anio + ".";
	}
	
	public String toStringSinMateria() {
		return "Turno: " + Turno + " - Cuatrimestre: " + Cuatrimestre + " - Año: " + Anio + ".";
	}
}
