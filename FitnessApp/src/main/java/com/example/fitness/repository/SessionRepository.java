package com.example.fitness.repository;

import com.example.fitness.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByUserId(Long userId);
    List<Session> findByUserIdAndDateBetween(Long userId, LocalDate start, LocalDate end);
}
