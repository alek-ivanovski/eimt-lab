package com.eimt.lab.service.impl;

import com.eimt.lab.model.Department;
import com.eimt.lab.model.Employee;
import com.eimt.lab.persistence.EmployeeRepository;
import com.eimt.lab.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) {
        employee.setActivated(false);
        employee.setActivationCode("0000");
        employee.setDepartment(new Department());
        employee.setRegistrationDate(LocalDateTime.now());
        return employeeRepository.save(employee);
    }

}
