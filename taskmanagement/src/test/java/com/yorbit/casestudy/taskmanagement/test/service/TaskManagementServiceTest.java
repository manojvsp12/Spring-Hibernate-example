package com.yorbit.casestudy.taskmanagement.test.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import com.yorbit.casestudy.taskmanagement.entity.Employee;
import com.yorbit.casestudy.taskmanagement.entity.Project;
import com.yorbit.casestudy.taskmanagement.exception.TaskManagementAppException;
import com.yorbit.casestudy.taskmanagement.repository.EmployeeRepository;
import com.yorbit.casestudy.taskmanagement.repository.ProjectRepository;
import com.yorbit.casestudy.taskmanagement.repository.TaskRepository;
import com.yorbit.casestudy.taskmanagement.service.impl.TaskManagementServiceImpl;

/**
 * @author Manoj SP
 *
 */
@ContextConfiguration(classes = { TestContext.class, WebApplicationContext.class })
@RunWith(SpringRunner.class)
@WebMvcTest
@ActiveProfiles("test")
public class TaskManagementServiceTest {

	@InjectMocks
	private TaskManagementServiceImpl service;

	@Mock
	private Environment env;

	@Mock
	private EmployeeRepository empRepo;

	@Mock
	private ProjectRepository projectRepo;

	@Mock
	private TaskRepository taskRepo;

	@Test
	public void testGetAllProjectNames() throws TaskManagementAppException {
		Project project = new Project("", "", null);
		when(projectRepo.findAll()).thenReturn(Collections.singletonList(project));
		assertTrue(service.getAllProjectNames().stream()
				.allMatch(projectDTO -> projectDTO.getProjectId().equals(project.getProjectId())));
	}

	@Test
	public void testGetEmployees() throws TaskManagementAppException {
		Employee emp = new Employee("", "", new Project("", "", null), null);
		when(empRepo.findByProjectId("")).thenReturn(Collections.singleton(emp));
		assertTrue(service.getEmployees("").stream()
				.allMatch(empDTO -> empDTO.getEmployeeId().equals(emp.getEmployeeId())));
	}
}
