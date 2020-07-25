package entidades;

public class Reporte {
	private int Id;
	private Curso curso;
	private Materia materia;
	private Profesor profesor;
	private Turno turno;
	private int total_alumnos_libres;
	private int total_alumnos_en_curso;
	private int total_alumnos_regularizados;
	private int total_no_aprobados;
	private int total_aprobados;
	private int total_alumnos;
	
	public int getId() {
		return Id;
	}
	
	public void setId(int id) {
		Id = id;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public Materia getMateria() {
		return materia;
	}
	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	public int getTotal_alumnos_en_curso() {
		return total_alumnos_en_curso;
	}

	public void setTotal_alumnos_en_curso(int total_alumnos_en_curso) {
		this.total_alumnos_en_curso = total_alumnos_en_curso;
	}

	public int getTotal_alumnos_regularizados() {
		return total_alumnos_regularizados;
	}

	public void setTotal_alumnos_regularizados(int total_alumnos_regularizados) {
		this.total_alumnos_regularizados = total_alumnos_regularizados;
	}
	public int getTotal_no_aprobados() {
		return total_no_aprobados;
	}
	public void setTotal_no_aprobados(int total_no_aprobados) {
		this.total_no_aprobados = total_no_aprobados;
	}
	public int getTotal_aprobados() {
		return total_aprobados;
	}
	public void setTotal_aprobados(int total_aprobados) {
		this.total_aprobados = total_aprobados;
	}
	public int getTotal_alumnos() {
		return total_alumnos;
	}
	public void setTotal_alumnos(int total_alumnos) {
		this.total_alumnos = total_alumnos;
	}

	public int getTotal_alumnos_libres() {
		return total_alumnos_libres;
	}

	public void setTotal_alumnos_libres(int total_alumnos_libres) {
		this.total_alumnos_libres = total_alumnos_libres;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	@Override
	public String toString() {
		return "Reporte [Id=" + Id + ", curso=" + curso + ", materia=" + materia + ", profesor=" + profesor + ", turno="
				+ turno + ", total_alumnos_libres=" + total_alumnos_libres + ", total_alumnos_en_curso="
				+ total_alumnos_en_curso + ", total_alumnos_regularizados=" + total_alumnos_regularizados
				+ ", total_no_aprobados=" + total_no_aprobados + ", total_aprobados=" + total_aprobados
				+ ", total_alumnos=" + total_alumnos + "]";
	}
}
