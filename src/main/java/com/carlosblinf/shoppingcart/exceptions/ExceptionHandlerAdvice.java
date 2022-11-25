package com.carlosblinf.shoppingcart.exceptions;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHandlerAdvice {
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> notFoundException(NotFoundException ex){
		String message = ex.getMessage();
		
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<?> customException(CustomException ex){
		String message = ex.getMessage();
		HttpStatus httpStatus = ex.getHttpStatus();

		return new ResponseEntity<>(message, httpStatus);
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<?> validationException(ValidationException ex){
		String message = ex.getMessage();

		return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> unhandledExceptions(Exception ex){
		String message = NestedExceptionUtils.getMostSpecificCause(ex).getMessage();
		
		ex.printStackTrace();
		
		return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
