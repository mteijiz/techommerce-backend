package com.techommerce.backend.exception;

public class NotExistingCategoryException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NotExistingCategoryException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotExistingCategoryException(String message) {
		super(message);
	}

	
	
}
