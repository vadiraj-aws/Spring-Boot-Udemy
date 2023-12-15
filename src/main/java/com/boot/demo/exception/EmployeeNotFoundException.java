package com.boot.demo.exception;

public class EmployeeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1421549926942465060L;

	public EmployeeNotFoundException(String message) {
		super(message);
	}

}
