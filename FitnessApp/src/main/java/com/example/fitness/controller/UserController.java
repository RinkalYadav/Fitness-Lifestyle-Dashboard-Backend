package com.example.fitness.controller;

import com.example.fitness.entity.Goal;
import com.example.fitness.entity.User;
import com.example.fitness.mailservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfile(@PathVariable Long id) {
        User user = userService.getProfile(id);
        if (user == null) return ResponseEntity.status(404).body(Map.of("message", "User not found"));
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable Long id, @RequestBody User user) {
        User updated = userService.updateProfile(id, user);
        if (updated == null) return ResponseEntity.status(404).body(Map.of("message", "User not found"));
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/{id}/goals")
    public ResponseEntity<?> setGoal(@PathVariable Long id, @RequestBody Goal goal) {
        return ResponseEntity.ok(userService.setGoal(id, goal));
    }

    @GetMapping("/{id}/goals")
    public ResponseEntity<?> getGoals(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getGoals(id));
    }
}
