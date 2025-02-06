package ies.goya.examen.ud5.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ies.goya.examen.ud5.model.Alumno;
import ies.goya.examen.ud5.model.Email;
//import ies.goya.examen.ud5.repository.AlumnoMapRepository;
import ies.goya.examen.ud5.repository.RepositorioAlumno;
import ies.goya.examen.ud5.repository.RepositorioEmails;

@Service
public class AlumnoService {
//	protected AlumnoMapRepository alumnoMapRepository;
//
//	public AlumnoService(AlumnoMapRepository alumnoMapRepository) {
//		this.alumnoMapRepository = alumnoMapRepository;
//	}
	
	private RepositorioAlumno repositorioAlumno;
	private RepositorioEmails repositorioEmails;
	
	public AlumnoService(RepositorioAlumno repositorioAlumno, RepositorioEmails repositorioEmails) {
		this.repositorioAlumno = repositorioAlumno;
		this.repositorioEmails = repositorioEmails;
	}
	

	// Obtener un alumno a partir de su id
//	public String get(Long id) {
//		Alumno alumno = alumnoMapRepository.get(id);
//		if (alumno == null)
//			return "No existe alumno con id=" + id;
//		return alumno.toString();
//	}
	public String get(Long id) {
		Alumno alumno = repositorioAlumno.getById(id);
		if (alumno == null)
			return "No existe alumno con id=" + id;
		return alumno.toString();
	}

	// Obtener todos los alumnos
//	public String getAll() {
//		String res = "";
//		for (Alumno a : alumnoMapRepository.getAll()) {
//			res += a.toString() + "<br>\n";
//		}
//		return res;
//	}
	public String getAll() {
		
		return repositorioAlumno.findByOrderByIdDesc().toString();
	}

	// Obtener todos los alumnos
//	public String getAllOrderByNombre() {
//		String res = "";
//		List<Alumno> todos = alumnoMapRepository.getAll();
//		todos.sort((a, b) -> (a.getNombre().compareTo(b.getNombre())));
//		for (Alumno a : todos) {
//			res += a.toString() + "<br>\n";
//		}
//		if (res.isEmpty()) return "LISTA VACÍA";
//		return res;
//	}
	public String getAllOrderByNombre() {
		return repositorioAlumno.findByOrderByNombreAsc().toString();
	}

	// Obtener todos los alumnos
//	public String getAllOrderByApellidos() {
//		String res = "";
//		List<Alumno> todos = alumnoMapRepository.getAll();
//		todos.sort((a, b) -> (a.getApellidos().compareTo(b.getApellidos())));
//		for (Alumno a : todos) {
//			res += a.toString() + "<br>\n";
//		}
//		if (res.isEmpty()) return "LISTA VACÍA";
//		return res;
//	}
	public String getAllOrderByApellidos() {
		return repositorioAlumno.findByOrderByApellidosAsc().toString() ;
	}

	// Añadir un alumno
//	public String add(String nombre, String apellidos) {
//		Alumno alumno = new Alumno();
//		alumno.setNombre(nombre);
//		alumno.setApellidos(apellidos);
//		alumnoMapRepository.save(alumno);
//		return alumno.toString();
//	}
	public String add(String nombre, String apellidos) {
		Alumno alumno = new Alumno();
//		Alumno alumno;
//		List<Alumno> consulta = repositorioAlumno.findByNombre(nombre);
//		if (consulta == null || consulta.isEmpty()) {
//			alumno = new Alumno(nombre);
//		}
		alumno.setNombre(nombre);
		alumno.setApellidos(apellidos);
		repositorioAlumno.save(alumno);
		return alumno.toString();
	}

	// Modificar un alumno
//	public String merge(Long id, String nombre, String apellidos) {
//		Alumno alumno = alumnoMapRepository.get(id);
//		if (alumno == null) {
//			return "No hay alumno con id=" + id;
//		}
//		if (nombre != null)
//			alumno.setNombre(nombre);
//		if (apellidos != null)
//			alumno.setApellidos(apellidos);
//		alumnoMapRepository.save(alumno);
//		return alumno.toString();
//	}
	public String merge(Long id, String nombre, String apellidos) {
		Alumno alumno = repositorioAlumno.getById(id);
		if (alumno == null) {
			return "No hay alumno con id=" + id;
		}
		if (nombre != null)
			alumno.setNombre(nombre);
		if (apellidos != null)
			alumno.setApellidos(apellidos);
		repositorioAlumno.save(alumno);
		return alumno.toString();
	}

