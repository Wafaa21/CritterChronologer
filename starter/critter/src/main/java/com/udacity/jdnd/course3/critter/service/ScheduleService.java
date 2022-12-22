package com.udacity.jdnd.course3.critter.service;


import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.model.Schedule;
import com.udacity.jdnd.course3.critter.repository.CustomerRepo;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepo;
import com.udacity.jdnd.course3.critter.repository.PetRepo;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepo scheduleRepo;
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private PetRepo petRepo;
    @Autowired
    private CustomerRepo customerRepo;

    public Schedule save(Schedule schedule, List<Long> employeeIds, List<Long> petIds){
       List <Employee> employees= employeeRepo.findAllById(employeeIds);
        List<Pet> pets=petRepo.findAllById(petIds);
        schedule.setEmployees(employees);
        schedule.setPets(pets);
        return scheduleRepo.save(schedule);
    }
    public List<Schedule> getAllSchedules(){
        return scheduleRepo.findAll();
    }

    public List<Schedule> getScheduleForPet(Long petId){
        Optional<Pet> petOpt=petRepo.findById(petId);
        if(petOpt.isPresent()){
            Pet pet=petOpt.get();
            return scheduleRepo.findAllByPets(pet);
        }
        return null;
    }

    public List<Schedule> getScheduleForEmployee(Long employeeId){
        Optional<Employee> empOpt=employeeRepo.findById(employeeId);
        if(empOpt.isPresent()){
            Employee employee=empOpt.get();
            return scheduleRepo.findAllByEmployees(employee);
        }
        else{return null;}
    }

    public List<Schedule> getScheduleForCustomer(long customerId) {
        Optional<Customer> customerOptional = customerRepo.findById(customerId);
        if(customerOptional.isPresent()){
            Customer customer = customerOptional.get();
            return scheduleRepo.findAllByPetsIn(customer.getPets());
        }
        else {
        return  null;}
    }
}
