package com.example.fitness.controller;

import com.example.fitness.entity.Session;
import com.example.fitness.mailservice.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    @Autowired private SessionService sessionService;

    @PostMapping
    public ResponseEntity<?> addSession(@RequestBody Session session) {
        return ResponseEntity.ok(sessionService.addSession(session));
    }

    @GetMapping
    public ResponseEntity<?> getSessions(@RequestParam Long userId) {
        return ResponseEntity.ok(sessionService.getSessions(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSession(@PathVariable Long id, @RequestBody Session session) {
        Session updated = sessionService.updateSession(id, session);
        if (updated == null) return ResponseEntity.status(404).body(Map.of("message", "Session not found"));
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSession(@PathVariable Long id) {
        sessionService.deleteSession(id);
        return ResponseEntity.ok(Map.of("message", "Session deleted"));
    }
}
