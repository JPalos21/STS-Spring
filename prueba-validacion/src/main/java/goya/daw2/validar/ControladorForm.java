package goya.daw2.validar;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class ControladorForm {

	@GetMapping("/")
	String inicial(PersonaForm personaForm) {
		return "inicio";
	}
	
	@PostMapping("/")
	String envioFormulario(@Valid PersonaForm personaForm, BindingResult vinculo) {
		
		if (vinculo.hasErrors()) {
			return "inicio";
		}
		return "resultado";
	}
	
}
