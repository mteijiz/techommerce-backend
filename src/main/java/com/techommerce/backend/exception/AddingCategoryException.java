package com.techommerce.backend.exception;

public class AddingCategoryException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public AddingCategoryException(String message, Throwable cause) {
		super(message, cause);
	}

	public AddingCategoryException(String message) {
		super(message);
	}

	
}
