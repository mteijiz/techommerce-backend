package com.techommerce.backend.exception;

public class InvalidRequestBrandException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidRequestBrandException() {
		super();
	}

	public InvalidRequestBrandException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
