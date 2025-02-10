package com.primerapirest.goya.controlador;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.primerapirest.goya.Entidad.Empleado;
import com.primerapirest.goya.NoEncontrado.EmpleadoNoEncontradoException;
import com.primerapirest.goya.Repositorio.EmpleadoRepository;

@RestController
public class EmpleadoController {
	 private final EmpleadoRepository repositorio;

	 EmpleadoController(EmpleadoRepository repositorio) {
	    this.repositorio = repositorio;
	  }
	 @GetMapping("/empleados")
	  List<Empleado> all() {
	    return repositorio.findAll();
	  }
	 
	 @PostMapping("/empleados")
	  Empleado nuevoEmpleado(@RequestBody Empleado nuevoEmpleado) {
	    return repositorio.save(nuevoEmpleado);
	  }

	  // Single item
	  
	  @GetMapping("/empleados/{id}")
	  Empleado one(@PathVariable("id") Long id) {
	    
	    return repositorio.findById(id)
	      .orElseThrow(() -> new EmpleadoNoEncontradoException(id));
	  }

	  @PutMapping("/empleados/{id}")
	  Empleado replaceEmployee(@RequestBody Empleado nuevoEmpleado, @PathVariable("id") Long id) {
	    
	    return repositorio.findById(id)
	      .map(empleado -> {
	    	  empleado.setNombre(nuevoEmpleado.getNombre());
	    	  empleado.setRol(nuevoEmpleado.getRol());
	        return repositorio.save(empleado);
	      })
	      .orElseGet(() -> {
	        return repositorio.save(nuevoEmpleado);
	      });
	  }

	  @DeleteMapping("/empleados/{id}")
	  void deleteEmployee(@PathVariable("id") Long id) {
		  repositorio.deleteById(id);
	  }
}
