package com.primerapirest.goya.BaseDatos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.primerapirest.goya.Entidad.Empleado;
import com.primerapirest.goya.Repositorio.EmpleadoRepository;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(EmpleadoRepository repositorio) {

    return args -> {
      log.info("Preloading " + repositorio.save(new Empleado("Bilbo Baggins", "burglar")));
      log.info("Preloading " + repositorio.save(new Empleado("Frodo Baggins", "thief")));
    };
  }
}