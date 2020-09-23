package com.techommerce.backend.exception;

public class EmptyBrandListException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmptyBrandListException() {
		super();
	}

	public EmptyBrandListException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmptyBrandListException(String message) {
		super(message);
	}

	
}
