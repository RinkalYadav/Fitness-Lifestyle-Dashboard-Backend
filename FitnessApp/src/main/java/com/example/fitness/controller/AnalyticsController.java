package com.example.fitness.controller;

import com.example.fitness.mailservice.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    @Autowired private AnalyticsService analyticsService;

    @GetMapping("/summary")
    public ResponseEntity<?> getSummary(@RequestParam Long userId) {
        return ResponseEntity.ok(analyticsService.getSummary(userId));
    }
}
