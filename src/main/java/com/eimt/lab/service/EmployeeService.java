package com.eimt.lab.service;

import com.eimt.lab.model.Employee;
import com.eimt.lab.model.EmployeeVerificationToken;
import com.eimt.lab.model.FormEmployee;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {

    Employee saveEmployee(FormEmployee formEmployee);

    String generateActivationCode();

    void createVerificationToken(Employee employee, String randomToken);

    EmployeeVerificationToken generateNewVerificationToken(String verificationToken);

    String validateVerificationToken(String token);

    boolean checkDuplicateEmail(String email);
}
