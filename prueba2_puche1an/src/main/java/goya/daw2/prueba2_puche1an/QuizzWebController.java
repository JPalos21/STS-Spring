package goya.daw2.prueba2_puche1an;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goya.daw2.prueba2_puche1an.servicios.ServicioQuiz;
import jakarta.servlet.http.HttpSession;

@Controller
public class QuizzWebController {
	private static final int NUM_PREGUNTAS=3;
	
	private ServicioQuiz servicioQuiz;
	
	public QuizzWebController(ServicioQuiz servicioQuiz) {
		this.servicioQuiz = servicioQuiz;
	}
	
	@GetMapping("/")
	public String inicio(Model model, HttpSession sesión) {
		model.addAttribute("etapa",0);
		model.addAttribute("numPreguntas",NUM_PREGUNTAS);
		sesión.removeAttribute("puntos");
		return "encuesta";
	}
	@PostMapping("/nombre")
	public String nombre(@RequestParam(name="nombre") String nombre, Model model, HttpSession sesión) {
		sesión.setAttribute("nombre", nombre);
		model.addAttribute("numPreguntas",NUM_PREGUNTAS);
		model.addAttribute("etapa",1);
		return "encuesta";
	}
	
	
	@PostMapping("/")
	public String encuesta(@RequestParam(name="puntos") Integer puntos, @RequestParam(name="etapa") Integer etapa, Model model, HttpSession sesión) {
		model.addAttribute("numPreguntas",NUM_PREGUNTAS);
		Integer puntosSesión = (Integer)sesión.getAttribute("puntos");
		if (puntosSesión == null) puntosSesión = 0;
		sesión.setAttribute("puntos", puntosSesión+puntos);
		if (etapa>NUM_PREGUNTAS) {
			// Resultado final:
			int puntosTotales = puntosSesión + puntos;
			model.addAttribute("puntos",puntosSesión+puntos);
			String nombre = (String) sesión.getAttribute("nombre");
			// mejor meter en la sesión el objeto jugador entero ??
			model.addAttribute("nombre",nombre);
			servicioQuiz.guardarDatos(nombre, puntosTotales);
			
		}
		model.addAttribute("etapa",etapa);
		return "encuesta";
	}
	
	
	
	// Aquí están los mapings de los resultados ordenados
	@GetMapping("/ranking")
	public String rankings(Model modelo) {
		modelo.addAttribute("usuarios", servicioQuiz.listarNombre());
		modelo.addAttribute("puntuaciones", servicioQuiz.listarPuntuacion());
		return "puntos";
	}
}

