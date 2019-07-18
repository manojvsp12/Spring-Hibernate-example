package com.yorbit.casestudy.taskmanagement.util;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;

import com.yorbit.casestudy.taskmanagement.dto.ErrorDTO;
import com.yorbit.casestudy.taskmanagement.exception.ValidationFailedException;

/**
 * The Class DataValidationUtil - utility to validate {@code Errors} from
 * validator and throw exception compiling all errors.
 *
 * @author Manoj SP
 */
public final class ValidationUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(ValidationUtil.class);

	/**
	 * Get list of errors from error object and build and throw {@code ValidationFailedException}.
	 *
	 * @param errors the errors
	 * @throws ValidationFailedException the ValidationFailedException
	 */
	public static void validate(Errors errors) {
		if (errors.hasErrors()) {
			Set<ErrorDTO> errorList = errors.getAllErrors().stream()
					.map(error -> new ErrorDTO(error.getCode(), error.getDefaultMessage()))
					.collect(Collectors.toCollection(LinkedHashSet::new));
			logger.debug("throwing  ValidationFailedException");
			throw new ValidationFailedException(errorList);
		}
	}

}
