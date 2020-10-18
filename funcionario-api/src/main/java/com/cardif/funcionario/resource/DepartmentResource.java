package com.cardif.funcionario.resource;

import com.cardif.funcionario.common.Constants;
import com.cardif.funcionario.model.Department;
import com.cardif.funcionario.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * Controller for {@link Department} operations
 * @author Emanoel de Lima
 */
@RestController
@RequestMapping("department/v1/")
public class DepartmentResource {

    @Autowired
    private DepartmentService departmentService;

    /**
     * Wellcome method
     * @return
     */
    @GetMapping("")
    public String hello() {
        return String.format(Constants.MSG_WELCOME, Constants.NAME_DEPARTMENT);
    }

    /**
     * Updates a department leader
     * @param departmentId
     * @param employeeId
     * @return modified {@link Department}
     * @throws Exception
     */
    @PutMapping(value="{departmentId}/leader/{employeeId}")
    public Department updateLeader(@PathVariable int departmentId, @PathVariable int employeeId) {
        try {
            return departmentService.updateLeader(departmentId, employeeId);
        }
        catch(Exception ex) { 
            throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage(), ex);
        }
    }
}