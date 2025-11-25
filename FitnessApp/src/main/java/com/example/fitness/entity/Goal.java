package com.example.fitness.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    
    private String title;

    public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getCurrentWeight() {
		return currentWeight;
	}
	public void setCurrentWeight(Integer currentWeight) {
		this.currentWeight = currentWeight;
	}
	public Integer getTargetWeight() {
		return targetWeight;
	}
	public void setTargetWeight(Integer targetWeight) {
		this.targetWeight = targetWeight;
	}
	@Column(length = 500)
    private String description;

    private LocalDate targetDate;
    private Integer currentWeight;
    private Integer targetWeight;

    private String status; // PENDING / COMPLETED

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }	
    public void setUserId(Long userId) { this.userId = userId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getTargetDate() { return targetDate; }
    public void setTargetDate(LocalDate targetDate) { this.targetDate = targetDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
