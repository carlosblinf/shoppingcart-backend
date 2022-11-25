package com.carlosblinf.shoppingcart.exceptions;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class ExceptionHandlerAdvice {
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> notFoundException(NotFoundException ex){
		String message = ex.getMessage();

		return getErrorResponse(message, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<?> customException(CustomException ex){
		String message = ex.getMessage();
		HttpStatus httpStatus = ex.getHttpStatus();

		return getErrorResponse(message, httpStatus);
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<?> validationException(ValidationException ex){
		String message = ex.getMessage();

		return getErrorResponse(message, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> unhandledExceptions(Exception ex){
		String message = NestedExceptionUtils.getMostSpecificCause(ex).getMessage();
		
		ex.printStackTrace();
		
		return getErrorResponse(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ResponseEntity<?> getErrorResponse (Object error, HttpStatus httpStatus){
		Map<String, Object> response = new HashMap<>();
		response.put("error", error);

		return new ResponseEntity<>(response, httpStatus);
	}

}
