package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.EmployeeSkill;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;

    public Optional<Employee> findById(Long id){
        return employeeRepo.findById(id);
    }

    public Employee save(Employee employee) {
        return employeeRepo.save(employee);
    }

    public List<Employee> findEmployeeForService(LocalDate date, Set<EmployeeSkill> skills) {

        List<Employee> employees = employeeRepo.findAllByDaysAvailable(date.getDayOfWeek());
        List<Employee> empAva=new ArrayList<>();
        for (Employee employee :employees) {
            if(employee.getSkills().containsAll(skills)){
                empAva.add(employee);
            }
        }
        return empAva;
    }

    public void setEmployeeAvaliable(Set<DayOfWeek> daysAvailable, long employeeId) {
        Optional<Employee> empOpt=employeeRepo.findById(employeeId);
        Employee employee;
        if(empOpt.isPresent()){
            employee=empOpt.get();
            employee.setDaysAvailable(daysAvailable);
            employeeRepo.save(employee);
        }

    }


    }
