package com.techommerce.backend.exception;

public class ExistingBrandException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExistingBrandException() {
		super();
	}

	public ExistingBrandException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
