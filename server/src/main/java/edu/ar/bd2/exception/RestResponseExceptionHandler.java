package edu.ar.bd2.exception;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import edu.ar.bd2.api.error.ApiError;
import edu.ar.bd2.api.error.ApiValidationError;
import edu.ar.bd2.api.exception.NotFoundException;
import edu.ar.bd2.api.exception.ServiceException;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestResponseExceptionHandler.class);

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
		LOGGER.error("Handling NotFoundException");
		ApiError apiError = buildApiError(ex);
		return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<Object> handleServiceException(ServiceException ex) {
		LOGGER.error("Handling ServiceException");
		ApiError apiError = buildApiError(ex);
		return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleServiceException(IllegalArgumentException ex) {
		LOGGER.error("Handling IllegalArgumentException");
		ApiError error = new ApiError(HttpStatus.BAD_REQUEST.toString());
		error.setMessage("IllegalArgumentException trying to parse the request");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(OptimisticLockingFailureException.class)
	public ResponseEntity<Object> handleOptimisticLockingFailureException(OptimisticLockingFailureException ex) {
		LOGGER.error("Handling OptimisticLockingFailureException");
		ApiError error = new ApiError(HttpStatus.CONFLICT.toString());
		error.setMessage("Optimistick locking during requested operation");
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		LOGGER.error("Handling MethodArgumentNotValidException");
		ApiError error = new ApiError(HttpStatus.BAD_REQUEST.toString());
		error.setMessage("Validation error");
		ex.getBindingResult().getFieldErrors().stream()
				.forEach(fieldError -> error.addSubError(new ApiValidationError(fieldError.getField(),
						fieldError.getDefaultMessage(), fieldError.getRejectedValue())));
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}


	private ApiError buildApiError(ServiceException ex) {
		HttpStatus httpStatus = Objects.nonNull(ex.getHttpStatus()) ? ex.getHttpStatus()
				: HttpStatus.INTERNAL_SERVER_ERROR;
		ApiError apiError = new ApiError(httpStatus.toString());
		apiError.setMessage(ex.getMessage());
		return apiError;
	}

}
