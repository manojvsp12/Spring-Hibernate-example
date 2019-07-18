package com.yorbit.casestudy.taskmanagement.service;

import java.util.Set;

import com.yorbit.casestudy.taskmanagement.dto.TaskDTO;
import com.yorbit.casestudy.taskmanagement.exception.TaskManagementAppException;

/**
 * The Interface TaskManagementService.
 *
 * @param <P> the generic type
 * @param <E> the element type
 */
public interface TaskManagementService<P, E> {

	/**
	 * Gets the all project names.
	 *
	 * @return the all project names
	 * @throws TaskManagementAppException the task management app exception
	 */
	Set<P> getAllProjectNames() throws TaskManagementAppException;;

	/**
	 * Gets the employees.
	 *
	 * @param projectId the project id
	 * @return the employees
	 * @throws TaskManagementAppException the task management app exception
	 */
	Set<E> getEmployees(String projectId) throws TaskManagementAppException;

	/**
	 * Adds the task.
	 *
	 * @param projectId the project id
	 * @param task the task
	 * @return true, if successful
	 * @throws TaskManagementAppException the task management app exception
	 */
	boolean addTask(String projectId, TaskDTO task) throws TaskManagementAppException;

	/**
	 * Gets the project details.
	 *
	 * @param projectId the project id
	 * @return the project details
	 * @throws TaskManagementAppException the task management app exception
	 */
	Set<P> getProjectDetails(String projectId) throws TaskManagementAppException;

}