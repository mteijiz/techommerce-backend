package com.techommerce.backend.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ExistingBrandException.class)
	public ResponseEntity<?> ExistingBrandException(ExistingBrandException e, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(InvalidRequestBrandException.class)
	public ResponseEntity<?> InvalidRequestBrandException(InvalidRequestBrandException e, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmptyBrandListException.class)
	public ResponseEntity<?> EmptyBrandListException(EmptyBrandListException e, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NotExistingBrandException.class)
	public ResponseEntity<?> NotExistingBrandException(NotExistingBrandException e, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
}
