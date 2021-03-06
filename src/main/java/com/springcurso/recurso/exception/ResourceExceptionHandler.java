package com.springcurso.recurso.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.springcurso.exception.NotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<apiError> handlerNotFoundException(NotFoundException ex){
		
		apiError error = new apiError(HttpStatus.NOT_FOUND.value(), ex.getMessage(), new Date());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		
	}
		
		
}
