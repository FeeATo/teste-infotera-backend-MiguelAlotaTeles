package com.miguelteles.projeto.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.miguelteles.projeto.services.exception.AuthenticationException;
import com.miguelteles.projeto.services.exception.ConnectionException;
import com.miguelteles.projeto.services.exception.InvalidDataException;

//Esta classe serve para responder ao cliente um erro de requisição

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(InvalidDataException.class)
	public ResponseEntity<StandardError> invalidData(InvalidDataException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Data inválida", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}	
	
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<StandardError> authenticationException(AuthenticationException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Erro na authenticação", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(ConnectionException.class)
	public ResponseEntity<StandardError> authenticationException(ConnectionException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.resolve(Integer.parseInt(e.getMessage()));
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Erro na conexão", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}