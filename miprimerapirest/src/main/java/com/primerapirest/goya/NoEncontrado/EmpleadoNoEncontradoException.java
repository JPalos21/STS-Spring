package com.primerapirest.goya.NoEncontrado;

public class EmpleadoNoEncontradoException extends RuntimeException {
	public EmpleadoNoEncontradoException(Long id) {
		    super("Empleado no encontrado " + id);
		  }
}
