package com.cardif.funcionario.repository;

import com.cardif.funcionario.model.EmployeeDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for {@link EmployeeDepartment} relashionship entity
 * @author Emanoel de Lima 
 */
@Repository
public interface EmployeeDepartmentRepository extends JpaRepository<EmployeeDepartment, Integer> {

    /**
     * Retrieve the active employee-department relationship of given employee id
     * @param employeeId
     * @return {@link EmployeeDepartment}
     */
    @Query(value = "SELECT ed FROM EmployeeDepartment ed WHERE ed.employeeId = ?1 AND ed.endDate IS NULL", nativeQuery = true)
    public EmployeeDepartment findActiveDepartment(Integer employeeId);

}
