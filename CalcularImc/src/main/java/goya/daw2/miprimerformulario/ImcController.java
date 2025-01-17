package goya.daw2.miprimerformulario;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class ImcController {
	@GetMapping("/")
	public String mostrarFormulario(Persona persona) {
		return "formulario";
	}
	
	@PostMapping("/")
	public String validarInfo(@Valid Persona persona, BindingResult vincular) {
		
		if (vincular.hasErrors()) {
			return "formulario";
		}
		
		return "resultado";
	}
	
}
