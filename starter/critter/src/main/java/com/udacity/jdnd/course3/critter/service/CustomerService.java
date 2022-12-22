package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepo;
import com.udacity.jdnd.course3.critter.repository.PetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private PetRepo petRepo;

    public List<Customer> findAllCustomer(){
        return customerRepo.findAllCustomer();
    }

    public Customer saveWithPets(Customer customer, List<Long> petIds){
        List<Pet> pets = new ArrayList<>();
        if (petIds != null) {
        pets=petRepo.findAllById(petIds);}
        customer.setPets(pets);
        return customerRepo.save(customer);
    }

    public Customer findByPetId(Long petId){
       Optional<Pet> pet= petRepo.findById(petId);
       if(pet.isPresent()){
           return pet.get().getCustomer();
       }
       return null;
    }

}
