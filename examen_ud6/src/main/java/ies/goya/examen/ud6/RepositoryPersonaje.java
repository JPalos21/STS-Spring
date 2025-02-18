package ies.goya.examen.ud6;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

// Lo voy a cambiar a Jpa que si no en el primer getmapping tengo que castear el objeto porque me dice que no es iterable
public interface RepositoryPersonaje extends JpaRepository<Personaje,Long> {
	boolean existsByNombre(String nombre);
	public Optional<Personaje> findByNombre(String nombre);
	Optional<Personaje> findByNombreAndDescripción(String nombre, String descripción);
}
