package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer,Long> {

    @Query("select c from Customer c left join c.pets")
    List<Customer> findAllCustomer();
}
