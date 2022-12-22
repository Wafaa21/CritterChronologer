package com.udacity.jdnd.course3.critter.model;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private LocalDate date;


    @ManyToMany
    @LazyCollection(LazyCollectionOption.TRUE)
    private List<Employee> employees;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.TRUE)
    private List<Pet> pets;


    @ElementCollection
    private Set<EmployeeSkill> activities;

}
