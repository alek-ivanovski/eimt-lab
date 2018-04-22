package com.eimt.lab.persistence;

import com.eimt.lab.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository {

    Employee save(Employee employee);

    Optional<Employee> findById(Long id);

    Optional<Employee> findByEmail(String email);

    long count();

}
