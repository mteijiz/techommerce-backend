package com.techommerce.backend.exception;

public class EmptyOrderListException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmptyOrderListException() {
		super();
	}

	public EmptyOrderListException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public EmptyOrderListException(String message) {
		super(message);
	}

}
