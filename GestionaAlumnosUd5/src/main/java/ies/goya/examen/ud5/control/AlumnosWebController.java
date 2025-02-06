package ies.goya.examen.ud5.control;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ies.goya.examen.ud5.service.AlumnoService;

@RestController
public class AlumnosWebController {
	protected AlumnoService alumnoService;
	
	public AlumnosWebController(AlumnoService alumnoService) {
		this.alumnoService = alumnoService;
	}	
	
	@GetMapping("/todos")
	public String todos() {
		return alumnoService.getAll(); 
	}
	
	@GetMapping("/alumnoPorId")
	public String alumnoPorId(@RequestParam(name="id",required=false) Long id) {
		if (id==null) return "HACE FALTA ID";
		return alumnoService.get(id).toString();
	}
	
	// En una app real los datos vendrías por POST
	@GetMapping("/nuevoAlumno")
	public String nuevoAlumno(@RequestParam(name="nombre",required=false) String nombre,
			@RequestParam(name="apellidos",required=false) String apellidos) {
		if (nombre==null || apellidos==null) return "HACEN FALTA NOMBRE Y APELLIDOS";
		return alumnoService.add(nombre,apellidos);
	}
	
	@GetMapping("/modificaAlumno")
	public String modificaAlumno(@RequestParam(name="id",required=false) Long id,
			@RequestParam(name="nombre", required=false) String nombre,
			@RequestParam(name="apellidos", required=false) String apellidos) {
		if (id==null) return "HACE FALTA ID";
		return alumnoService.merge(id,nombre,apellidos);
	}
	
	@GetMapping("/borraAlumno")
	public String borraAlumno(@RequestParam(name="id",required=false) Long id) {
		if (id==null) return "HACE FALTA ID";
		return alumnoService.delete(id);
	}
	
	@GetMapping("/meteEmailAlumno")
	public String asociaEmailConAlumno(@RequestParam(name="id",required=false) Long id,
			@RequestParam(name="email",required=false) String email) {
		if (id==null || email==null) return "HACE FALTA ID e EMAIL";
		return alumnoService.addEmailToAlumno(id,email);
	}
	
	@GetMapping("/borraEmailDeAlumno")
	public String borraEmailDeAlumno(@RequestParam(name="id",required=false) Long id,
			@RequestParam(name="email",required=false) String email) {
		if (id==null || email==null) return "HACE FALTA ID e EMAIL";
		return alumnoService.delEmailFromAlumno(id,email);
		
	}
	
	@GetMapping("/buscaPorNombreYApellidos") 
	public String buscaAlumno(@RequestParam(name="nombre",required=false) String nombre,
							@RequestParam(name="apellidos",required=false) String apellidos) {
		if (nombre==null || apellidos==null) return "HACEN FALTA NOMBRE Y APELLIDOS";
		return alumnoService.findByNombreAndApellidos(nombre,apellidos);
	}
	
	@GetMapping("/buscaPorNombre") 
	public String buscaAlumnoPorNombre(@RequestParam(name="nombre",required=false) String nombre) {
		if (nombre==null) return "HACEN FALTA NOMBRE";
		return alumnoService.findByNombre(nombre);
	}
	
	@GetMapping("/buscaPorApellidos") 
	public String buscaAlumnoPorApellidos(@RequestParam(name="apellidos",required=false) String apellidos) {
		if (apellidos==null) return "HACEN FALTA APELLIDOS";
		return alumnoService.findByApellidos(apellidos);
	}
	
	@GetMapping("/todosPorNombre")
	public String todosPorNombre() {
		return alumnoService.getAllOrderByNombre(); 
	}
	
	@GetMapping("/todosPorApellidos")
	public String todosPorApellidos() {
		return alumnoService.getAllOrderByApellidos(); 
	}
	
	@GetMapping("/")
	public String indice() {
		return "Bienvenido a GesAlumnos:"
				+ "Use <a href=/todos>/todos</a> para consultar todos"
				+ "<br> <a href=/todosPorNombre>/todosPorNombre</a> listar todos ordenados por nombre"
				+ "<br> <a href=/todosPorApellidos>todosPorApellidos</a> listar todos ordenados por apellidos"
				+ "<br> <a href=/alumnoPorId>/alumnosPorId</a> para buscar por id"
				+ "<br> <a href=/nuevoAlumno>/nuevoAlumno</a> para meter un nuevo alumno"
				+ "<br> <a href=/modificaAlumno>/modificaAlumno</a> para modificar"
				+ "<br> <a href=/borraAlumno>/borraAlumno</a> para borrar (por id)"
				+ "<br> <a href=/borraEmailDeAlumno>/borraEmailDeAlumno</a> para borrar email de un alumno por id"
				+ "<br> <a href=/meteEmailAlumno>/meteEmailAlumno</a> para añadir email a un alumno (por id)"
				+ "<br> <a href=/buscaPorNombre>/buscaPorNombre</a> para buscar por nombre"
				+ "<br> <a href=/buscaPorApellidos>/buscaPorApellidos</a> para buscar por apellidos"
				+ "<br> <a href=/buscaPorNombreYApellidos>/buscaPorNombreYApellidos</a> para buscar por nombre y apellidos";
	}
	
} // class
