package entidades;

public class Profesor extends Persona {
	private Curso Curso;
	
	public Profesor() {
		super();
	}

	public Curso getCurso() {
		return Curso;
	}

	public void setCurso(Curso curso) {
		Curso = curso;
	}

}
