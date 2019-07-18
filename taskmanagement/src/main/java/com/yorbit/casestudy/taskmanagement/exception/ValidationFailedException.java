package com.yorbit.casestudy.taskmanagement.exception;

import java.util.Set;

import com.yorbit.casestudy.taskmanagement.dto.ErrorDTO;

/**
 * The Class ValidationFailedException.
 *
 * @author Manoj SP
 */
public class ValidationFailedException extends RuntimeException {
	
	private final transient Set<ErrorDTO> errors;

	private static final long serialVersionUID = -1326589403472226350L;

	/**
	 * Instantiates a new validation failed exception.
	 *
	 * @param errors the errors
	 */
	public ValidationFailedException(Set<ErrorDTO> errors) {
		this.errors = errors;
	}

	/**
	 * Gets the errors.
	 *
	 * @return the errors
	 */
	public Set<ErrorDTO> getErrors() {
		return errors;
	}

}
