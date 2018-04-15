package com.eimt.lab.persistence;

import com.eimt.lab.model.Employee;

public interface EmployeeRepository {

    Employee save(Employee employee);

    Employee findById(Long id);

    long count();

}
