package com.example.practica_examen_ud6.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.practica_examen_ud6.model.Empleado;

@Repository
public interface EmpleadoRepositorio extends JpaRepository<Empleado, Long>  {

    Optional<Empleado> findByNombreAndRol(String nombre, String rol);

}
