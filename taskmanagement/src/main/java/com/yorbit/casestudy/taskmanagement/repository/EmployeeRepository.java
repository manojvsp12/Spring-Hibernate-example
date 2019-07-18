package com.yorbit.casestudy.taskmanagement.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yorbit.casestudy.taskmanagement.entity.Employee;

/**
 * The Interface EmployeeRepository.
 *
 * @author Manoj SP
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

	@Query("select e from Employee e where project_id = :projectId")
	Set<Employee> findByProjectId(@Param("projectId") String projectId);
}
