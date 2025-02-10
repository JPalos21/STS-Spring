package com.primerapirest.goya.NoEncontrado;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmpleadoNoEncontradoConsejo {

	@ExceptionHandler(EmpleadoNoEncontradoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String empleadoNoEncontradoManejo(EmpleadoNoEncontradoException ex) {
		 return ex.getMessage();
	}
}
