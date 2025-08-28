// src/main/java/com/jobportal/entity/Application.java
package com.jobportal.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    private LocalDateTime appliedDate;
    
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;
    
    @Column(columnDefinition = "TEXT")
    private String coverLetter;
    
    // Constructors, getters, and setters
    public Application() {
        this.appliedDate = LocalDateTime.now();
        this.status = ApplicationStatus.APPLIED;
    }
    
    // Getters and setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Job getJob() { return job; }
    public void setJob(Job job) { this.job = job; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public LocalDateTime getAppliedDate() { return appliedDate; }
    public void setAppliedDate(LocalDateTime appliedDate) { this.appliedDate = appliedDate; }
    
    public ApplicationStatus getStatus() { return status; }
    public void setStatus(ApplicationStatus status) { this.status = status; }
    
    public String getCoverLetter() { return coverLetter; }
    public void setCoverLetter(String coverLetter) { this.coverLetter = coverLetter; }
}

enum ApplicationStatus {
    APPLIED, UNDER_REVIEW, INTERVIEW, ACCEPTED, REJECTED
}