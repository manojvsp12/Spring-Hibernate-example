package com.yorbit.casestudy.taskmanagement.dto;

/**
 * The Class EmployeeDTO.
 *
 * @author Manoj SP
 */
public class EmployeeDTO {

	private String employeeId;
	
	private String employeeName;

	/**
	 * Instantiates a new employee DTO.
	 */
	public EmployeeDTO() {

	}

	/**
	 * Instantiates a new employee DTO.
	 *
	 * @param employeeId the employee id
	 * @param employeeName the employee name
	 */
	public EmployeeDTO(String employeeId, String employeeName) {
		this.employeeId = employeeId;
		this.employeeName = employeeName;
	}

	/**
	 * Gets the employee id.
	 *
	 * @return the employee id
	 */
	public String getEmployeeId() {
		return employeeId;
	}

	/**
	 * Sets the employee id.
	 *
	 * @param employeeId the new employee id
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * Gets the employee name.
	 *
	 * @return the employee name
	 */
	public String getEmployeeName() {
		return employeeName;
	}

	/**
	 * Sets the employee name.
	 *
	 * @param employeeName the new employee name
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
		result = prime * result + ((employeeName == null) ? 0 : employeeName.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		EmployeeDTO other = (EmployeeDTO) obj;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId)) {
			return false;
		}
		if (employeeName == null) {
			if (other.employeeName != null) {
				return false;
			}
		} else if (!employeeName.equals(other.employeeName)) {
			return false;
		}
		return true;
	}

}
