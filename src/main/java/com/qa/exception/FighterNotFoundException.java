package com.qa.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No fighter found with that id")
public class FighterNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 342493152181432543L;

	public FighterNotFoundException() {
		super();
	}

	public FighterNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FighterNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public FighterNotFoundException(String message) {
		super(message);
	}

	public FighterNotFoundException(Throwable cause) {
		super(cause);
	}

}