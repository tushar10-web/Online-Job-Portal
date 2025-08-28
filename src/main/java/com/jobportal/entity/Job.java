// src/main/java/com/jobportal/entity/Job.java
package com.jobportal.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String company;
    
    @Column(nullable = false)
    private String location;
    
    @Enumerated(EnumType.STRING)
    private JobType type;
    
    private String salary;
    
    @Enumerated(EnumType.STRING)
    private ExperienceLevel experience;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    private String skills;
    private LocalDate deadline;
    private LocalDateTime postedDate;
    private boolean featured;
    
    @ManyToOne
    @JoinColumn(name = "posted_by")
    private User postedBy;
    
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private List<Application> applications;
    
    // Constructors, getters, and setters
    public Job() {
        this.postedDate = LocalDateTime.now();
        this.featured = false;
    }
    
    // Getters and setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public JobType getType() { return type; }
    public void setType(JobType type) { this.type = type; }
    
    public String getSalary() { return salary; }
    public void setSalary(String salary) { this.salary = salary; }
    
    public ExperienceLevel getExperience() { return experience; }
    public void setExperience(ExperienceLevel experience) { this.experience = experience; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }
    
    public LocalDate getDeadline() { return deadline; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }
    
    public LocalDateTime getPostedDate() { return postedDate; }
    public void setPostedDate(LocalDateTime postedDate) { this.postedDate = postedDate; }
    
    public boolean isFeatured() { return featured; }
    public void setFeatured(boolean featured) { this.featured = featured; }
    
    public User getPostedBy() { return postedBy; }
    public void setPostedBy(User postedBy) { this.postedBy = postedBy; }
    
    public List<Application> getApplications() { return applications; }
    public void setApplications(List<Application> applications) { this.applications = applications; }
}

enum JobType {
    FULL_TIME, PART_TIME, CONTRACT, REMOTE
}

enum ExperienceLevel {
    ENTRY_LEVEL, MID_LEVEL, SENIOR_LEVEL, EXECUTIVE
}