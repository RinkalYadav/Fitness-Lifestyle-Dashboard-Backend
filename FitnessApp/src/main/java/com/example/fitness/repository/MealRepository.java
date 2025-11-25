package com.example.fitness.repository;

import com.example.fitness.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findByUserId(Long userId);
    List<Meal> findByUserIdAndDateBetween(Long userId, LocalDate start, LocalDate end);
}
