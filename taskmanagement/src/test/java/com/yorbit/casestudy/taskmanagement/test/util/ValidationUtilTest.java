package com.yorbit.casestudy.taskmanagement.test.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.yorbit.casestudy.taskmanagement.dto.TaskDTO;
import com.yorbit.casestudy.taskmanagement.exception.ValidationFailedException;
import com.yorbit.casestudy.taskmanagement.util.ValidationUtil;

/**
 * @author Manoj SP
 *
 */
public class ValidationUtilTest {

	@Test
	public void testValidate() {
		TaskDTO task = new TaskDTO();
		Errors errors = new BeanPropertyBindingResult(task, "TaskDTO");
		errors.reject("", "");
		try {
			ValidationUtil.validate(errors);
		} catch (ValidationFailedException e) {
			assertTrue(e.getErrors().stream().allMatch(
					error -> error.getErrorFieldId().contentEquals("") && error.getErrorMessage().contentEquals("")));
		}
	}
}
