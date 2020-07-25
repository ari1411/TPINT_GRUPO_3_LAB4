package entidades;

import java.util.Date;

public class Persona {
	private int Legajo;
	private String Nombre;
	private String Apellido;
	private String Dni;
	private Date FechaNac;
	private String Direccion;
	private Localidad localidad;
	private String Telefono;
	private String Mail;
	private Boolean Estado;

	public Persona() {
	}

	public Persona(int legajo, String nombre, String apellido, String dni, Date fechaNac, String direccion,
			       Localidad localidad, String telefono, String mail, Boolean estado) {
		this.Legajo = legajo;
		this.Nombre = nombre;
		this.Apellido = apellido;
		this.Dni = dni;
		this.FechaNac = fechaNac;
		this.Direccion = direccion;
		this.localidad = localidad;
		this.Telefono = telefono;
		this.Mail = mail;
		this.Estado = estado;
	}
	public int getLegajo() {
		return Legajo;
	}

	public void setLegajo(int legajo) {
		Legajo = legajo;
	}
	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getApellido() {
		return Apellido;
	}

	public void setApellido(String apellido) {
		Apellido = apellido;
	}

	public String getDni() {
		return Dni;
	}

	public void setDni(String dni) {
		Dni = dni;
	}

	public Date getFechaNac() {
		return FechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		FechaNac = fechaNac;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public String getMail() {
		return Mail;
	}

	public void setMail(String mail) {
		Mail = mail;
	}

	public Boolean getEstado() {
		return Estado;
	}

	public void setEstado(Boolean estado) {
		Estado = estado;
	}

	@Override
	public String toString() {
		return "Persona [Legajo=" + Legajo + ", Nombre=" + Nombre + ", Apellido=" + Apellido + ", Dni=" + Dni + ", FechaNac=" + FechaNac + ", Direccion=" + Direccion + ", Localidad=" + localidad.getId()
				+ ", Telefono=" + Telefono + ", Mail=" + Mail + ", Estado=" + Estado + "]";
	}
}
