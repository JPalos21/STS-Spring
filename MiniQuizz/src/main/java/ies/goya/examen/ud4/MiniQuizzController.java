package ies.goya.examen.ud4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class MiniQuizzController {

	static final String[] SIGNOS = {"Aries", "Tauro", "Géminis", "Cáncer", "Leo", "Virgo", "Libra", "Escorpio", "Sagitario", "Capricornio", "Acuraio", "Piscis"};
	static final String[] AFICCIONES = { "Deportes", "Juerga", "Lectura" , "Relaciones sociales" };
	
	// No hace falta entender este método que a partir de unos datos genera una categoría de forma algo random
	static String generaCategoría(String nombre, Integer signo, String aficciones) {
		String[] categorías = {"Tierra","Aire","Agua","Fuego"};
		String[] aficcionesArray = aficciones.split(",");
		Integer num = Arrays.asList(aficcionesArray).stream().map(Integer::parseInt).reduce(0,Integer::sum);
		int indice = (nombre.length() * signo * num) % categorías.length;
		return categorías[indice];
	}
	
	@GetMapping("/")
	public String inicio() {
		return "inicio";
	}
	
	@PostMapping("etapa2")
	public String procesaEtapa2(@RequestParam(name="nombre") String nombre, HttpSession sesion, Model modelo) {
		if (nombre.isBlank()) {
			modelo.addAttribute("errores", "El nombre es obligatorio");
			return "inicio";
		}
		else {
			// Las partes comentadas sería la forma correcta de realizarlo
//			List<String> respuestas =  new ArrayList<String>();
			sesion.setAttribute("nombre", nombre);
//			respuestas.add(nombre);
			//sesion.setAttribute("respuestas", respuestas);
			return "etapa2";
		}
	}
	
	@PostMapping("/etapa3")
	public String procesaEtapa3(@RequestParam(name="signo") Integer signo, HttpSession sesion, Model modelo) {
		sesion.setAttribute("signo", signo);
//		List<String> respuestas = (List<String>) sesion.getAttribute("respuestas");
//		respuestas.add(SIGNOS[signo-1]);
//		sesion.setAttribute("respuestas", respuestas);
		return "etapa3";
	}
	
	@PostMapping("/resultado")
	public String procesaResultado(@RequestParam(name="aficciones", required = false) String aficciones, HttpSession sesion, Model modelo) {
		if (aficciones == null || aficciones.isEmpty()) {
			modelo.addAttribute("errores", "Hay que marcar al menos una afición");
			return "etapa3";
		}
		String nombre = (String) sesion.getAttribute("nombre");
		Integer signo = (Integer) sesion.getAttribute("signo");
//		List<String> respuestas = (List<String>) sesion.getAttribute("respuestas");
//		
//		String strAficciones = "";
//		for (String s : aficciones.split(",")) {
//			int i = Integer.parseInt(s.trim());
//			strAficciones += AFICCIONES[i-1] + ",";
//		}
//		respuestas.add(strAficciones);
		List<String> respuestas = new ArrayList<String>();
		respuestas.add(nombre);
		respuestas.add(SIGNOS[signo-1]);
		String strAficciones = "";
		String[] arrAficciones = aficciones.split(",");
		for (String aficcion : arrAficciones) {
			int intAficcion = Integer.parseInt(aficcion.trim());
			strAficciones += AFICCIONES[intAficcion - 1];
		}
		respuestas.add(strAficciones);
		
		modelo.addAttribute("categoría" , generaCategoría(nombre, signo, aficciones));
//		modelo.addAttribute("respuestas", respuestas);
		modelo.addAttribute("respuestas", respuestas);
		return "resultado";
	}
	
	
}
