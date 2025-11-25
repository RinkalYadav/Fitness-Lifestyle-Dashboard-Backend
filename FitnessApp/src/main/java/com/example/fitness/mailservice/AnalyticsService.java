package com.example.fitness.mailservice;


import com.example.fitness.entity.Meal;
import com.example.fitness.entity.Session;
import com.example.fitness.entity.Workout;
import com.example.fitness.repository.MealRepository;
import com.example.fitness.repository.SessionRepository;
import com.example.fitness.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class AnalyticsService {

    @Autowired private WorkoutRepository workoutRepository;
    @Autowired private MealRepository mealRepository;
    @Autowired private SessionRepository sessionRepository;

    public Map<String, Object> getSummary(Long userId) {
        LocalDate start = LocalDate.now().minusDays(30);
        LocalDate end = LocalDate.now();

        List<Workout> workouts = workoutRepository.findByUserIdAndDateBetween(userId, start, end);
        List<Meal> meals = mealRepository.findByUserIdAndDateBetween(userId, start, end);
        List<Session> sessions = sessionRepository.findByUserIdAndDateBetween(userId, start, end);

        int totalWorkoutMins = workouts.stream().mapToInt(w -> Optional.ofNullable(w.getDuration()).orElse(0)).sum();
        int totalWorkoutCalories = workouts.stream().mapToInt(w -> Optional.ofNullable(w.getCalories()).orElse(0)).sum();
        int totalMealsCalories = meals.stream().mapToInt(m -> Optional.ofNullable(m.getCalories()).orElse(0)).sum();
        int totalSessionMins = sessions.stream().mapToInt(s -> Optional.ofNullable(s.getDuration()).orElse(0)).sum();

        Map<String, Object> result = new HashMap<>();
        result.put("rangeStart", start);
        result.put("rangeEnd", end);
        result.put("workoutsCount", workouts.size());
        result.put("workoutMinutes", totalWorkoutMins);
        result.put("workoutCalories", totalWorkoutCalories);
        result.put("mealsCount", meals.size());
        result.put("mealsCalories", totalMealsCalories);
        result.put("sessionsCount", sessions.size());
        result.put("sessionMinutes", totalSessionMins);
        return result;
    }
}
