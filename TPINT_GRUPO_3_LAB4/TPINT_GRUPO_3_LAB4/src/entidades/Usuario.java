package entidades;

public class Usuario {
	private int id;
	private String username;
	private String pass;
	private TipoUsuario tipoUsuario;
	private int legajo;
	private int estado;

	public Usuario() {

	}

	public Usuario(int id, String username, String pass, TipoUsuario tipoUsuario, int legajo, int estado) {
		this.id = id;
		this.username = username;
		this.pass = pass;
		this.tipoUsuario = tipoUsuario;
		this.legajo = legajo;
		this.estado = estado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public int getLegajo() {
		return legajo;
	}

	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", pass=" + pass + ", tipoUsuario=" + tipoUsuario
				+ ", legajo=" + legajo + ", estado=" + estado + "]";
	}
}
