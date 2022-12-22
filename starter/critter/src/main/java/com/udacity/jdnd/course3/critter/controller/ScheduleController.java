package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.Schedule;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule=new Schedule();
        schedule.setDate(scheduleDTO.getDate());

        List<Long> empIds=scheduleDTO.getPetIds();
        schedule.setActivities(scheduleDTO.getActivities());
        schedule = scheduleService.save(schedule, scheduleDTO.getEmployeeIds(), scheduleDTO.getPetIds());
        return convertScheduleToScheduleDTO(schedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        List<ScheduleDTO> scheduleDTOs = null;
        if (schedules != null) {
            scheduleDTOs = schedules.stream().map(x->this.convertScheduleToScheduleDTO(x)).collect(Collectors.toList());
        }
        return scheduleDTOs;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> schedules = scheduleService.getScheduleForPet(petId);
        List<ScheduleDTO> scheduleDTOs = null;
        if (schedules != null) {
            scheduleDTOs = schedules.stream().map(x->this.convertScheduleToScheduleDTO(x)).collect(Collectors.toList());
        }
        return scheduleDTOs;    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> schedules = scheduleService.getScheduleForEmployee(employeeId);
        List<ScheduleDTO> scheduleDTOs = null;
        if (schedules != null) {
            scheduleDTOs = schedules.stream().map(x->this.convertScheduleToScheduleDTO(x)).collect(Collectors.toList());
        }
        return scheduleDTOs;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> schedules = scheduleService.getScheduleForCustomer(customerId);
        List<ScheduleDTO> scheduleDTOs = null;
        if (schedules != null) {
            scheduleDTOs = schedules.stream().map(x->this.convertScheduleToScheduleDTO(x)).collect(Collectors.toList());
        }
        return scheduleDTOs;     }

    private ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule){
//        ScheduleDTO scheduleDTO = new ScheduleDTO();
//        BeanUtils.copyProperties(schedule, scheduleDTO);
//        return scheduleDTO;

        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(schedule.getId());
        scheduleDTO.setEmployeeIds(schedule.getEmployees().stream().map(x -> x.getId()).collect(Collectors.toList()));
        scheduleDTO.setPetIds(schedule.getPets().stream().map(x -> x.getId()).collect(Collectors.toList()));
        scheduleDTO.setDate(schedule.getDate());
        scheduleDTO.setActivities(schedule.getActivities());
        return scheduleDTO;
    }
}
