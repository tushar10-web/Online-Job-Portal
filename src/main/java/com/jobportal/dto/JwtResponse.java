// src/main/java/com/jobportal/dto/JwtResponse.java
package com.jobportal.dto;

import com.jobportal.entity.User;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private User user;
    
    public JwtResponse(String accessToken, User user) {
        this.token = accessToken;
        this.user = user;
    }
    
    // Getters and setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}