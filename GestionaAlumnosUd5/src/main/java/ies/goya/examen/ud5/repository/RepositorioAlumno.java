package ies.goya.examen.ud5.repository;

import java.util.List;
//import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ies.goya.examen.ud5.model.Alumno;
//import ies.goya.examen.ud5.model.Email;

@Repository
public interface RepositorioAlumno extends CrudRepository<Alumno, Long> {
//	List<Alumno> findByNombre(String nombre);
	String findByNombre(String nombre);
	List<Alumno> findByApellidos(String apellidos);
	Alumno getById(Long id);
	List<Alumno> findByOrderByIdDesc();
	List<Alumno> findByOrderByNombreAsc();
	List<Alumno> findByOrderByApellidosAsc();
	Alumno findByNombreAndApellidos(String nombre, String apellidos);
}
