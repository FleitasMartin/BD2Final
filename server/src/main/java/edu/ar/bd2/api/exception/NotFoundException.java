package edu.ar.bd2.api.exception;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class NotFoundException extends ServiceException {

	public NotFoundException() {
		super(HttpStatus.NOT_FOUND);
	}

	public NotFoundException(String message) {
		super(HttpStatus.NOT_FOUND, message);
	}
}
