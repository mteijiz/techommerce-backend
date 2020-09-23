package com.techommerce.backend.exception;

public class ExistingSubcategoryNameException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ExistingSubcategoryNameException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExistingSubcategoryNameException(String message) {
		super(message);
	}

}
