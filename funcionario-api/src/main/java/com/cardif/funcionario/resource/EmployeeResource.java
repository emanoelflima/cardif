package com.cardif.funcionario.resource;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import com.cardif.funcionario.common.Constants;
import com.cardif.funcionario.model.Employee;
import com.cardif.funcionario.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Controller for {@link Employee} operations.
 * @author Emanoel de Lima
 */
@RestController
@RequestMapping("employee/v1/")
public class EmployeeResource {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Wellcome method
     * @return
     */
    @GetMapping("")
    public String hello() {
        return String.format(Constants.MSG_WELCOME, Constants.NAME_EMPLOYEE);
    }
    
    /**
     * Retrieves an active employee by its id
     * @param employeeId
     * @return {@link Employee}, if found.
     */
    @GetMapping("{employeeId}")
    public Employee getById(@PathVariable Integer employeeId) {
        return employeeService.getById(employeeId);
    }

    /**
     * Retrieves a list of active employees by given department id
     * @param departmentId
     * @return list of active {@link Employees}
     */
    @GetMapping("listByDepartment/{departmentId}")
    public List<Employee> listByDepartment(@PathVariable Integer departmentId) {
        return employeeService.listByDepartmentId(departmentId);
    }

    /**
     * Saves a new employee
     * @param employee {@link Employee}
     * @return saved {@link Employee}
     * @throws Exception
     */
    @PostMapping
    public Employee create(@RequestBody Employee employee, HttpServletResponse response) {
        try {
            return employeeService.create(employee);
        }
        catch(Exception ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage(), ex);
        }
    }

    /**
     * Updates data of given employee.
     * @param employeeId
     * @param employee {@link Employee}
     * @return updated {@link Employee}
     */
    @PutMapping(value="{employeeId}")
    public Employee update(@PathVariable Integer employeeId, @RequestBody Employee employee) {
        return employeeService.update(employeeId, employee);
    }

    /**
     * Deactivates an employee
     * @param employeeId
     */
    @DeleteMapping(value = "{employeeId}")
    public void delete(@PathVariable Integer employeeId) {
        employeeService.delete(employeeId);
    }

}