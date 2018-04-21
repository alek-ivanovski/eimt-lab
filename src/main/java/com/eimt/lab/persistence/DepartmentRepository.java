package com.eimt.lab.persistence;

import com.eimt.lab.model.Department;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository {

    Optional<Department> findById(Long id);

}
