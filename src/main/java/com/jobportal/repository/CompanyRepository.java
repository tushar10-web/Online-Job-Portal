// src/main/java/com/jobportal/repository/CompanyRepository.java
package com.jobportal.repository;

import com.jobportal.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    long count();
}