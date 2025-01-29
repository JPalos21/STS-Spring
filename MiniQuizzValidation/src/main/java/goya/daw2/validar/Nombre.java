package goya.daw2.validar;

import jakarta.validation.constraints.NotEmpty;

public class Nombre {

	@NotEmpty
	String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
