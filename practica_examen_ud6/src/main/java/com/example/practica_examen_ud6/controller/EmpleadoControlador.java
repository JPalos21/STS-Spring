package com.example.practica_examen_ud6.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.practica_examen_ud6.model.Empleado;
import com.example.practica_examen_ud6.repository.EmpleadoRepositorio;


@RestController
@RequestMapping("/empleados")
public class EmpleadoControlador {

    private EmpleadoRepositorio empleadoRepositorio;
    public EmpleadoControlador(EmpleadoRepositorio empleadoRepositorio) {
        this.empleadoRepositorio = empleadoRepositorio;
    }

    @GetMapping
    ResponseEntity<List<Empleado>> todos() {
        List<Empleado> empleados = empleadoRepositorio.findAll();
        return ResponseEntity.ok(empleados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> findById(@PathVariable("id") Long id) {
        Optional<Empleado> empleadoOpt = empleadoRepositorio.findById(id);

        if (empleadoOpt.isPresent()) {
            return ResponseEntity.ok(empleadoOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Empleado> crear(@RequestBody Empleado empleado) {
        Optional<Empleado> empleadoEx = empleadoRepositorio.findByNombreAndRol(empleado.getNombre(), empleado.getRol());

        if (empleadoEx.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        Empleado empleadoGuardar = empleadoRepositorio.save(empleado);
        return ResponseEntity.status(HttpStatus.CREATED).body(empleadoGuardar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> update(@PathVariable("id") Long id, @RequestBody Empleado empleado) {
        if (!empleadoRepositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        } else {
            empleado.setId(id);
            Empleado empleadoAct = empleadoRepositorio.save(empleado);
            return ResponseEntity.ok(empleadoAct);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Empleado> borrar(@PathVariable("id") Long id) {
        if (!empleadoRepositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        empleadoRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }    
    
}
