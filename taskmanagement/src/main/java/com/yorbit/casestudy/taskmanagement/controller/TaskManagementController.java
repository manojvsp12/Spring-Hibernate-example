package com.yorbit.casestudy.taskmanagement.controller;

import static com.yorbit.casestudy.taskmanagement.constant.TaskManagementConstants.FAILED_DB_OPERATIONS;
import static com.yorbit.casestudy.taskmanagement.constant.TaskManagementConstants.FIELD_PROJECTS;
import static com.yorbit.casestudy.taskmanagement.constant.TaskManagementConstants.FIELD_PROJECT_ERROR_MSG;

import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.hibernate.exception.JDBCConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionException;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.yorbit.casestudy.taskmanagement.dto.EmployeeDTO;
import com.yorbit.casestudy.taskmanagement.dto.ProjectDTO;
import com.yorbit.casestudy.taskmanagement.dto.TaskDTO;
import com.yorbit.casestudy.taskmanagement.exception.TaskManagementAppException;
import com.yorbit.casestudy.taskmanagement.service.TaskManagementService;
import com.yorbit.casestudy.taskmanagement.util.ValidationUtil;
import com.yorbit.casestudy.taskmanagement.validator.TaskValidator;

/**
 * The Class TaskManagementController.
 *
 * @author Manoj SP
 */
@Controller
public class TaskManagementController {

	private static final Logger logger = LoggerFactory.getLogger(TaskManagementController.class);
	
	Locale locale = LocaleContextHolder.getLocale();
	
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private TaskValidator validator;

	@Autowired
	private TaskManagementService<ProjectDTO, EmployeeDTO> service;

	/**
	 * Inits the binder.
	 *
	 * @param binder
	 *            the binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validator);
	}

	/**
	 * Home.
	 *
	 * @return the string
	 */
	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("page_title", messageSource.getMessage("page_title", null, locale));
		model.addAttribute("home_heading", messageSource.getMessage("home_heading", null, locale));
		model.addAttribute("assign_tasks_link", messageSource.getMessage("assign_tasks_link", null, locale));
		model.addAttribute("task_view_link", messageSource.getMessage("task_view_link", null, locale));
		return "home";
	}

	/**
	 * Assign tasks.
	 *
	 * @return the string
	 */
	@GetMapping("/assigntasks")
	public String assignTasks(Model model) {
		model.addAttribute("page_title", messageSource.getMessage("page_title", null, locale));
		model.addAttribute("project_label", messageSource.getMessage("project_label", null, locale));
		model.addAttribute("select_project_label", messageSource.getMessage("select_project_label", null, locale));
		model.addAttribute("desc_label", messageSource.getMessage("desc_label", null, locale));
		model.addAttribute("start_date_label", messageSource.getMessage("start_date_label", null, locale));
		model.addAttribute("end_date_label", messageSource.getMessage("end_date_label", null, locale));
		model.addAttribute("emp_label", messageSource.getMessage("emp_label", null, locale));
		model.addAttribute("add_task_button_label", messageSource.getMessage("add_task_button_label", null, locale));
		model.addAttribute("cancel_button_label", messageSource.getMessage("cancel_button_label", null, locale));
		return "assigntasks";
	}

	/**
	 * Task view.
	 *
	 * @return the string
	 */
	@GetMapping("/taskview")
	public String taskView(Model model) {
		model.addAttribute("page_title", messageSource.getMessage("page_title", null, locale));
		model.addAttribute("view_tasks_heading", messageSource.getMessage("view_tasks_heading", null, locale));
		model.addAttribute("filter_by_project_label", messageSource.getMessage("filter_by_project_label", null, locale));
		model.addAttribute("select_project_dropdown_label", messageSource.getMessage("select_project_dropdown_label", null, locale));
		model.addAttribute("all_projects_label", messageSource.getMessage("all_projects_label", null, locale));
		model.addAttribute("cancel_link", messageSource.getMessage("cancel_link", null, locale));
		return "taskview";
	}

	/**
	 * Project list.
	 *
	 * @return the response entity
	 * @throws TaskManagementAppException the task management app exception
	 */
	@GetMapping("/projectlist")
	public ResponseEntity<Set<ProjectDTO>> projectList() throws TaskManagementAppException {
		try {
			return new ResponseEntity<>(service.getAllProjectNames(), HttpStatus.OK);
		} catch (DataAccessException | TransactionException | JDBCConnectionException e) {
			logger.error("Failed to perform DB operations", e);
			throw new TaskManagementAppException(FAILED_DB_OPERATIONS, e);
		}
	}

	/**
	 * Employee list.
	 *
	 * @param projectId the project id
	 * @return the response entity
	 * @throws TaskManagementAppException the task management app exception
	 */
	@GetMapping("/{projectId}/employeelist")
	public ResponseEntity<Set<EmployeeDTO>> employeeList(@PathVariable String projectId)
			throws TaskManagementAppException {
		try {
			return new ResponseEntity<>(service.getEmployees(projectId), HttpStatus.OK);
		} catch (DataAccessException | TransactionException | JDBCConnectionException e) {
			logger.error("Failed to perform DB operations", e);
			throw new TaskManagementAppException(FAILED_DB_OPERATIONS, e);
		}
		
	}

	/**
	 * Adds the task.
	 *
	 * @param projectId the project id
	 * @param task the task
	 * @param errors the errors
	 * @return the response entity
	 * @throws TaskManagementAppException the task management app exception
	 */
	@PostMapping("/{projectId}/addTask")
	public ResponseEntity<Boolean> addTask(@PathVariable String projectId, @Validated @RequestBody TaskDTO task,
			Errors errors) throws TaskManagementAppException {
		try {
			if (Objects.isNull(projectId) || projectId.contentEquals("undefined")) {
				logger.debug("ProjectId is null: {} undefined: {}", Objects.isNull(projectId),
						Optional.ofNullable(projectId).orElse("").contentEquals("undefined"));
				errors.reject(FIELD_PROJECTS, FIELD_PROJECT_ERROR_MSG);
				ValidationUtil.validate(errors);
			}
			return new ResponseEntity<>(service.addTask(projectId, task), HttpStatus.OK);
		} catch (DataAccessException | TransactionException | JDBCConnectionException e) {
			logger.error("Failed to perform DB operations", e);
			throw new TaskManagementAppException(FAILED_DB_OPERATIONS, e);
		}
	}

	/**
	 * Project details.
	 *
	 * @param projectId the project id
	 * @return the response entity
	 * @throws TaskManagementAppException the task management app exception
	 */
	@GetMapping("/details/{projectId}")
	public ResponseEntity<Set<ProjectDTO>> projectDetails(@PathVariable String projectId)
			throws TaskManagementAppException {
		try {
			return new ResponseEntity<>(service.getProjectDetails(projectId), HttpStatus.OK);
		} catch (DataAccessException | TransactionException | JDBCConnectionException e) {
			logger.error("Failed to perform DB operations", e);
			throw new TaskManagementAppException(FAILED_DB_OPERATIONS, e);
		}
	}

}
