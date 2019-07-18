package com.yorbit.casestudy.taskmanagement.exception;

import static com.yorbit.casestudy.taskmanagement.constant.TaskManagementConstants.UNKNOWN_ERROR_OCCURED;

import java.util.Collections;
import java.util.Locale;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.yorbit.casestudy.taskmanagement.dto.ErrorDTO;

/**
 * The Class TaskManagementExceptionHandler.
 *
 * @author Manoj SP
 */
@RestControllerAdvice
public class TaskManagementExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(TaskManagementExceptionHandler.class);
	
	@Autowired
	private MessageSource messageSource;

	/**
	 * Handle all exceptions.
	 *
	 * @param ex the ex
	 * @param request the request
	 * @return the response entity
	 */
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		logger.error("unknown error", ex);
		TaskManagementAppException e = new TaskManagementAppException(UNKNOWN_ERROR_OCCURED);
		logger.error("throwing", e);
		return new ResponseEntity<>(buildExceptionResponse(e), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Handle validation failed exceptions.
	 *
	 * @param ex the ex
	 * @param request the request
	 * @return the response entity
	 */
	@ExceptionHandler(ValidationFailedException.class)
	protected ResponseEntity<Object> handleValidationFailedExceptions(ValidationFailedException ex,
			WebRequest request) {
		logger.error("validationFailedException thrown with status 406", ex);
		return new ResponseEntity<>(buildExceptionResponse(ex), HttpStatus.NOT_ACCEPTABLE);
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler#handleExceptionInternal(java.lang.Exception, java.lang.Object, org.springframework.http.HttpHeaders, org.springframework.http.HttpStatus, org.springframework.web.context.request.WebRequest)
	 */
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object errorMessage,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.error("Spring based Excepion", ex);
		return handleAllExceptions(ex, request);
	}

	/**
	 * Builds the exception response.
	 *
	 * @param e the e
	 * @return the object
	 */
	private Object buildExceptionResponse(Exception e) {
		try {
			Locale locale = LocaleContextHolder.getLocale();

			if (e.getClass().isAssignableFrom(ValidationFailedException.class)) {
				return ((ValidationFailedException) e).getErrors().stream().map(
						error -> error.setErrorMessage(messageSource.getMessage(error.getErrorMessage(), null, locale)))
						.collect(Collectors.toSet());
			} else {
				ErrorDTO error = new ErrorDTO(null, messageSource.getMessage(e.getMessage(), null, locale));
				return Collections.singletonList(error);
			}
		} catch (NoSuchMessageException ex) {
			logger.error("NoSuchMessageException", ex);
			ErrorDTO error = new ErrorDTO(null, e.getMessage());
			return Collections.singletonList(error);
		}
	}
}
