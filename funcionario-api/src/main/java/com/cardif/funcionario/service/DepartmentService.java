package com.cardif.funcionario.service;

import java.util.Optional;
import com.cardif.funcionario.common.Constants;
import com.cardif.funcionario.model.Department;
import com.cardif.funcionario.model.Employee;
import com.cardif.funcionario.model.EmployeeDepartment;
import com.cardif.funcionario.repository.DepartmentRepository;
import com.cardif.funcionario.repository.EmployeeRepository;
import com.cardif.funcionario.util.EmployeeDepartmentUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Services for {@link Department} operations.
 * @author Emanoel de Lima
 */
@Service
public class DepartmentService {
    
    @Autowired
    private DepartmentRepository _departmentRepo;

    @Autowired
    private EmployeeRepository _employeeRepo;

    /**
     * Updates a department leader by their ids.
     * @param departmentId
     * @param employeeId
     * @return updated {@link Department}, if given employee and department ids are valid.
     * @throws Exception
     */
    public Department updateLeader(Integer departmentId, Integer employeeId) throws Exception {
        Optional<Department> departmentData = _departmentRepo.findById(departmentId);
        Employee employee = _employeeRepo.findByIsActiveTrueAndId(employeeId);
        
        if(departmentData.isEmpty()) {
            throw new Exception(Constants.MSG_ERROR_INVALID_DEPARTMENT);
        }
        if(employee == null) { 
            throw new Exception(Constants.MSG_ERROR_INVALID_EMPLOYEE);
        }

        EmployeeDepartment activeDepartment = EmployeeDepartmentUtils.getActive(employee.getDepartments());
        Integer activeDepartmentId = activeDepartment.getPk().getDepartmentId();

        if(!activeDepartmentId.equals(departmentId)) {
            throw new Exception(Constants.MSG_ERROR_INVALID_LEADER);
        }
        
        Department department = departmentData.get();
        
        department.setLeader(employee);
        _departmentRepo.save(department);
        
        return _departmentRepo.save(department);
    }

}
