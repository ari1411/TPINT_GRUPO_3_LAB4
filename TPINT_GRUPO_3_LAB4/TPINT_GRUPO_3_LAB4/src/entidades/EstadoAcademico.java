package entidades;

public class EstadoAcademico {

	private int Id;
	private String Nombre;
	
	

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombres(String nombres) {
		Nombre = nombres;
	}

	public EstadoAcademico() {
	
	}
	@Override
	public String toString() {
		return "EstadoAcademico [Id=" + Id + ", Nombres=" + Nombre + "]";
	}

}
