package com.techommerce.backend.exception;

public class ExistingCategoryCodeException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ExistingCategoryCodeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ExistingCategoryCodeException(String arg0) {
		super(arg0);
	}

	
	
}
