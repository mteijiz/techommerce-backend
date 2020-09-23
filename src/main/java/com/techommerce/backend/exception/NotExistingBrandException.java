package com.techommerce.backend.exception;

public class NotExistingBrandException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotExistingBrandException() {
		super();
	}

	public NotExistingBrandException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotExistingBrandException(String message) {
		super(message);
	}

}
