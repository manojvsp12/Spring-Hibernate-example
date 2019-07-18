package com.yorbit.casestudy.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yorbit.casestudy.taskmanagement.entity.Task;

/**
 * The Interface TaskRepository.
 *
 * @author Manoj SP
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, String> {

}
