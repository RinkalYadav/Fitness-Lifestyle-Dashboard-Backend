package com.example.fitness.mailservice;

import com.example.fitness.entity.Goal;
import com.example.fitness.entity.User;
import com.example.fitness.repository.GoalRepository;
import com.example.fitness.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired private UserRepository userRepository;
    @Autowired private GoalRepository goalRepository;

    public User getProfile(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateProfile(Long id, User updated) {
        return userRepository.findById(id).map(u -> {
            if (updated.getName() != null) u.setName(updated.getName());
            if (updated.getEmail() != null) u.setEmail(updated.getEmail());
            if (updated.getRole() != null) u.setRole(updated.getRole());
            if (updated.getSpecializations() != null) u.setSpecializations(updated.getSpecializations());

            // ✅ Add these
            if (updated.getAge() != null) u.setAge(updated.getAge());
            if (updated.getHeight() != null) u.setHeight(updated.getHeight());
            if (updated.getWeight() != null) u.setWeight(updated. 	getWeight());

            return userRepository.save(u);
        }).orElse(null);
    }


    public Goal setGoal(Long userId, Goal goal) {
        goal.setUserId(userId);
        if (goal.getStatus() == null) goal.setStatus("PENDING");
        return goalRepository.save(goal);
    }

    public List<Goal> getGoals(Long userId) {
        return goalRepository.findByUserId(userId);
    }
}
