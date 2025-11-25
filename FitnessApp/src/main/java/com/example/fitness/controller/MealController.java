package com.example.fitness.controller;

import com.example.fitness.entity.Meal;
import com.example.fitness.mailservice.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/meals")
public class MealController {

    @Autowired private MealService mealService;

    @PostMapping
    public ResponseEntity<?> addMeal(@RequestBody Meal meal) {
        return ResponseEntity.ok(mealService.addMeal(meal));
    }

    @GetMapping
    public ResponseEntity<?> getMeals(@RequestParam Long userId) {
        return ResponseEntity.ok(mealService.getMeals(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMeal(@PathVariable Long id, @RequestBody Meal meal) {
        Meal updated = mealService.updateMeal(id, meal);
        if (updated == null) return ResponseEntity.status(404).body(Map.of("message", "Meal not found"));
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMeal(@PathVariable Long id) {
        mealService.deleteMeal(id);
        return ResponseEntity.ok(Map.of("message", "Meal deleted"));
    }
}
