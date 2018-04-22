package com.eimt.lab.service.impl;

import com.eimt.lab.model.*;
import com.eimt.lab.model.exceptions.DuplicateEmailException;
import com.eimt.lab.persistence.DepartmentRepository;
import com.eimt.lab.persistence.EmployeeRepository;
import com.eimt.lab.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    EmployeeRepository employeeRepository;

    DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public Employee saveEmployee(FormEmployee formEmployee) {

        if(this.checkDuplicateEmail(formEmployee.getEmail())){
            throw new DuplicateEmailException();
        }

        Employee employee = new Employee();

        employee.setEmail(formEmployee.getEmail());
        employee.setPassword(formEmployee.getPassword());
        employee.setFirstName(formEmployee.getFirstName());
        employee.setLastName(formEmployee.getLastName());
        employee.setBirthDate(LocalDate.parse(formEmployee.getBirthDate().toString()));

        if(formEmployee.getGender().equals("MALE"))
            employee.setGender(Gender.MALE);
        else
            employee.setGender(Gender.FEMALE);

        employee.setActivated(false);
        employee.setActivationCode("0000");

        Optional<Department> department = departmentRepository.findById(0L);

        employee.setDepartment(department.isPresent() ? department.get() : null);
        employee.setRole(Role.EMPLOYEE);
        employee.setRegistrationDate(LocalDateTime.now());

        return employeeRepository.save(employee);
    }

    public boolean checkDuplicateEmail(String email) {
        return employeeRepository.findByEmail(email).isPresent();
    }

}
