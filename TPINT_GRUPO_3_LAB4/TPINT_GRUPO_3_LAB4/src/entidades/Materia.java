package entidades;

public class Materia {
	
	private int id;
	private String Nombre;
	private boolean Estado;
	
	public Materia() {
		this.Estado = true;
	}
	
	
	public Materia(int id, String nombre) {
		this.id = id;
		this.Nombre = nombre;
		this.Estado = true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public boolean isEstado() {
		return Estado;
	}

	public void setEstado(boolean estado) {
		Estado = estado;
	}

	@Override
	public String toString() {
		return "Materia [id=" + id + ", Nombre=" + Nombre + ", Estado=" + Estado + "]";
	}

}
