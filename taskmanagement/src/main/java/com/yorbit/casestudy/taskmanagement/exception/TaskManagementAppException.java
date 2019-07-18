package com.yorbit.casestudy.taskmanagement.exception;

/**
 * The Class TaskManagementAppException.
 *
 * @author Manoj SP
 */
public class TaskManagementAppException extends Exception {

	private static final long serialVersionUID = -8417792648408724024L;

	/**
	 * Instantiates a new task management app exception.
	 */
	public TaskManagementAppException() {
		super();
	}

	/**
	 * Instantiates a new task management app exception.
	 *
	 * @param errorMessage the error message
	 */
	public TaskManagementAppException(String errorMessage) {
		super(errorMessage);
	}

	/**
	 * Instantiates a new task management app exception.
	 *
	 * @param errorMessage the error message
	 * @param cause the cause
	 */
	public TaskManagementAppException(String errorMessage, Throwable cause) {
		super(errorMessage, cause);
	}
}
