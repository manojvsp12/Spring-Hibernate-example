package com.yorbit.casestudy.taskmanagement.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

/**
 * The Class Task.
 * 
 * @author Manoj SP
 */
@Entity
public class Task implements Comparable<Task> {

	@Id
	@GenericGenerator(name = "task_id_generator", strategy = "com.yorbit.casestudy.taskmanagement.generator.TaskIdGenerator")
	@GeneratedValue(generator = "task_id_generator")
	@Column(nullable = false, unique = true, updatable = false)
	private String taskId;

	@Column(nullable = false, unique = false, updatable = true)
	private String description;

	@Column(nullable = false, unique = false, updatable = true)
	private LocalDate startDateOfTask;

	@Column(nullable = false, unique = false, updatable = true)
	private LocalDate dueDateOfTask;

	@Column(nullable = false, unique = false, updatable = true)
	private LocalDateTime createdTimestamp;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "emp_task", joinColumns = { @JoinColumn(name = "task_id") }, inverseJoinColumns = {
			@JoinColumn(name = "employee_id") })
	private Set<Employee> employees;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "projectId")
	private Project project;

	/**
	 * Instantiates a new task.
	 */
	public Task() {

	}

	/**
	 * Instantiates a new task.
	 *
	 * @param taskId the task id
	 * @param description the description
	 * @param startDateOfTask the start date of task
	 * @param dueDateOfTask the due date of task
	 * @param createdTimestamp the created timestamp
	 * @param employees the employees
	 */
	public Task(String taskId, String description, LocalDate startDateOfTask, LocalDate dueDateOfTask,
			LocalDateTime createdTimestamp, Set<Employee> employees) {
		this.taskId = taskId;
		this.description = description;
		this.startDateOfTask = startDateOfTask;
		this.dueDateOfTask = dueDateOfTask;
		this.createdTimestamp = createdTimestamp;
		this.employees = employees;
	}

	/**
	 * Gets the task id.
	 *
	 * @return the task id
	 */
	public String getTaskId() {
		return taskId;
	}

	/**
	 * Sets the task id.
	 *
	 * @param taskId the new task id
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the start date of task.
	 *
	 * @return the start date of task
	 */
	public LocalDate getStartDateOfTask() {
		return startDateOfTask;
	}

	/**
	 * Sets the start date of task.
	 *
	 * @param startDateOfTask the new start date of task
	 */
	public void setStartDateOfTask(LocalDate startDateOfTask) {
		this.startDateOfTask = startDateOfTask;
	}

	/**
	 * Gets the due date of task.
	 *
	 * @return the due date of task
	 */
	public LocalDate getDueDateOfTask() {
		return dueDateOfTask;
	}

	/**
	 * Sets the due date of task.
	 *
	 * @param dueDateOfTask the new due date of task
	 */
	public void setDueDateOfTask(LocalDate dueDateOfTask) {
		this.dueDateOfTask = dueDateOfTask;
	}

	/**
	 * Gets the created timestamp.
	 *
	 * @return the created timestamp
	 */
	public LocalDateTime getCreatedTimestamp() {
		return createdTimestamp;
	}

	/**
	 * Sets the created timestamp.
	 *
	 * @param createdTimestamp the new created timestamp
	 */
	public void setCreatedTimestamp(LocalDateTime createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	/**
	 * Gets the project.
	 *
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * Sets the project.
	 *
	 * @param project the new project
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 * Gets the employees.
	 *
	 * @return the employees
	 */
	public Set<Employee> getEmployees() {
		return employees;
	}

	/**
	 * Sets the employees.
	 *
	 * @param employees the new employees
	 */
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((dueDateOfTask == null) ? 0 : dueDateOfTask.hashCode());
		result = prime * result + ((startDateOfTask == null) ? 0 : startDateOfTask.hashCode());
		result = prime * result + ((taskId == null) ? 0 : taskId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (dueDateOfTask == null) {
			if (other.dueDateOfTask != null)
				return false;
		} else if (!dueDateOfTask.equals(other.dueDateOfTask))
			return false;
		if (startDateOfTask == null) {
			if (other.startDateOfTask != null)
				return false;
		} else if (!startDateOfTask.equals(other.startDateOfTask))
			return false;
		if (taskId == null) {
			if (other.taskId != null)
				return false;
		} else if (!taskId.equals(other.taskId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Task obj) {
		return this.getTaskId().compareTo(obj.getTaskId());
	}

}
