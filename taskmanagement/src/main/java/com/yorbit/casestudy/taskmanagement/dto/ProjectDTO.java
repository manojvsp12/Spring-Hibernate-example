package com.yorbit.casestudy.taskmanagement.dto;

import java.util.Set;

/**
 * The Class ProjectDTO.
 *
 * @author Manoj SP
 */
public class ProjectDTO {

	private String projectId;
	
	private String projectName;
	
	private Set<TaskDTO> tasks;
	
	/**
	 * Instantiates a new project DTO.
	 */
	public ProjectDTO() {

	}
	
	/**
	 * Instantiates a new project DTO.
	 *
	 * @param projectId the project id
	 * @param projectName the project name
	 * @param tasks the tasks
	 */
	public ProjectDTO(String projectId, String projectName, Set<TaskDTO> tasks) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.tasks = tasks;
	}

	/**
	 * Gets the project id.
	 *
	 * @return the project id
	 */
	public String getProjectId() {
		return projectId;
	}
	
	/**
	 * Sets the project id.
	 *
	 * @param projectId the new project id
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	/**
	 * Gets the project name.
	 *
	 * @return the project name
	 */
	public String getProjectName() {
		return projectName;
	}
	
	/**
	 * Sets the project name.
	 *
	 * @param projectName the new project name
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	/**
	 * Gets the tasks.
	 *
	 * @return the tasks
	 */
	public Set<TaskDTO> getTasks() {
		return tasks;
	}
	
	/**
	 * Sets the tasks.
	 *
	 * @param tasks the new tasks
	 */
	public void setTasks(Set<TaskDTO> tasks) {
		this.tasks = tasks;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((projectId == null) ? 0 : projectId.hashCode());
		result = prime * result + ((projectName == null) ? 0 : projectName.hashCode());
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
		ProjectDTO other = (ProjectDTO) obj;
		if (projectId == null) {
			if (other.projectId != null)
				return false;
		} else if (!projectId.equals(other.projectId))
			return false;
		if (projectName == null) {
			if (other.projectName != null)
				return false;
		} else if (!projectName.equals(other.projectName))
			return false;
		return true;
	}
	
}
