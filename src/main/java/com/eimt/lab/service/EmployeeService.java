package com.eimt.lab.service;

import com.eimt.lab.model.Employee;
import com.eimt.lab.model.FormEmployee;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {

    Employee saveEmployee(FormEmployee formEmployee);

}
