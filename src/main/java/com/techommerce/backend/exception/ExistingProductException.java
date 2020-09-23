package com.techommerce.backend.exception;

public class ExistingProductException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExistingProductException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ExistingProductException(String arg0) {
		super(arg0);
	}

}
