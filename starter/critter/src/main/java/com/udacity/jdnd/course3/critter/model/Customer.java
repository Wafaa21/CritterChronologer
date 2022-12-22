package com.udacity.jdnd.course3.critter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Nationalized
    @Column(length=20)
    private String name;

    @Column(length=15)
    private String phoneNumber;

    @Column(length=500)
    private String notes;

    @OneToMany(mappedBy = "customer",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Pet> pets;



}
