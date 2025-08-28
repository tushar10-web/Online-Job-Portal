// src/main/java/com/jobportal/entity/Company.java
package com.jobportal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    private String industry;
    private String size;
    private String location;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    private int openJobs;
    private String logo;
    
    // Constructors, getters, and setters
    public Company() {}
    
    // Getters and setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }
    
    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public int getOpenJobs() { return openJobs; }
    public void setOpenJobs(int openJobs) { this.openJobs = openJobs; }
    
    public String getLogo() { return logo; }
    public void setLogo(String logo) { this.logo = logo; }
}