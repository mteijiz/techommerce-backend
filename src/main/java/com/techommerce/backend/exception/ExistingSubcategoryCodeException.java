package com.techommerce.backend.exception;

public class ExistingSubcategoryCodeException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ExistingSubcategoryCodeException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExistingSubcategoryCodeException(String message) {
		super(message);
	}

}
