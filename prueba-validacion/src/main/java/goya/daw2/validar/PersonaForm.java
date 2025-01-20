package goya.daw2.validar;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PersonaForm {

	@NotNull
	private String nombre;
	
	@Min(18)
	private Integer edad;
	
	@Size(min=2, max=20)
	private String mensaje;
	
}
