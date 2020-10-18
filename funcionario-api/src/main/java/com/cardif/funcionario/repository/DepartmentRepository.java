package com.cardif.funcionario.repository;

import com.cardif.funcionario.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for {@link Department} entity
 * @author Emanoel de Lima
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
