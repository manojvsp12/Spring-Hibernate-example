package com.yorbit.casestudy.taskmanagement.test.controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionException;
import org.springframework.validation.Errors;
import org.springframework.web.context.WebApplicationContext;

import com.yorbit.casestudy.taskmanagement.constant.TaskManagementConstants;
import com.yorbit.casestudy.taskmanagement.controller.TaskManagementController;
import com.yorbit.casestudy.taskmanagement.dto.EmployeeDTO;
import com.yorbit.casestudy.taskmanagement.dto.ProjectDTO;
import com.yorbit.casestudy.taskmanagement.dto.TaskDTO;
import com.yorbit.casestudy.taskmanagement.exception.TaskManagementAppException;
import com.yorbit.casestudy.taskmanagement.service.TaskManagementService;
import com.yorbit.casestudy.taskmanagement.validator.TaskValidator;

/**
 * @author Manoj SP
 *
 */
@ContextConfiguration(classes = { TestContext.class, WebApplicationContext.class })
@RunWith(SpringRunner.class)
@WebMvcTest
@ActiveProfiles("test")
public class TaskManagementControllerTest {

	@InjectMocks
	private TaskManagementController controller;

	@Mock
	private TaskValidator validator;

	@Mock
	private TaskManagementService<ProjectDTO, EmployeeDTO> service;

	@Mock
	private Errors errors;

	@Test
	public void testProjectList() throws TaskManagementAppException {
		Set<ProjectDTO> proj = Collections.singleton(new ProjectDTO());
		when(service.getAllProjectNames()).thenReturn(proj);
		assertTrue(controller.projectList().getBody().equals(proj));
	}

	@SuppressWarnings("serial")
	@Test
	public void testProjectListException() throws TaskManagementAppException {
		when(service.getAllProjectNames()).thenThrow(new TransactionException("") {
		});
		try {
			controller.projectList();
		} catch (TaskManagementAppException e) {
			assertTrue(e.getMessage().contentEquals(TaskManagementConstants.FAILED_DB_OPERATIONS));
		}
	}

	@Test
	public void testEmployeeList() throws TaskManagementAppException {
		Set<EmployeeDTO> emp = Collections.singleton(new EmployeeDTO());
		when(service.getEmployees("")).thenReturn(emp);
		assertTrue(controller.employeeList("").getBody().equals(emp));
	}

	@SuppressWarnings("serial")
	@Test
	public void testEmployeeListException() throws TaskManagementAppException {
		when(service.getEmployees("")).thenThrow(new TransactionException("") {
		});
		try {
			controller.employeeList("");
		} catch (TaskManagementAppException e) {
			assertTrue(e.getMessage().contentEquals(TaskManagementConstants.FAILED_DB_OPERATIONS));
		}
	}

	@Test
	public void testAddTask() throws TaskManagementAppException {
		TaskDTO task = new TaskDTO();
		when(service.addTask("", task)).thenReturn(true);
		assertTrue(controller.addTask("", task, errors).getBody());
	}

	@SuppressWarnings("serial")
	@Test
	public void testAddTaskException() throws TaskManagementAppException {
		when(service.addTask("", null)).thenThrow(new TransactionException("") {
		});
		try {
			controller.addTask("", null, null);
		} catch (TaskManagementAppException e) {
			assertTrue(e.getMessage().contentEquals(TaskManagementConstants.FAILED_DB_OPERATIONS));
		}
	}

	public void testprojectDetails() throws TaskManagementAppException {
		Set<ProjectDTO> proj = Collections.singleton(new ProjectDTO());
		when(service.getProjectDetails("")).thenReturn(proj);
		assertTrue(controller.projectDetails("").getBody().equals(proj));
	}

	@SuppressWarnings("serial")
	@Test
	public void testprojectDetailsException() throws TaskManagementAppException {
		when(service.getProjectDetails("")).thenThrow(new TransactionException("") {
		});
		try {
			controller.projectDetails("");
		} catch (TaskManagementAppException e) {
			assertTrue(e.getMessage().contentEquals(TaskManagementConstants.FAILED_DB_OPERATIONS));
		}
	}
}
