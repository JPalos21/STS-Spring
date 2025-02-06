package ies.goya.examen.ud5.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ies.goya.examen.ud5.model.Alumno;
import ies.goya.examen.ud5.model.Email;

@Repository
public interface RepositorioEmails extends CrudRepository<Email, Long> {

	Email findByEmail(String email);
	List<Email> findByAlumno(Alumno alumno);
}
