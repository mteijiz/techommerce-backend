package com.techommerce.backend.exception;

public class EmptySubcategoryListException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmptySubcategoryListException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmptySubcategoryListException(String message) {
		super(message);
	}

}
