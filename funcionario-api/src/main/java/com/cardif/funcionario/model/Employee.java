package com.cardif.funcionario.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class representing the Employee entity
 * @author Emanoel de Lima
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "funcionario")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funcionario_id")
    private Integer id;

    @Column(name = "funcionario_name")
    private String name;

    @Column(name = "funcionario_birthday")
    private LocalDate birthDate;

    @Column(name = "funcionario_document")
    private String document;

    @OneToOne
    @JoinColumn(name = "cargo_id", referencedColumnName = "cargo_id")
    private Position position;

    @OneToMany
    @JoinColumn(name = "funcionario_id", referencedColumnName = "funcionario_id", insertable = false, updatable = false)
    private List<EmployeeDepartment> departments;

    @Column(name = "cd_ativo")
    private boolean isActive;

    /**
     * Class constructor
     * @param id
     */
    public Employee(Integer id) {
        this.id = id;
    }

    /**
     * Returns the current age of an employee based on its birthdate data and current localdate.
     * @return
     */
    public long getAge() {
        if(this.birthDate != null) {
            return this.birthDate.until(LocalDate.now(), ChronoUnit.YEARS);
        }
        return 0;
    }
}
