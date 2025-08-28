// src/main/java/com/jobportal/entity/User.java
package com.jobportal.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    @Enumerated(EnumType.STRING)
    private UserType userType;
    
    private LocalDateTime createdAt;
    
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserProfile profile;
    
    @OneToMany(mappedBy = "postedBy", cascade = CascadeType.ALL)
    private List<Job> postedJobs;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Application> applications;
    
    // Constructors, getters, and setters
    public User() {}
    
    public User(String name, String email, String password, UserType userType) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.createdAt = LocalDateTime.now();
    }
    
    // Getters and setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public UserType getUserType() { return userType; }
    public void setUserType(UserType userType) { this.userType = userType; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public UserProfile getProfile() { return profile; }
    public void setProfile(UserProfile profile) { this.profile = profile; }
    
    public List<Job> getPostedJobs() { return postedJobs; }
    public void setPostedJobs(List<Job> postedJobs) { this.postedJobs = postedJobs; }
    
    public List<Application> getApplications() { return applications; }
    public void setApplications(List<Application> applications) { this.applications = applications; }
}

enum UserType {
    JOB_SEEKER, EMPLOYER
}