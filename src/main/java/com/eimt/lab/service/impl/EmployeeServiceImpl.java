package com.eimt.lab.service.impl;

import com.eimt.lab.model.*;
import com.eimt.lab.model.exceptions.DuplicateEmailException;
import com.eimt.lab.persistence.DepartmentRepository;
import com.eimt.lab.persistence.EmployeeRepository;
import com.eimt.lab.persistence.VerificationTokenRepository;
import com.eimt.lab.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    public static final String INVALID_TOKEN = "invalidToken";
    public static final String EXPIRED_TOKEN = "expired";
    public static final String VALID_TOKEN = "valid";


    EmployeeRepository employeeRepository;

    DepartmentRepository departmentRepository;

    VerificationTokenRepository verificationTokenRepository;

    Random rand = new Random();

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               DepartmentRepository departmentRepository,
                               VerificationTokenRepository verificationTokenRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.verificationTokenRepository = verificationTokenRepository;
    }

    public Employee saveEmployee(FormEmployee formEmployee) {

        if (this.checkDuplicateEmail(formEmployee.getEmail())) {
            throw new DuplicateEmailException();
        }

        Employee employee = new Employee();

        employee.setEmail(formEmployee.getEmail());
        employee.setPassword(formEmployee.getPassword());
        employee.setFirstName(formEmployee.getFirstName());
        employee.setLastName(formEmployee.getLastName());
        employee.setBirthDate(LocalDate.parse(formEmployee.getBirthDate().toString()));

        if (formEmployee.getGender().equals("MALE"))
            employee.setGender(Gender.MALE);
        else
            employee.setGender(Gender.FEMALE);

        employee.setActivated(false);
        employee.setActivationCode(this.generateActivationCode());

        Optional<Department> department = departmentRepository.findById(0L);

        employee.setDepartment(department.isPresent() ? department.get() : null);
        employee.setRole(Role.EMPLOYEE);
        employee.setRegistrationDate(LocalDateTime.now());

        return employeeRepository.save(employee);
    }

    @Override
    public void createVerificationToken(Employee employee, String randomToken) {
        EmployeeVerificationToken token = new EmployeeVerificationToken();
        token.setToken(randomToken);
        token.setEmployee(employee);
        verificationTokenRepository.save(token);
    }

    @Override
    public EmployeeVerificationToken generateNewVerificationToken(String verificationToken) {
        EmployeeVerificationToken vToken = verificationTokenRepository.findByToken(verificationToken);
        vToken.setToken(UUID.randomUUID().toString());
        vToken = verificationTokenRepository.save(vToken);
        return vToken;
    }

    @Override
    public String validateVerificationToken(String token) {

        EmployeeVerificationToken employeeToken = verificationTokenRepository.findByToken(token);
        if (employeeToken == null) {
            return INVALID_TOKEN;
        }

        Employee employee = employeeToken.getEmployee();
        Calendar calendar = Calendar.getInstance();
        if(employeeToken.getExpiryDate().getTime() - calendar.getTime().getTime() <= 0){
            verificationTokenRepository.delete(employeeToken);
            return EXPIRED_TOKEN;
        }

        employee.setActivated(true);
        employeeRepository.save(employee);
        return VALID_TOKEN;

    }

    public String generateActivationCode() {
        Integer activationCode = rand.nextInt();
        return activationCode.toString();
    }

    public boolean checkDuplicateEmail(String email) {
        return employeeRepository.findByEmail(email).isPresent();
    }

}

