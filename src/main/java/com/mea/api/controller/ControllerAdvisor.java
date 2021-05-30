package com.mea.api.controller;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mea.api.error.BeliverExists;
import com.mea.api.error.CultException;
import com.mea.api.error.PresenceException;
import com.mea.api.error.RegisterException;
import com.mea.api.error.ResourceNotFoundException;
import com.mea.api.misc.ApiResponseObject;
import com.mea.api.model.Beliver;

@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({ResourceNotFoundException.class, PresenceException.class,CultException.class, RegisterException.class})
	public ResponseEntity<Object> handleInvalidException(RuntimeException ex, WebRequest request) {

		Map<String, Object> res = new ApiResponseObject().response(Boolean.TRUE, ex.getMessage(), Arrays.asList());

		return new ResponseEntity<>(res, HttpStatus.OK);
	} 
	@ExceptionHandler(BeliverExists.class)
	public ResponseEntity<Object> BeliverExist(RuntimeException ex, WebRequest request) {
		String[] data = ex.getMessage().split("#");
		Map<String, Object> response = new LinkedHashMap<>();
		response.put("name", data[1]);
		response.put("id", data[2]);
		Map<String, Object> res = new ApiResponseObject().response(Boolean.TRUE, data[0].toString(),  response);

		return new ResponseEntity<>(res, HttpStatus.OK);
	} 
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		Map<String, Object> res = new ApiResponseObject().response(Boolean.TRUE, ex.getLocalizedMessage(),
				ex.getHttpMethod());

		return new ResponseEntity<Object>(res, headers, HttpStatus.OK);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, String> errors = new LinkedHashMap<>();

		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		String message = "Campo(os) inválidos!";
		
		Map<String, Object> res = new ApiResponseObject().response(Boolean.TRUE, message, errors);

		return new ResponseEntity<>(res, HttpStatus.OK);

	}
}
