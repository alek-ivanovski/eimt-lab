package com.eimt.lab.persistence.jpa;

import com.eimt.lab.model.Employee;
import com.eimt.lab.persistence.EmployeeRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaEmployeeRepository extends EmployeeRepository, JpaRepository<Employee, Long> {
}
