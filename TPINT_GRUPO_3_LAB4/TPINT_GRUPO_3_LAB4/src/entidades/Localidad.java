package entidades;

public class Localidad {
	private int Id;
	private String Nombre;
	private Provincia Provincia;
	
	public Localidad() {}
	
	public Localidad(int id, String nombre, entidades.Provincia provincia) {
		this.Id = id;
		this.Nombre = nombre;
		this.Provincia = provincia;
	}
	
	public Localidad(int id, String nombre) {
		this.Id = id;
		this.Nombre = nombre;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getNombreLoc() {
		return Nombre;
	}

	public void setNombreLoc(String nombre) {
		Nombre = nombre;
	}

	public Provincia getProvincia() {
		return Provincia;
	}

	public void setProvincia(Provincia provincia) {
		Provincia = provincia;
	}

	@Override
	public String toString() {
		return "Localidad [Id=" + Id + ", Nombre=" + Nombre + ", Provincia=" + Provincia.getId() + "]";
	}
}
