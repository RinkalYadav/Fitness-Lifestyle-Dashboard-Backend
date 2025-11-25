package com.example.fitness.mailservice;

import com.example.fitness.entity.Workout;
import com.example.fitness.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {

    @Autowired private WorkoutRepository workoutRepository;

    public Workout addWorkout(Workout w) {
        return workoutRepository.save(w);
    }

    public List<Workout> getWorkouts(Long userId) {
        return workoutRepository.findByUserId(userId);
    }

    public Workout updateWorkout(Long id, Workout data) {
        return workoutRepository.findById(id).map(w -> {
            if (data.getType() != null) w.setType(data.getType());
            if (data.getDuration() != null) w.setDuration(data.getDuration());
            if (data.getCalories() != null) w.setCalories(data.getCalories());
            if (data.getDate() != null) w.setDate(data.getDate());
            if (data.getUserId() != null) w.setUserId(data.getUserId());
            return workoutRepository.save(w);
        }).orElse(null);
    }

    public void deleteWorkout(Long id) {
        workoutRepository.deleteById(id);
    }
}
