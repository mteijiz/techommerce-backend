package com.techommerce.backend.exception;

public class ExistingCategoryNameException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExistingCategoryNameException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExistingCategoryNameException(String message) {
		super(message);
	}

}
