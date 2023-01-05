package com.luv2code.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {
	
	// Exception Handler CustomerNotFound exc
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException exc){
		
		// Create CustomerErrorResponse Instance
		CustomerErrorResponse error = new CustomerErrorResponse(
					HttpStatus.NOT_FOUND.value(),
					exc.getMessage(),
					System.currentTimeMillis()
				);
		
		// return ResponseEntity
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	// Exception Handler for any exception (Generic exception handler)
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(Exception exc){
		
		// Create CustomerErrorResponse Instance
		CustomerErrorResponse error = new CustomerErrorResponse(
					HttpStatus.BAD_REQUEST.value(),
					exc.getMessage(),
					System.currentTimeMillis()
				);
		
		// return ResponseEntity
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
}
