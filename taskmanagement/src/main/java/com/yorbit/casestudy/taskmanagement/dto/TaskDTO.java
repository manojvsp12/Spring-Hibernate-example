package com.yorbit.casestudy.taskmanagement.dto;

import java.util.Set;

/**
 * The Class TaskDTO.
 *
 * @author Manoj SP
 */
public class TaskDTO {
	
	private String taskId;
	
	private String desc;
	
	private String startDate;
	
	private String endDate;
	
	private Set<EmployeeDTO> assignedEmployees;

	/**
	 * Instantiates a new task DTO.
	 */
	public TaskDTO() {

	}

	/**
	 * Instantiates a new task DTO.
	 *
	 * @param taskId the task id
	 * @param desc the desc
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param assignedEmployees the assigned employees
	 */
	public TaskDTO(String taskId, String desc, String startDate, String endDate, Set<EmployeeDTO> assignedEmployees) {
		this.taskId = taskId;
		this.desc = desc;
		this.startDate = startDate;
		this.endDate = endDate;
		this.assignedEmployees = assignedEmployees;
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
	 * Gets the desc.
	 *
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * Sets the desc.
	 *
	 * @param desc the new desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * Gets the assigned employees.
	 *
	 * @return the assigned employees
	 */
	public Set<EmployeeDTO> getAssignedEmployees() {
		return assignedEmployees;
	}

	/**
	 * Sets the assigned employees.
	 *
	 * @param assignedEmployees the new assigned employees
	 */
	public void setAssignedEmployees(Set<EmployeeDTO> assignedEmployees) {
		this.assignedEmployees = assignedEmployees;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
		TaskDTO other = (TaskDTO) obj;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

}
