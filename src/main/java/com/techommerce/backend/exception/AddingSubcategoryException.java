package com.techommerce.backend.exception;

public class AddingSubcategoryException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public AddingSubcategoryException(String message, Throwable cause) {
		super(message, cause);
	}

	public AddingSubcategoryException(String message) {
		super(message);
	}

}
