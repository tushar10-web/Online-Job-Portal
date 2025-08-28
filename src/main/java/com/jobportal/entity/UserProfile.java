// src/main/java/com/jobportal/entity/UserProfile.java
package com.jobportal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_profiles")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    private String fullName;
    private String phone;
    private String jobTitle;
    private String location;
    
    @Column(columnDefinition = "TEXT")
    private String bio;
    
    private String skills;
    
    // Constructors, getters, and setters
    public UserProfile() {}
    
    // Getters and setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    
    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }
}