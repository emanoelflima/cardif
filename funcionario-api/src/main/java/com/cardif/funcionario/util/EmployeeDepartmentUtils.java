package com.cardif.funcionario.util;

import java.util.List;
import com.cardif.funcionario.model.EmployeeDepartment;

/**
 * Utility for {@link EmployeeDepartment} manipulation
 * @author Emanoel de Lima
 */
public class EmployeeDepartmentUtils {

    /**
     * Retrieves the active employee-department relationship from a list.
     * @param employeeDepartment list of {@link EmployeeDepartment}
     * @return {@link EmployeeDepartment}
     */
    public static EmployeeDepartment getActive(List<EmployeeDepartment> employeeDepartment) {
        return employeeDepartment.stream()
        .filter(x -> x.getEndDate() == null)
        .findAny()
        .orElse(null);
    }
}
