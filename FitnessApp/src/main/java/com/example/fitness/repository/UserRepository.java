package com.example.fitness.repository;
import com.example.fitness.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

	public interface UserRepository extends JpaRepository<User, Long> {
	    User findByName(String name);
	    User findByEmail(String email);
	    User findByResetToken(String resetToken);
	    User findByOtp(String otp);
	    User findByNameAndEmail(String name, String email);
	}

