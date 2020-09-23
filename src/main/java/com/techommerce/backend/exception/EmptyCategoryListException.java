package com.techommerce.backend.exception;

public class EmptyCategoryListException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmptyCategoryListException() {
		super();
	}

	public EmptyCategoryListException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public EmptyCategoryListException(String arg0) {
		super(arg0);
	}
	
}
