package goya.daw2.ud5.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import goya.daw2.ud5.modelo.Puntuacion;


@Repository
public interface RepositorioPuntuacion extends CrudRepository<Puntuacion, Long> {
	public List<Puntuacion> findAllByOrderByPuntosDesc();
}
