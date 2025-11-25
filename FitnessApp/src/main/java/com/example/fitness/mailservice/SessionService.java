package com.example.fitness.mailservice;

import com.example.fitness.entity.Session;
import com.example.fitness.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {

    @Autowired private SessionRepository sessionRepository;

    public Session addSession(Session s) {
        return sessionRepository.save(s);
    }

    public List<Session> getSessions(Long userId) {
        return sessionRepository.findByUserId(userId);
    }

    public Session updateSession(Long id, Session data) {
        return sessionRepository.findById(id).map(s -> {
            if (data.getSessionType() != null) s.setSessionType(data.getSessionType());
            if (data.getDuration() != null) s.setDuration(data.getDuration());
            if (data.getNotes() != null) s.setNotes(data.getNotes());
            if (data.getDate() != null) s.setDate(data.getDate());
            if (data.getUserId() != null) s.setUserId(data.getUserId());
            return sessionRepository.save(s);
        }).orElse(null);
    }

    public void deleteSession(Long id) {
        sessionRepository.deleteById(id);
    }
}
