package com.techommerce.backend.exception;

public class ExistsMainImageOfAProductException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExistsMainImageOfAProductException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ExistsMainImageOfAProductException(String arg0) {
		super(arg0);
	}

}
