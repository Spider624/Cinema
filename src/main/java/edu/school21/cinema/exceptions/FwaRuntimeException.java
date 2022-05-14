package edu.school21.cinema.exceptions;

public class FwaRuntimeException extends RuntimeException {

	public FwaRuntimeException() {
	}

	public FwaRuntimeException(String message) {
		super(message);
	}

	public FwaRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public FwaRuntimeException(Throwable cause) {
		super(cause);
	}

	public FwaRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
