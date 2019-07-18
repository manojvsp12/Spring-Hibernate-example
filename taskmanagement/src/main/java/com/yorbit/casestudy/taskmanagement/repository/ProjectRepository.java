package com.yorbit.casestudy.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yorbit.casestudy.taskmanagement.entity.Project;

/**
 * The Interface ProjectRepository.
 *
 * @author Manoj SP
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {

}
