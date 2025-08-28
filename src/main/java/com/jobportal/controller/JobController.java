// src/main/java/com/jobportal/controller/JobController.java
package com.jobportal.controller;

import com.jobportal.entity.Job;
import com.jobportal.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "*")
public class JobController {
    
    @Autowired
    private JobService jobService;
    
    @GetMapping
    public ResponseEntity<?> getAllJobs(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String jobTypes,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        
        Page<Job> jobs = jobService.findJobsWithFilters(search, location, jobTypes, page - 1, limit);
        
        return ResponseEntity.ok(new JobPageResponse(jobs.getContent(), jobs.getTotalElements()));
    }
    
    @GetMapping("/featured")
    public ResponseEntity<List<Job>> getFeaturedJobs() {
        List<Job> featuredJobs = jobService.findFeaturedJobs();
        return ResponseEntity.ok(featuredJobs);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('EMPLOYER')")
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        Job savedJob = jobService.save(job);
        return ResponseEntity.ok(savedJob);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Job job = jobService.findById(id);
        return ResponseEntity.ok(job);
    }
}

class JobPageResponse {
    private List<Job> jobs;
    private long total;
    
    public JobPageResponse(List<Job> jobs, long total) {
        this.jobs = jobs;
        this.total = total;
    }
    
    // Getters and setters
    public List<Job> getJobs() { return jobs; }
    public void setJobs(List<Job> jobs) { this.jobs = jobs; }
    
    public long getTotal() { return total; }
    public void setTotal(long total) { this.total = total; }
}