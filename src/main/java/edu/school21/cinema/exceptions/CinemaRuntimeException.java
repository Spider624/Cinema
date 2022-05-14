package edu.school21.cinema.exceptions;

public class CinemaRuntimeException extends RuntimeException {

	public CinemaRuntimeException() {
	}

	public CinemaRuntimeException(String message) {
		super(message);
	}

	public CinemaRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public CinemaRuntimeException(Throwable cause) {
		super(cause);
	}

	public CinemaRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
