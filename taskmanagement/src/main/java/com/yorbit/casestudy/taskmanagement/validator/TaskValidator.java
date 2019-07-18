package com.yorbit.casestudy.taskmanagement.validator;

import static com.yorbit.casestudy.taskmanagement.constant.TaskManagementConstants.DATE_FORMAT;
import static com.yorbit.casestudy.taskmanagement.constant.TaskManagementConstants.EMPTY_EMP_ERROR_MSG;
import static com.yorbit.casestudy.taskmanagement.constant.TaskManagementConstants.FIELD_DESC;
import static com.yorbit.casestudy.taskmanagement.constant.TaskManagementConstants.FIELD_DESC_ERROR_MSG;
import static com.yorbit.casestudy.taskmanagement.constant.TaskManagementConstants.FIELD_EMPLOYEES;
import static com.yorbit.casestudy.taskmanagement.constant.TaskManagementConstants.FIELD_END_DATE;
import static com.yorbit.casestudy.taskmanagement.constant.TaskManagementConstants.FIELD_END_DT_ERROR_MSG;
import static com.yorbit.casestudy.taskmanagement.constant.TaskManagementConstants.FIELD_START_DATE;
import static com.yorbit.casestudy.taskmanagement.constant.TaskManagementConstants.FIELD_START_DT_ERROR_MSG;
import static com.yorbit.casestudy.taskmanagement.constant.TaskManagementConstants.FUTURE_END_DT_ERROR_MSG;
import static com.yorbit.casestudy.taskmanagement.constant.TaskManagementConstants.FUTURE_START_DT_ERROR_MSG;
import static com.yorbit.casestudy.taskmanagement.constant.TaskManagementConstants.START_DATE_GT_END_DT_ERROR_MSG;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.yorbit.casestudy.taskmanagement.dto.TaskDTO;
import com.yorbit.casestudy.taskmanagement.util.ValidationUtil;

/**
 * The Class TaskValidator.
 *
 * @author Manoj SP
 */
@Component
public class TaskValidator implements Validator {
	
	@Autowired
	private Environment env;

	private static final Logger logger = LoggerFactory.getLogger(TaskValidator.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(TaskDTO.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		TaskDTO task = (TaskDTO) target;
		try {
			if (Objects.isNull(task.getDesc()) || task.getDesc().isEmpty()) {
				logger.debug("input desc is null: {} empty: {}", Objects.isNull(task.getDesc()),
						task.getDesc().isEmpty());
				logger.error("invalid description provided as input");
				errors.reject(FIELD_DESC, FIELD_DESC_ERROR_MSG);
			}

			if (!isValidDate(task.getStartDate())) {
				logger.debug("Future start date provided as input");
				errors.reject(FIELD_START_DATE, FUTURE_START_DT_ERROR_MSG);
			}
		} catch (DateTimeParseException e) {
			logger.error("Invalid start date provided as input", e);
			errors.reject(FIELD_START_DATE, FIELD_START_DT_ERROR_MSG);
		}

		try {
			if (!isValidDate(task.getEndDate())) {
				logger.debug("Future end date provided as input");
				errors.reject(FIELD_END_DATE, FUTURE_END_DT_ERROR_MSG);
			}
		} catch (DateTimeParseException e) {
			logger.error("Invalid end date provided as input", e);
			errors.reject(FIELD_END_DATE, FIELD_END_DT_ERROR_MSG);
		}

		if (!errors.hasErrors() && LocalDate
				.parse(task.getStartDate(), DateTimeFormatter.ofPattern(env.getProperty(DATE_FORMAT))).isAfter(LocalDate
						.parse(task.getEndDate(), DateTimeFormatter.ofPattern(env.getProperty(DATE_FORMAT))))) {
			logger.debug("start date greater than end date is provided");
			errors.reject(FIELD_START_DATE, START_DATE_GT_END_DT_ERROR_MSG);
		}

		if (Objects.isNull(task.getAssignedEmployees()) || task.getAssignedEmployees().isEmpty()) {
			logger.debug("input employee list is null: {} empty: {}", Objects.isNull(task.getAssignedEmployees()),
					task.getAssignedEmployees().isEmpty());
			errors.reject(FIELD_EMPLOYEES, EMPTY_EMP_ERROR_MSG);
		}

		ValidationUtil.validate(errors);
	}

	/**
	 * Checks if is valid date.
	 *
	 * @param date
	 *            the date
	 * @return true, if is valid date
	 */
	private boolean isValidDate(String date) {
		logger.debug("inside isValidDate");
		return Objects.nonNull(date) && LocalDate.parse(date,
				DateTimeFormatter.ofPattern(env.getProperty(DATE_FORMAT)).withResolverStyle(ResolverStyle.STRICT))
				.isBefore(LocalDate.now());
	}

}
