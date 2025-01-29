package goya.daw2.ud5;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goya.daw2.ud5.modelo.Puntuacion;
import goya.daw2.ud5.modelo.Usuario;
import goya.daw2.ud5.repositories.RepositorioPuntuacion;
import goya.daw2.ud5.repositories.RepositorioUsuario;
import jakarta.servlet.http.HttpSession;

@Controller
public class QuizzWebController {
	private static final int NUM_PREGUNTAS=3;
	
	// Esto es lo mismo que hacer lo del @Autowired, la declaración de los repositorios y el método
	private RepositorioUsuario repositorioUsuario;
	private RepositorioPuntuacion repositorioPuntuacion;
	
	public QuizzWebController(RepositorioUsuario repositorioUsuario, RepositorioPuntuacion repositorioPuntuacion) {
		this.repositorioUsuario = repositorioUsuario;
		this.repositorioPuntuacion=repositorioPuntuacion;
	}
	
//	@Autowired
//	private RepositorioUsuario repositorioUsuario;
//	
//	@Autowired
//	private RepositorioPuntuacion repositorioPuntuacion;
	
	
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
			guardarDatos(nombre, puntosTotales);
			
		}
		model.addAttribute("etapa",etapa);
		return "encuesta";
	}
	
	public void guardarDatos(String nombre, int puntos) {
		Puntuacion puntuacion = new Puntuacion(puntos);
		
		Usuario usuario;
		List<Usuario> consulta = repositorioUsuario.findByNombre(nombre);
		
		if (consulta == null || consulta.isEmpty()) {
			usuario = new Usuario(nombre);
		}
		else usuario = consulta.get(0);
		usuario.addPuntuacion(puntuacion);
		repositorioUsuario.save(usuario);
	}
	
	// Aquí están los mapings de los resultados ordenados
	@GetMapping("/ranking")
	public String rankings(Model modelo) {
		modelo.addAttribute("usuarios", repositorioUsuario.findAllByOrderByNombreAsc());
		modelo.addAttribute("puntuaciones", repositorioPuntuacion.findAllByOrderByPuntosDesc());
		return "puntos";
	}

}
