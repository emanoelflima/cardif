package com.cardif.funcionario.repository;

import java.util.List;
import com.cardif.funcionario.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for {@link Employee} entity
 * @author Emanoel de Lima
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    /**
     * Retrieves a list of active employees by department id
     * @param departmentId
     * @return list of {@link Employee}
     */
    public List<Employee> findByIsActiveTrueAndDepartments_Pk_DepartmentId(Integer departmentId);

    /**
     * Retrieve active employee by its id
     * @param id
     * @return {@link Employee}
     */
    public Employee findByIsActiveTrueAndId(Integer id);

    /**
     * Retrieves an employee by given document.
     * @param document
     * @return {@link Employee}
     */
    public Employee findByDocument(String document);
}
