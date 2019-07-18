package com.yorbit.casestudy.taskmanagement.constant;

/**
 * The Class TaskManagementConstants.
 *
 * @author Manoj SP
 */
public class TaskManagementConstants {
	
	/** The Constant DATE_FORMAT. */
	public static final String DATE_FORMAT = "yorbit.casestudy.taskmanagement.date-format";
	
	public static final String ERRORMESSAGES = "yorbit.casestudy.taskmanagement.error-msg-file-name";
	
	public static final String FIELDS = "yorbit.casestudy.taskmanagement.field-details-file-name";

	public static final String DEFAULT_LANG = "yorbit.casestudy.taskmanagement.default-lang";
	
	/** The Constant SCRIPT_FILE_PROPERTY. */
	public static final String SCRIPT_FILE_PROPERTY = "spring.datasource.data";
	
	/** The Constant TASK_EXISTS. */
	public static final String TASK_EXISTS = "select count(task_id) from Task where task_id = ?";
	
	/** The Constant TASK_ID_PREFIX. */
	public static final String TASK_ID_PREFIX = "task";
	
	/** The Constant TASK_COUNT. */
	public static final String TASK_COUNT = "select count(task_id) from Task";
	
	/** The Constant FIELD_DESC. */
	public static final String FIELD_DESC = "desc";
	
	/** The Constant FIELD_START_DATE. */
	public static final String FIELD_START_DATE = "startDate";
	
	/** The Constant FIELD_END_DATE. */
	public static final String FIELD_END_DATE = "endDate";
	
	/** The Constant FIELD_EMPLOYEES. */
	public static final String FIELD_EMPLOYEES = "employees";
	
	/** The Constant FIELD_PROJECTS. */
	public static final String FIELD_PROJECTS = "projects";
	
	/** The Constant FIELD_DESC_ERROR_MSG. */
	public static final String FIELD_DESC_ERROR_MSG = "YOR-TM-01";
	
	/** The Constant FIELD_START_DT_ERROR_MSG. */
	public static final String FIELD_START_DT_ERROR_MSG = "YOR-TM-02";
	
	/** The Constant FUTURE_START_DT_ERROR_MSG. */
	public static final String FUTURE_START_DT_ERROR_MSG = "YOR-TM-03";
	
	/** The Constant FIELD_END_DT_ERROR_MSG. */
	public static final String FIELD_END_DT_ERROR_MSG = "YOR-TM-04";
	
	/** The Constant FUTURE_END_DT_ERROR_MSG. */
	public static final String FUTURE_END_DT_ERROR_MSG = "YOR-TM-05";
	
	/** The Constant START_DATE_GT_END_DT_ERROR_MSG. */
	public static final String START_DATE_GT_END_DT_ERROR_MSG = "YOR-TM-06";
	
	/** The Constant EMPTY_EMP_ERROR_MSG. */
	public static final String EMPTY_EMP_ERROR_MSG = "YOR-TM-07";
	
	/** The Constant FIELD_PROJECT_ERROR_MSG. */
	public static final String FIELD_PROJECT_ERROR_MSG = "YOR-TM-08";

	/** The Constant UNKNOWN_ERROR_OCCURED. */
	public static final String UNKNOWN_ERROR_OCCURED = "YOR-TM-09";
	
	public static final String FAILED_DB_OPERATIONS = "YOR-TM-10";
}
