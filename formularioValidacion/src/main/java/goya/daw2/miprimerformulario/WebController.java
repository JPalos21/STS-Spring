package goya.daw2.miprimerformulario;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class WebController {
	
	@GetMapping("/")
	public String mostrarFormulario(PersonaFormulario personaFormulario) {
		return "form";
	}
	
	@PostMapping("/")
	public String validarInfo(@Valid PersonaFormulario personaFormulario, BindingResult vincular) {
		
		if (vincular.hasErrors()) {
			return "form";
		}
		
		return "/resultado";
	}
	
}
