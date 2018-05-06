package com.eimt.lab.persistence;

import com.eimt.lab.model.Employee;
import com.eimt.lab.model.EmployeeVerificationToken;

import java.util.Date;

public interface VerificationTokenRepository {

    EmployeeVerificationToken save(EmployeeVerificationToken token);

    EmployeeVerificationToken findByToken(String token);

    EmployeeVerificationToken findByEmployee(String employee);

    void deleteByExpiryDateLessThan(Date date);

    void delete(EmployeeVerificationToken token);

}
