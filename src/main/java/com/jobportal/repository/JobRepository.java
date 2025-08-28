// src/main/java/com/jobportal/repository/JobRepository.java
package com.jobportal.repository;

import com.jobportal.entity.Job;
import com.jobportal.entity.JobType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByFeaturedTrue();
    
    @Query("SELECT j FROM Job j WHERE " +
           "(:search IS NULL OR LOWER(j.title) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(j.company) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(j.skills) LIKE LOWER(CONCAT('%', :search, '%'))) AND " +
           "(:location IS NULL OR LOWER(j.location) LIKE LOWER(CONCAT('%', :location, '%'))) AND " +
           "(:jobType IS NULL OR j.type = :jobType)")
    Page<Job> findJobsWithFilters(@Param("search") String search,
                                  @Param("location") String location,
                                  @Param("jobType") JobType jobType,
                                  Pageable pageable);
    
    long count();
}