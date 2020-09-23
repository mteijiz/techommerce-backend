package com.techommerce.backend.exception;

public class ProductQuantityIsLowerThanZeroException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProductQuantityIsLowerThanZeroException() {
		super();
	}

	public ProductQuantityIsLowerThanZeroException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ProductQuantityIsLowerThanZeroException(String message) {
		super(message);
	}

	
	
}
