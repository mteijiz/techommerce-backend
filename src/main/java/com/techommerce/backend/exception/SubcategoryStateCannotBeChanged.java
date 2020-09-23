package com.techommerce.backend.exception;

public class SubcategoryStateCannotBeChanged extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SubcategoryStateCannotBeChanged(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public SubcategoryStateCannotBeChanged(String arg0) {
		super(arg0);
	}

}
