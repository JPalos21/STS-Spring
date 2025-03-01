package com.primerapirest.goya.Entidad;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
@Entity
public class Empleado {
	@Id
	@GeneratedValue
	private Long id;
	private String nombre;
	private String rol;

	public Empleado() {

	}

	public Empleado(String nombre, String rol) {

		this.nombre = nombre;
		this.rol = rol;
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

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre) && Objects.equals(rol, other.rol);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre, rol);
	}

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", rol=" + rol + "]";
	}

}
