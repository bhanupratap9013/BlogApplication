package com.blog.BlogApp.exceptions;

import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.BlogApp.Dto.ApiResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity resourceNotFoundExceptionHandler(ResourceNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with userId "+e.getFieldValue()+" not found");
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArguementNotValidException(MethodArgumentNotValidException e) {
		
		Map<String, String> map = new HashMap<>();
		
		e.getBindingResult().getAllErrors().forEach((error)->{
			map.put(((FieldError)error).getField(),error.getDefaultMessage());
		});
		
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.BAD_REQUEST);
	}
	
	 @ExceptionHandler(BadCredentialsException.class)
	 public ResponseEntity<String> badCredentialsExceptionHandler(BadCredentialsException e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bad credentials");
	 }

	 @ExceptionHandler(AccessDeniedException.class)
	 public ResponseEntity<String> accessDeniedExceptionHandler(AccessDeniedException e) {
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
	 }
	 
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiResponse> handleApiException(ApiException ex) {
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, true);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
	}

}
