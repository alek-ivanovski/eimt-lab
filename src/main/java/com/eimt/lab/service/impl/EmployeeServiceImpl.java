package com.eimt.lab.service.impl;

import com.eimt.lab.model.*;
import com.eimt.lab.persistence.EmployeeRepository;
import com.eimt.lab.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee saveEmployee(FormEmployee formEmployee) {

        Employee employee = new Employee();

        employee.setEmail(formEmployee.getEmail());
        employee.setPassword(formEmployee.getPassword());
        employee.setFirstName(formEmployee.getFirstName());
        employee.setLastName(formEmployee.getLastName());
        employee.setBirthDate(formEmployee.getBirthDate());

        if(formEmployee.getGender().equals("MALE"))
            employee.setGender(Gender.MALE);
        else
            employee.setGender(Gender.FEMALE);

        employee.setActivated(false);
        employee.setActivationCode("0000");

        Department department = new Department("Digitalization and Innovation");

        employee.setDepartment(department);
        employee.setRole(Role.EMPLOYEE);
        employee.setRegistrationDate(LocalDateTime.now());

        return employeeRepository.save(employee);
    }

}
