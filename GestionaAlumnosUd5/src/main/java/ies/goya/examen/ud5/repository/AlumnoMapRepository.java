package ies.goya.examen.ud5.repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import ies.goya.examen.ud5.model.Alumno;

@Repository
public class AlumnoMapRepository {
	protected Map<Long,Alumno> repo;

	public AlumnoMapRepository() {
		repo = new LinkedHashMap<Long,Alumno>();
		cargaDatos();
	}
	
	
	// Devolver un alumno por su id
	public Alumno get(Long id) {
		return repo.get(id);
	}
	
	// Devolver todos los alumnos
	public List<Alumno> getAll() {
		return new ArrayList<Alumno>(repo.values());
	}
	
	// Guardar o sobreescribir un alumno
	public void save(Alumno alumno) {
		repo.put(alumno.getId(), alumno);
	}
	
	// Borrar un alumno a partir de su id
	public void del(Long id) {
		repo.remove(id);
	}
	
	// Buscar un alumno por nombre y apellidos
	public Alumno findByNombreAndApellidos(String nombre, String apellidos) {
		for (Alumno alum : repo.values()) {
			if (alum.getNombre().equals(nombre) && (alum.getApellidos().equals(apellidos))) {
				return alum;
			}
		}
		return null;
	}
	
	// Buscar alumnos por nombre
	public List<Alumno> findByNombre(String nombre) {
		List<Alumno> alumnos = new ArrayList<Alumno>();
		
		for (Alumno alum : repo.values()) {
			if (alum.getNombre().equals(nombre)) {
				alumnos.add(alum);
			}
		}
		return alumnos;
	}
	
	// Buscar alumnos por apellidos
	public List<Alumno> findByApellidos(String apellidos) {
		List<Alumno> alumnos = new ArrayList<Alumno>();

		for (Alumno alum : repo.values()) {
			if (alum.getApellidos().equals(apellidos)) {
				alumnos.add(alum);
			}
		}
		return alumnos;
	}
	
	private void cargaDatos() {
		repoAddOne(new Alumno("Benjamín Alfredo","Avilés Vargas","benjamin.aviles1@educa.madrid.org"));
		repoAddOne(new Alumno("Juan Antonio","Aznar Delgado","juan.aznar2@educa.madrid.org"));
		repoAddOne(new Alumno("David","Barroso Martínez","dbm655@educa.madrid.org"));
		repoAddOne(new Alumno("Sergio","Berrendero Toledano","sergio.berrendero@educa.madrid.org"));
		repoAddOne(new Alumno("Lucas","Chacón Langa","lucas.chacon4@educa.madrid.org"));
		repoAddOne(new Alumno("Ainhoa Nicole","Conforme Alarcón","ainhoa.conforme1@educa.madrid.org"));
		repoAddOne(new Alumno("Hicham","El Farissi Ahram","hicham.el@educa.madrid.org"));
		repoAddOne(new Alumno("Laila","El Haddad Rouas","laila.el18@educa.madrid.org"));
		repoAddOne(new Alumno("Ivyel","Gago Jiménez","diego.gago@educa.madrid.org"));
		repoAddOne(new Alumno("Lorena","Garvín Navarrete","lgn546@educa.madrid.org"));
		repoAddOne(new Alumno("Noé Moisés","Guamán Álvarez","noe.guaman@educa.madrid.org"));
		repoAddOne(new Alumno("Kevin","Incio Hernández","kevin.incio@educa.madrid.org"));
		repoAddOne(new Alumno("Ángela","Mancheño Sánchez","angela.mancheno@educa.madrid.org"));
		repoAddOne(new Alumno("Mario","Martín García","mario.martin53@educa.madrid.org"));
		repoAddOne(new Alumno("Javier","Páez Alvarado","javier.paezalvarado@educa.madrid.org"));
		repoAddOne(new Alumno("Lucas","Sánchez Solera","lucas.sanchez3@educa.madrid.org"));
		repoAddOne(new Alumno("Rodrigo","Serrano Jiménez","rodrigo.serrano@educa.madrid.org"));
		repoAddOne(new Alumno("Iván","Turro Arroyo","ita917@educa.madrid.org"));
	}
	
	private void repoAddOne(Alumno alumno) {
		repo.put(alumno.getId(), alumno);
	}
	
	
}
