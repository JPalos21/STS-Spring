package ies.goya.examen.ud5.model;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Alumno {
	//static protected long contadorIds = 1; // a borrar cuando lo haga la BBDD
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	protected String nombre;
	protected String apellidos;
	
	@OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL, orphanRemoval = true)
	protected Set<Email> emails;
	
	public Alumno() {
		//id = contadorIds++; // a quitar al meterlo en BD
		emails = new LinkedHashSet<Email>();
	}
	
	public Alumno(String nombre, String apellidos) {
		this();
		this.nombre = nombre;
		this.apellidos = apellidos;
	}
	
	public Alumno(String nombre, String apellidos,String email) {
		this(nombre,apellidos);
		addEmail(new Email(email));
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public Set<Email> getEmails() {
		return emails;
	}
	
	public void addEmail(Email email) {
		emails.add(email);
		email.setAlumno(this);
	}
	
	public boolean delEmail(Email email) {
		return emails.remove(email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellidos, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		return Objects.equals(apellidos, other.apellidos) && Objects.equals(nombre, other.nombre);
	}
	
	@Override
	public String toString() {
		return "Alumno [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", emails=" + emails + "]";
	}

}
