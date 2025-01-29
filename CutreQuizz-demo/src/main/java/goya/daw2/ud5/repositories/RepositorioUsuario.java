package goya.daw2.ud5.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import goya.daw2.ud5.modelo.Usuario;

@Repository
public interface RepositorioUsuario extends CrudRepository<Usuario, Long> {
	public List<Usuario> findByNombre(String nombre);
	public List<Usuario> findAllByOrderByNombreAsc();
}
