package com.techommerce.backend.exception;

public class ErrorSavingImageIntoAFolder extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErrorSavingImageIntoAFolder() {
		super();
	}

	public ErrorSavingImageIntoAFolder(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ErrorSavingImageIntoAFolder(String message) {
		super(message);
	}

	
	
}
