package com.techommerce.backend.exception;

import java.util.function.Supplier;

public class NotExistingCategoryException extends RuntimeException{

	public NotExistingCategoryException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NotExistingCategoryException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
	
}
