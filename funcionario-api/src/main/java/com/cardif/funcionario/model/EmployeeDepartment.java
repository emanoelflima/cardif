package com.cardif.funcionario.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import com.cardif.funcionario.model.pk.EmployeeDepartmentPk;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class representing the Employee-Department relationship entity
 * @author Emanoel de Lima
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "funcionario_departamento")
public class EmployeeDepartment {

    @EmbeddedId
    private EmployeeDepartmentPk pk;

    @Column(name = "data_admissao")
    private Date startDate;

    @Column(name = "data_saida")
    private Date endDate;

    /**
     * Class constructor
     * @param employeeId
     * @param departmentId
     */
    public EmployeeDepartment(Integer employeeId, Integer departmentId) {
        this.pk = new EmployeeDepartmentPk(employeeId, departmentId);
    }

    /**
     * Class constructor
     * @param employeeId
     * @param departmentId
     * @param startDate
     */
    public EmployeeDepartment(Integer employeeId, Integer departmentId, Date startDate) {
        this.pk = new EmployeeDepartmentPk(employeeId, departmentId);
        this.startDate = startDate;
    }
}
