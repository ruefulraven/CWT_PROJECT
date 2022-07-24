package com.gradle.cwt.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CWTExceptionController {
	
	@Autowired
	Environment environment;
	
	private String numberExceptionProperty;
	private String generalExceptionProperty;
	
	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<String> numberExceptionHandler(NumberFormatException e){
		numberExceptionProperty = environment.getProperty("REQUEST.INCORRECT_NUMBER_FORMAT");
		return new ResponseEntity<>(numberExceptionProperty, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> generalExceptionHandler(Exception e){
		generalExceptionProperty = environment.getProperty("GENERAL.EXCEPTION_MESSAGE");
		return new ResponseEntity<>(generalExceptionProperty, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
