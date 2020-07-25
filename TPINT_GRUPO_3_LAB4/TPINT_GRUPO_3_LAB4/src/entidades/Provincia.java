package entidades;

public class Provincia {
	private int Id;
	private String NombreProv;
	
	public Provincia() {
	}
	
	public Provincia(int id, String nombreProv) {
		this.Id = id;
		this.NombreProv = nombreProv;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getNombreProv() {
		return NombreProv;
	}
	public void setNombreProv(String nombreProv) {
		NombreProv = nombreProv;
	}
	@Override
	public String toString() {
		return "Provincia [Id=" + Id + ", Nombre=" + NombreProv + "]";
	}
}
