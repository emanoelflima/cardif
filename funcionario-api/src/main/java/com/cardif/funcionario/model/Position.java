package com.cardif.funcionario.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class representing the Position entity
 * @author Emanoel de Lima
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cargo")
public class Position {

    @Id
    @Column(name = "cargo_id")
    private Integer id;

    @Column(name = "cargo_name")
    private String name;
}
