// src/main/java/com/jobportal/repository/ApplicationRepository.java
package com.jobportal.repository;

import com.jobportal.entity.Application;
import com.jobportal.entity.User;
import com.jobportal.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByUserOrderByAppliedDateDesc(User user);
    Optional<Application> findByUserAndJob(User user, Job job);
    long count();
}