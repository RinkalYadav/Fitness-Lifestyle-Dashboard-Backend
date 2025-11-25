package com.example.fitness.mailservice;

import com.example.fitness.entity.Meal;
import com.example.fitness.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealService {

    @Autowired private MealRepository mealRepository;

    public Meal addMeal(Meal m) {
        return mealRepository.save(m);
    }

    public List<Meal> getMeals(Long userId) {
        return mealRepository.findByUserId(userId);
    }

    public Meal updateMeal(Long id, Meal data) {
        return mealRepository.findById(id).map(m -> {
            if (data.getMealType() != null) m.setMealType(data.getMealType());
            if (data.getFoodItems() != null) m.setFoodItems(data.getFoodItems());
            if (data.getCalories() != null) m.setCalories(data.getCalories());
            if (data.getDate() != null) m.setDate(data.getDate());
            if (data.getUserId() != null) m.setUserId(data.getUserId());
            return mealRepository.save(m);
        }).orElse(null);
    }

    public void deleteMeal(Long id) {
        mealRepository.deleteById(id);
    }
}
