package com.example.fitness.mailservice;

import com.example.fitness.entity.Schedule;
import com.example.fitness.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired private ScheduleRepository scheduleRepository;

    public Schedule addSchedule(Schedule s) {
        return scheduleRepository.save(s);
    }

    public List<Schedule> getSchedules(Long userId) {
        return scheduleRepository.findByUserId(userId);
    }

    public Schedule updateSchedule(Long id, Schedule data) {
        return scheduleRepository.findById(id).map(s -> {
            if (data.getTitle() != null) s.setTitle(data.getTitle());
            if (data.getDescription() != null) s.setDescription(data.getDescription());
            if (data.getStartTime() != null) s.setStartTime(data.getStartTime());
            if (data.getEndTime() != null) s.setEndTime(data.getEndTime());
            if (data.getUserId() != null) s.setUserId(data.getUserId());
            return scheduleRepository.save(s);
        }).orElse(null);
    }

    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }
}
