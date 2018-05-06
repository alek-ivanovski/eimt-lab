package com.eimt.lab.persistence.jpa;

import com.eimt.lab.model.EmployeeVerificationToken;
import com.eimt.lab.persistence.VerificationTokenRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface JpaVerificationTokenRepository extends VerificationTokenRepository,
        JpaRepository<EmployeeVerificationToken, Long> {
}
