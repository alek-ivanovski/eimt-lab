package com.eimt.lab.persistence.jpa;

import com.eimt.lab.model.Department;
import com.eimt.lab.persistence.DepartmentRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDepartmentRepository extends DepartmentRepository, JpaRepository<Department, Long> {
}
