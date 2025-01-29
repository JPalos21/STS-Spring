package goya.daw2.validar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

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
	String etapa1(Nombre nombre) {
		return "inicio";
	}
	
	@PostMapping("/")
	String procesaEtapa1(@Valid Nombre nombre, BindingResult results) {
		if (results.hasErrors()) {
			return "inicio";
		}
		return "etapa2";
	}
	
}
