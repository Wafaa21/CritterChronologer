package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepo extends JpaRepository<Schedule,Long> {

    List<Schedule> findAllByPets(Pet pet);
    List<Schedule> findAllByEmployees(Employee employee);

   List<Schedule> findAllByPetsIn(List<Pet> pets);

}
