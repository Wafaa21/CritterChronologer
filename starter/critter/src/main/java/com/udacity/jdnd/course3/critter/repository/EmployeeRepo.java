package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee,Long> {

    List<Employee> findAllByDaysAvailable(DayOfWeek dayOfWeek);
}
