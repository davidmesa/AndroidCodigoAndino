package co.cristiansierra.entidades;

public class Usuario {
	
	private static Usuario instancia;
	
	private String token;
	
	public static Usuario darInstancia() {
		if(instancia == null) {
			instancia = new Usuario();
		}
		return instancia;
	}

	private Usuario() {
		token=null;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
