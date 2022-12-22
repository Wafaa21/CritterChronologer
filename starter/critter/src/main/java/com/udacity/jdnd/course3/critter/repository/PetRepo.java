package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetRepo extends JpaRepository<Pet,Long> {

    List<Pet> findAllByCustomerId(Long customerId);

    @Query("select p from Pet p left join p.customer")
    List<Customer> findAllPets();
}
