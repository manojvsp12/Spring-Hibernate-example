package com.yorbit.casestudy.taskmanagement.generator;

import static com.yorbit.casestudy.taskmanagement.constant.TaskManagementConstants.TASK_COUNT;
import static com.yorbit.casestudy.taskmanagement.constant.TaskManagementConstants.TASK_EXISTS;
import static com.yorbit.casestudy.taskmanagement.constant.TaskManagementConstants.TASK_ID_PREFIX;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;;

/**
 * The Class TaskIdGenerator.
 *
 * @author Manoj SP
 */
@Component
public class TaskIdGenerator implements IdentifierGenerator {

	private static final Logger logger = LoggerFactory.getLogger(TaskIdGenerator.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.id.IdentifierGenerator#generate(org.hibernate.engine.spi.
	 * SharedSessionContractImplementor, java.lang.Object)
	 */
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		logger.debug("generating taskId");
		long id = 1l;
		try (ResultSet resultSet = session.connection().createStatement().executeQuery(TASK_COUNT)) {

			if (resultSet.next()) {
				id = id + resultSet.getLong(1);
			}

			String taskId = TASK_ID_PREFIX + id;
			while (this.existsById(session, taskId)) {
				taskId = TASK_ID_PREFIX + ++id;
			}
			return taskId;
		} catch (SQLException e) {
			logger.error("SQLException", e);
			throw new HibernateException(e);
		}
	}

	/**
	 * Exists by id.
	 *
	 * @param session
	 *            the session
	 * @param taskId
	 *            the task id
	 * @return true, if successful
	 */
	private boolean existsById(SharedSessionContractImplementor session, String taskId) {
		logger.debug("Checking whether task id exists");
		Connection connection = session.connection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(TASK_EXISTS)) {
			preparedStatement.setString(1, taskId);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.next() && resultSet.getInt(1) == 1;
			}
		} catch (SQLException e) {
			logger.error("SQLException", e);
			throw new HibernateException(e);
		}
	}

}
