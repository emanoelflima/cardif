package com.cardif.funcionario.model.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class representing the primary key of {@link Department} entity
 * @author Emanoel de Lima
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class EmployeeDepartmentPk implements Serializable {
    
    private static final long serialVersionUID = 3960085943764757318L;

    @Column(name = "funcionario_id")
    private Integer employeeId;

    @Column(name = "departamento_id")
    private Integer departmentId;

}
