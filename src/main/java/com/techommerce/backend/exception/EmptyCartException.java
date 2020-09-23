package com.techommerce.backend.exception;

public class EmptyCartException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmptyCartException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public EmptyCartException(String arg0) {
		super(arg0);
	}

	
	
}
