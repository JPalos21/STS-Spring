package ies.goya.examen.ud5.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Email {
	//static protected long contadorIds = 1; // a quitar al meterlo en BD
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	protected String email;
	
	@ManyToOne
	Alumno alumno;
	
public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Email(Alumno alumno) {
		super();
		this.alumno = alumno;
	}

	public Email(String email, Alumno alumno) {
		super();
		this.email = email;
		this.alumno = alumno;
	}

	//	public Email() {
//		id = contadorIds++;  // a quitar al meterlo en BD
//	}
	public Email() {
		super(); 
	}
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Email(String email) {
		this();
		this.email = email;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(email);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Email other = (Email) obj;
		return Objects.equals(email, other.email);
	}
	
	@Override
	public String toString() {
		return "Email [id=" + id + ", email=" + email + "]";
	}

}
