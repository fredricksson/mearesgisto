package com.mea.api.security.exception;

public class AuthenticationException extends RuntimeException {

	public AuthenticationException(String message) {
		super(message);
	}

	public AuthenticationException(String message, Throwable t) {
		super(message, t);

	}

}