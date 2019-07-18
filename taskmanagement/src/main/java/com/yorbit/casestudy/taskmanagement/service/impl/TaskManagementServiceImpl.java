package com.yorbit.casestudy.taskmanagement.service.impl;

import static com.yorbit.casestudy.taskmanagement.constant.TaskManagementConstants.DATE_FORMAT;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.yorbit.casestudy.taskmanagement.dto.EmployeeDTO;
import com.yorbit.casestudy.taskmanagement.dto.ProjectDTO;
import com.yorbit.casestudy.taskmanagement.dto.TaskDTO;
import com.yorbit.casestudy.taskmanagement.entity.Project;
import com.yorbit.casestudy.taskmanagement.entity.Task;
import com.yorbit.casestudy.taskmanagement.exception.TaskManagementAppException;
import com.yorbit.casestudy.taskmanagement.repository.EmployeeRepository;
import com.yorbit.casestudy.taskmanagement.repository.ProjectRepository;
import com.yorbit.casestudy.taskmanagement.repository.TaskRepository;
import com.yorbit.casestudy.taskmanagement.service.TaskManagementService;

/**
 * The Class TaskManagementServiceImpl.
 *
 * @author Manoj SP
 */
@Service
@Transactional
public class TaskManagementServiceImpl implements TaskManagementService<ProjectDTO, EmployeeDTO> {

	private static final Logger logger = LoggerFactory.getLogger(TaskManagementServiceImpl.class);

	@Autowired
	private Environment env;

	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private ProjectRepository projectRepo;

	@Autowired
	private TaskRepository taskRepo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yorbit.casestudy.taskmanagement.service.TaskManagementService#
	 * getAllProjectNames()
	 */
	@Override
	public Set<ProjectDTO> getAllProjectNames() throws TaskManagementAppException {
		logger.debug("inside getAllProjectNames");
		return projectRepo.findAll().stream()
				.map(project -> new ProjectDTO(project.getProjectId(), project.getProjectName(), null))
				.collect(Collectors.toSet());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yorbit.casestudy.taskmanagement.service.TaskManagementService#
	 * getEmployees(java.lang.String)
	 */
	@Override
	public Set<EmployeeDTO> getEmployees(String projectId) throws TaskManagementAppException {
		logger.debug("inside getEmployees");
		return empRepo.findByProjectId(projectId).stream()
				.map(employee -> new EmployeeDTO(employee.getEmployeeId(), employee.getEmployeeName()))
				.collect(Collectors.toSet());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yorbit.casestudy.taskmanagement.service.TaskManagementService#addTask(
	 * java.lang.String, com.yorbit.casestudy.taskmanagement.dto.TaskDTO)
	 */
	@Override
	public boolean addTask(String projectId, @RequestBody TaskDTO task) throws TaskManagementAppException {
		logger.debug("inside addTask");
		Task taskEntity = new Task(null, task.getDesc(),
				LocalDate.parse(task.getStartDate(), DateTimeFormatter.ofPattern((env.getProperty(DATE_FORMAT)))),
				LocalDate.parse(task.getEndDate(), DateTimeFormatter.ofPattern((env.getProperty(DATE_FORMAT)))),
				LocalDateTime.now(), new TreeSet<>(empRepo.findAllById(task.getAssignedEmployees().stream()
						.map(EmployeeDTO::getEmployeeId).collect(Collectors.toSet()))));
		projectRepo.findById(projectId).ifPresent(project -> {
			taskEntity.setProject(project);
			taskRepo.save(taskEntity);
		});
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yorbit.casestudy.taskmanagement.service.TaskManagementService#
	 * getProjectDetails(java.lang.String)
	 */
	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public Set<ProjectDTO> getProjectDetails(String projectId) throws TaskManagementAppException {
		if (projectId != "all" && projectRepo.existsById(projectId)) {
			logger.debug("inside getProjectDetails condition: all");
			Project project = projectRepo.getOne(projectId);
			ProjectDTO projectDTO = new ProjectDTO(project.getProjectId(), project.getProjectName(), project.getTasks()
					.stream().sorted((Task t1, Task t2) -> t1.getCreatedTimestamp().compareTo(t2.getCreatedTimestamp()))
					.map(task -> new TaskDTO(task.getTaskId(), task.getDescription(),
							task.getStartDateOfTask()
									.format(DateTimeFormatter.ofPattern((env.getProperty(DATE_FORMAT)))),
							task.getDueDateOfTask().format(DateTimeFormatter.ofPattern((env.getProperty(DATE_FORMAT)))),
							task.getEmployees().stream()
									.map(emp -> new EmployeeDTO(emp.getEmployeeId(), emp.getEmployeeName()))
									.collect(Collectors.toSet())))
					.collect(Collectors.toCollection(LinkedHashSet::new)));
			return Collections.singleton(projectDTO);
		} else {
			logger.debug("inside getProjectDetails condition: projectId");
			return projectRepo.findAll().stream().map(project -> new ProjectDTO(project.getProjectId(),
					project.getProjectName(),
					project.getTasks().stream()
							.sorted((Task t1, Task t2) -> t1.getCreatedTimestamp().compareTo(t2.getCreatedTimestamp()))
							.map(task -> new TaskDTO(task.getTaskId(), task.getDescription(),
									task.getStartDateOfTask()
											.format(DateTimeFormatter.ofPattern((env.getProperty(DATE_FORMAT)))),
									task.getDueDateOfTask()
											.format(DateTimeFormatter.ofPattern((env.getProperty(DATE_FORMAT)))),
									task.getEmployees().stream()
											.map(emp -> new EmployeeDTO(emp.getEmployeeId(), emp.getEmployeeName()))
											.collect(Collectors.toSet())))
							.collect(Collectors.toCollection(LinkedHashSet::new))))
					.collect(Collectors.toSet());
		}
	}
}
