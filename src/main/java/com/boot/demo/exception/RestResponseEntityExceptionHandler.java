package com.boot.demo.exception;

import java.util.Set;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EmployeeNotFoundException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessage employeeNotFoundHandler(EmployeeNotFoundException exception) {
		return new ErrorMessage(HttpStatus.NOT_FOUND,exception.getMessage());
	}
	
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage genericExceptionHandler(Exception exception) {
		return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR,exception.getMessage());
	}
	
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorMessage>  constraintViolationException(ConstraintViolationException exception) {
		Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        String errorMessage = violations.iterator().next().getMessage();
        ErrorMessage errorResponse = new ErrorMessage(HttpStatus.BAD_REQUEST,errorMessage);
        return ResponseEntity.badRequest().body(errorResponse);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage  constraintViolationException(DataIntegrityViolationException exception) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST,"Please check the input data.");
	}
	
	
	
}
