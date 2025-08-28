// src/main/java/com/jobportal/controller/AuthController.java
package com.jobportal.controller;

import com.jobportal.dto.*;
import com.jobportal.entity.User;
import com.jobportal.entity.UserProfile;
import com.jobportal.security.JwtUtils;
import com.jobportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    UserService userService;
    
    @Autowired
    PasswordEncoder encoder;
    
    @Autowired
    JwtUtils jwtUtils;
    
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        
        String jwt = jwtUtils.generateJwtToken(authentication);
        
        User user = userService.findByEmail(loginRequest.getEmail()).orElse(null);
        
        return ResponseEntity.ok(new JwtResponse(jwt, user));
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }
        
        // Create new user's account
        User user = new User(signUpRequest.getName(),
                            signUpRequest.getEmail(),
                            encoder.encode(signUpRequest.getPassword()),
                            signUpRequest.getUserType());
        
        // Create user profile
        UserProfile profile = new UserProfile();
        profile.setUser(user);
        profile.setFullName(signUpRequest.getName());
        user.setProfile(profile);
        
        userService.save(user);
        
        // Generate JWT token
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signUpRequest.getEmail(), signUpRequest.getPassword()));
        
        String jwt = jwtUtils.generateJwtToken(authentication);
        
        return ResponseEntity.ok(new JwtResponse(jwt, user));
    }
}