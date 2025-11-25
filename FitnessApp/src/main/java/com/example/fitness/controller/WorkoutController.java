package com.example.fitness.controller;

import com.example.fitness.entity.Workout;
import com.example.fitness.mailservice.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    @Autowired private WorkoutService workoutService;

    @PostMapping
    public ResponseEntity<?> addWorkout(@RequestBody Workout workout) {
        return ResponseEntity.ok(workoutService.addWorkout(workout));
    }

    @GetMapping
    public ResponseEntity<?> getWorkouts(@RequestParam Long userId) {
        return ResponseEntity.ok(workoutService.getWorkouts(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateWorkout(@PathVariable Long id, @RequestBody Workout workout) {
        Workout updated = workoutService.updateWorkout(id, workout);
        if (updated == null) return ResponseEntity.status(404).body(Map.of("message", "Workout not found"));
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWorkout(@PathVariable Long id) {
        workoutService.deleteWorkout(id);
        return ResponseEntity.ok(Map.of("message", "Workout deleted"));
    }
}
