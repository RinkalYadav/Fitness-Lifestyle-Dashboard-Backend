package com.example.fitness.controller;


import com.example.fitness.entity.User;
import com.example.fitness.mailservice.OtpService;
import com.example.fitness.repository.UserRepository;
import com.example.fitness.security.JwtUtil;

//import com.example.fitness.security.JwtUtil;
//import com.example.fitness.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private UserRepository userRepository;
    @Autowired private BCryptPasswordEncoder passwordEncoder;
    @Autowired private JwtUtil jwtUtil;
    
    @Autowired private OtpService otpService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if ("TRAINER".equalsIgnoreCase(user.getRole())) {
            if (user.getSpecializations() == null || user.getSpecializations().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Trainer must select at least one specialization"));
            }
        } else if ("USER".equalsIgnoreCase(user.getRole())) {
            if (user.getSpecializations() == null || user.getSpecializations().split(",").length != 1) {
                return ResponseEntity.badRequest().body(Map.of("error", "User must select exactly one specialization"));
            }
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok(Map.of("message", "Registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String name = loginData.get("name");
        String email = loginData.get("email");
        String password = loginData.get("password");

        User foundUser = userRepository.findByNameAndEmail(name, email);
        if (foundUser == null || !passwordEncoder.matches(password, foundUser.getPassword())) {
            return ResponseEntity.status(403).body("Invalid credentials");
        }

        String token = jwtUtil.generateToken(foundUser.getName(), foundUser.getRole());
        return ResponseEntity.ok(Map.of(
                "token", token,
                "name", foundUser.getName(),
                "role", foundUser.getRole(),
                "specializations", foundUser.getSpecializations()
        ));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        String email = request.get("email");

        User user = userRepository.findByNameAndEmail(name, email);
        if (user == null) {
            return ResponseEntity.status(404).body(Map.of("message", "User not found"));
        }

        String otp = String.valueOf(new Random().nextInt(900000) + 100000);
        user.setOtp(otp);
        userRepository.save(user);
        
        otpService.sendOtpEmail(email, otp);

        // Return OTP in response (instead of sending email)
        return ResponseEntity.ok(Map.of(
        		 "message", "OTP sent successfully to your registered email"
        ));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> request) {
        String otp = request.get("otp");
        String newPassword = request.get("newPassword");

        User user = userRepository.findByOtp(otp);
        if (user == null) {
            return ResponseEntity.status(400).body(Map.of("message", "Invalid OTP"));
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setOtp(null);
        userRepository.save(user);

        return ResponseEntity.ok(Map.of("message", "Password reset successfully"));
    }

}

