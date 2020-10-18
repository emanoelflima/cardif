package com.cardif.funcionario.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class representing the Department entity
 * @author Emanoel de Lima
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "departamento")
public class Department {

    @Id
    @Column(name = "departamento_id")
    public Integer id;

    @Column(name = "departamento_name")
    public String name;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "funcionario_id", referencedColumnName = "funcionario_id")
    public Employee leader;
}
