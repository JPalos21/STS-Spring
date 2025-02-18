package ies.goya.examen.ud6;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personajes")
public class ControladorPersonaje {

	private RepositoryPersonaje repositoryPersonaje;
	public ControladorPersonaje(RepositoryPersonaje repositoryPersonaje) {
		this.repositoryPersonaje = repositoryPersonaje;
	}
	
	@GetMapping
	public ResponseEntity<List<Personaje>> todos() {
		List<Personaje> personajes = repositoryPersonaje.findAll();
		return ResponseEntity.ok(personajes);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Personaje> one(@PathVariable("id") Long id) {
		Optional<Personaje> personajeOpt = repositoryPersonaje.findById(id);
		if (personajeOpt.isPresent()) {
			return ResponseEntity.ok(personajeOpt.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<Personaje> guardar(@RequestBody Personaje personaje) {
		Optional<Personaje> personajeOpt = repositoryPersonaje.findByNombre(personaje.getNombre());
		if (personaje.getId() != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		if (personajeOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}
		Personaje personajeGuardado = repositoryPersonaje.save(personaje);
		return ResponseEntity.status(HttpStatus.CREATED).body(personajeGuardado);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Personaje> put(@PathVariable("id") Long id, @RequestBody Personaje personaje) {
		if (!repositoryPersonaje.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		Optional<Personaje> personajeOpt = repositoryPersonaje.findByNombre(personaje.getNombre());
		if (personajeOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}
//		if (personajeOpt.get().getId() != personaje.getId()) {
//			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
//		}
		personaje.setId(id);
		Personaje personajeActu = repositoryPersonaje.save(personaje);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> actualizar(@PathVariable("id") Long id, @RequestBody Personaje personaje) {
		if (!repositoryPersonaje.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		if (personaje.getId() != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		Optional<Personaje> personajeNombre = repositoryPersonaje.findByNombre(personaje.getNombre());
		if (personajeNombre.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}
		Optional<Personaje> personajeOpt = repositoryPersonaje.findById(id);
		if (personaje.getNombre() != null) {
			personajeOpt.get().setNombre(personaje.getNombre());
		}
		if (personaje.getDescripción() != null) {
			personajeOpt.get().setDescripción(personaje.getDescripción());
		}
		repositoryPersonaje.save(personajeOpt.get());
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Personaje> borrar(@PathVariable("id") Long id) {
		if (!repositoryPersonaje.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		repositoryPersonaje.deleteById(id);
		return ResponseEntity.noContent().build();
	}
		
}
