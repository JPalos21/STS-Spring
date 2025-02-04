package goya.daw2.prueba2_puche1an.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import goya.daw2.prueba2_puche1an.model.Puntuacion;

@Repository
public interface RepositorioPuntuacion extends CrudRepository<Puntuacion, Long> {

    List<Puntuacion> findAllByOrderByPuntosDesc();
}
