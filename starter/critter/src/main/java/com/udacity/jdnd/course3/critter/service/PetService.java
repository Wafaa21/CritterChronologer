package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepo;
import com.udacity.jdnd.course3.critter.repository.PetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetService {

    @Autowired
    private PetRepo petRepo;

    @Autowired
    private CustomerRepo customerRepo;

    public Optional<Pet> findById(Long petId) {
        return petRepo.findById(petId);
    }

    public List<Pet> findAll() {
        return petRepo.findAll();
    }

    public List<Pet> findPetsByCustomerId(long customerId) {
        return petRepo.findAllByCustomerId(customerId);
    }

    public Pet savePetWithCustomer(Pet pet, Long ownerId) {
        Optional<Customer> customerOp = customerRepo.findById(ownerId);
        if (customerOp.isPresent()) {
            Customer customer = customerOp.get();
            pet.setCustomer(customer);
            pet = petRepo.save(pet);
            customer.getPets().add(pet);
            customerRepo.save(customer);

        }
        return pet;

    }
}
