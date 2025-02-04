package goya.daw2.prueba2_puche1an.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import goya.daw2.prueba2_puche1an.model.Usuario;

@Repository
public interface RepositorioUsuario extends CrudRepository<Usuario, Long> {

    List<Usuario> findByNombre(String nombre);
    List<Usuario> findAllByOrderByNombreDesc();
}
