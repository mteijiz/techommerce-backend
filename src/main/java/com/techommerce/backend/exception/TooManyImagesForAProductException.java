package com.techommerce.backend.exception;

public class TooManyImagesForAProductException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TooManyImagesForAProductException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public TooManyImagesForAProductException(String message) {
		super(message);
	}

}
