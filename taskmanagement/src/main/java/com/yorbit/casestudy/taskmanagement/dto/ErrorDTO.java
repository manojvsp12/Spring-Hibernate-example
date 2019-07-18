package com.yorbit.casestudy.taskmanagement.dto;

// TODO: Auto-generated Javadoc
/**
 * The Class ErrorDTO.
 *
 * @author Manoj SP
 */
public class ErrorDTO {
	
	/** The error field id. */
	private String errorFieldId;
	
	/** The error message. */
	private String errorMessage;
	
	/**
	 * Instantiates a new error DTO.
	 *
	 * @param errorFieldId the error field id
	 * @param errorMessage the error message
	 */
	public ErrorDTO(String errorFieldId, String errorMessage) {
		this.errorFieldId = errorFieldId;
		this.errorMessage = errorMessage;
	}

	/**
	 * Gets the error field id.
	 *
	 * @return the error field id
	 */
	public String getErrorFieldId() {
		return errorFieldId;
	}

	/**
	 * Sets the error field id.
	 *
	 * @param errorFieldId the new error field id
	 * @return the error DTO
	 */
	public ErrorDTO setErrorFieldId(String errorFieldId) {
		this.errorFieldId = errorFieldId;
		return this;
	}

	/**
	 * Gets the error message.
	 *
	 * @return the error message
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Sets the error message.
	 *
	 * @param errorMessage the new error message
	 * @return the error DTO
	 */
	public ErrorDTO setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		return this;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((errorFieldId == null) ? 0 : errorFieldId.hashCode());
		result = prime * result + ((errorMessage == null) ? 0 : errorMessage.hashCode());
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
		ErrorDTO other = (ErrorDTO) obj;
		if (errorFieldId == null) {
			if (other.errorFieldId != null)
				return false;
		} else if (!errorFieldId.equals(other.errorFieldId))
			return false;
		if (errorMessage == null) {
			if (other.errorMessage != null)
				return false;
		} else if (!errorMessage.equals(other.errorMessage))
			return false;
		return true;
	}
	
}