	// Borrar un alumno
//	public String delete(Long id) {
//		Alumno alumno = alumnoMapRepository.get(id);
//		if (alumno == null) {
//			return "No hay alumno con id=" + id;
//		}
//		alumnoMapRepository.del(id);
//		return "Borrado: " + alumno;
//	}
	public String delete(Long id) {
		Alumno alumno = repositorioAlumno.getById(id);
		if (alumno == null) {
			return "No hay alumno con id=" + id;
		}
		if (alumno.getEmails() != null) {
			List<Email> consulta = repositorioEmails.findByAlumno(alumno);
			repositorioEmails.deleteAll(consulta);
		}
		repositorioAlumno.deleteById(id);
		return "Borrado: " + alumno;
	}

	// Asociar un email a un alumno
//	public String addEmailToAlumno(Long id, String emailStr) {
//		Alumno alumno = alumnoMapRepository.get(id);
//		if (alumno == null) {
//			return "No hay alumno con id=" + id;
//		}
//		alumno.addEmail(new Email(emailStr));
//		alumnoMapRepository.save(alumno);
//		return "Añadido email a alumno: " + alumno;
//	}
	public String addEmailToAlumno(Long id, String emailStr) {
		Alumno alumno = repositorioAlumno.getById(id);
		if (alumno == null) {
			return "No hay alumno con id=" + id;
		}
		alumno.addEmail(new Email(emailStr));
		repositorioAlumno.save(alumno);
		return "Añadido email a alumno: " + alumno;
	}

	// Desasociar en email de un alumno
//	public String delEmailFromAlumno(Long id, String emailStr) {
//		Alumno alumno = alumnoMapRepository.get(id);
//		if (alumno == null) {
//			return "No hay alumno con id=" + id;
//		}
//		alumno.delEmail(new Email(emailStr));
//		return "Borrado email de alumno: " + alumno;
//	}
	public String delEmailFromAlumno(Long id, String emailStr) {
		Alumno alumno = repositorioAlumno.getById(id);
		Email emailBorrar = repositorioEmails.findByEmail(emailStr);
		System.out.println(emailBorrar);
		if (alumno == null) {
			return "No hay alumno con id=" + id;
		}
		alumno.delEmail(emailBorrar);
		repositorioEmails.delete(emailBorrar);
		System.out.println(alumno);
		return "Borrado email de alumno: " + alumno;
	}

	// Buscar alumno por nombre y apellidos
//	public String findByNombreAndApellidos(String nombre, String apellidos) {
//		Alumno alumno = alumnoMapRepository.findByNombreAndApellidos(nombre, apellidos);
//		if (alumno == null) {
//			return "No existe alumno con nombre " + nombre + " y apellidos " + apellidos;
//		}
//		return alumno.toString();
//	}
	public String findByNombreAndApellidos(String nombre, String apellidos) {
		Alumno alumno = repositorioAlumno.findByNombreAndApellidos(nombre, apellidos);
		if (alumno == null) {
			return "No existe alumno con nombre " + nombre + " y apellidos " + apellidos;
		}
		return alumno.toString();
	}

	// Buscar alumno por nombre y apellidos
//	public String findByNombre(String nombre) {
//		String res = "";
//		List<Alumno> alumnos = alumnoMapRepository.findByNombre(nombre);
//		for (Alumno alumno : alumnos) {
//				res += alumno + "<br>\n";
//		}
//		if (res.isEmpty()) return "LISTA VACÍA";
//		return res;
//	}
	public String findByNombre(String nombre) {
		return repositorioAlumno.findByNombre(nombre);
	}
	
	// Buscar alumno por nombre y apellidos
//	public String findByApellidos(String apellidos) {
//		String res = "";
//		List<Alumno> alumnos = alumnoMapRepository.findByApellidos(apellidos);
//		for (Alumno alumno : alumnos) {
//				res += alumno + "<br>\n";
//		}
//		if (res.isEmpty()) return "LISTA VACÍA";
//		return res;
//	}
	public String findByApellidos(String apellidos) {
		String res = "";
		List<Alumno> alumnos = repositorioAlumno.findByApellidos(apellidos);
		for (Alumno alumno : alumnos) {
				res += alumno + "<br>\n";
		}
		if (res.isEmpty()) return "LISTA VACÍA";
		return res;
	}

}
