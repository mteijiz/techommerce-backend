package com.techommerce.backend.exception;

public class EmptyProductListException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmptyProductListException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmptyProductListException(String message) {
		super(message);
	}

}
