package ru.ruselprom.lib.exceptions;

public class CreoSessionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CreoSessionException() {
		super();
	}

	public CreoSessionException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CreoSessionException(String message, Throwable cause) {
		super(message, cause);
	}

	public CreoSessionException(String message) {
		super(message);
	}

	public CreoSessionException(Throwable cause) {
		super(cause);
	}
}
