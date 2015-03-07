package com.saltoconsulting.dojo.customer.exception;

public class NoResultException extends RuntimeException {

	private static final long serialVersionUID = -9066185346252763134L;

	public NoResultException() {
	}
	
	public NoResultException(String message) {
		super(message);
	}
	
	public NoResultException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public NoResultException(Throwable cause) {
		super(cause);
	}
	
}
