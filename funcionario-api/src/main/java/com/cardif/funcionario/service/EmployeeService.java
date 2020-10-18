package com.cardif.funcionario.service;

import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import com.cardif.funcionario.common.Constants;
import com.cardif.funcionario.model.Employee;
import com.cardif.funcionario.model.EmployeeDepartment;
import com.cardif.funcionario.repository.EmployeeDepartmentRepository;
import com.cardif.funcionario.repository.EmployeeRepository;
import com.cardif.funcionario.util.EmployeeDepartmentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Services for {@link Employee} operations.
 * @author Emanoel de Lima
 */
@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository _employeeRepo;

    @Autowired
    private EmployeeDepartmentRepository _employeeDepartmentRepo;
    
    /**
     * Creates a new employee.
     * @param employee {@link employee}
     * @return created {@link Employee}
     * @throws Exception
     */
    @Transactional
    public Employee create(Employee employee) throws Exception {
        if(employee.getId() == null && employee.getDocument() != null && !employee.getDocument().isEmpty()) {
            
            Employee existingEmployee = _employeeRepo.findByDocument(employee.getDocument());
            
            if(existingEmployee != null) {
                throw new Exception(Constants.MSG_ERROR_EXISTING_DOCUMENT);
            }
            
            EmployeeDepartment employeeDepartment = EmployeeDepartmentUtils.getActive(employee.getDepartments());
            employee.setActive(true);
            employee = _employeeRepo.save(employee);
            employeeDepartment.getPk().setEmployeeId(employee.getId());
            _employeeDepartmentRepo.save(employeeDepartment);
            return employee;
        }
        throw new Exception(Constants.MSG_ERROR_NEW_EMPLOYEE_WITH_ID);
    }

    /**
     * Updates an existing and active employee data by given id.
     * @param employeeId
     * @param employee {@link Employee}
     * @return updated {@link Employee}
     */
    @Transactional
    public Employee update(Integer employeeId, Employee employee) {
        Employee currentEmployee = _employeeRepo.findByIsActiveTrueAndId(employeeId);
        if(currentEmployee != null) {
            EmployeeDepartment currentDepartment = EmployeeDepartmentUtils.getActive(currentEmployee.getDepartments());
            EmployeeDepartment newDepartment = EmployeeDepartmentUtils.getActive(employee.getDepartments());
            employee.setDepartments(currentEmployee.getDepartments());

            if(!currentDepartment.getPk().getDepartmentId().equals(newDepartment.getPk().getDepartmentId())) {
                newDepartment.setStartDate(new Date());
                newDepartment.getPk().setEmployeeId(employeeId);
                currentDepartment.setEndDate(newDepartment.getStartDate());
                _employeeDepartmentRepo.save(newDepartment);
                _employeeDepartmentRepo.save(currentDepartment);
                employee.getDepartments().add(newDepartment);
            }
            return _employeeRepo.save(employee);
        }
        return null;
    }

    /**
     * Deactivates an active employee by its id.
     * @param employeeId
     */
    @Transactional
    public void delete(Integer employeeId) {
        Employee employee = _employeeRepo.findByIsActiveTrueAndId(employeeId);
        if(employee != null) {
            employee.setActive(false);
            _employeeRepo.save(employee);
        }
    }

    /**
     * Retrieves an active employee by its id.
     * @param employeeId
     * @return {@link Employee}
     */
    public Employee getById(Integer employeeId) {
        return _employeeRepo.findByIsActiveTrueAndId(employeeId);
    }

    /**
     * Retrieves a list of active employees by given department id.
     * @param departmentId
     * @return list of {@link Employee}
     */
    public List<Employee> listByDepartmentId(Integer departmentId) {
        return _employeeRepo.findByIsActiveTrueAndDepartments_Pk_DepartmentId(departmentId);
    }

}
