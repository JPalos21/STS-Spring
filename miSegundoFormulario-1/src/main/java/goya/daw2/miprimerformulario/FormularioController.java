package goya.daw2.miprimerformulario;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormularioController {
	@GetMapping("/")
	String inicio() {
		return "form";
	}
	
	@PostMapping("/")
	String procesaForm(@RequestParam(name="nombre", required=false)String nombre, Model modelo) {
		if (nombre == null || nombre.isBlank()) {
			modelo.addAttribute("errorMsg", "El nombre no puede estar vacío");
			return "form";
		}
		else {
			modelo.addAttribute("nombre",nombre);
			return "saludo";
		}
	}
}
