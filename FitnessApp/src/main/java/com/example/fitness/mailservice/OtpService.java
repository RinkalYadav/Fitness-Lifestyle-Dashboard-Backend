package com.example.fitness.mailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class OtpService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOtpEmail(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("rinkalbyadav@gmail.com"); // must be verified in Brevo
        message.setTo(toEmail);
        message.setSubject("Password Reset OTP");
        message.setText("Dear user,\n\nYour OTP for password reset is: " + otp + "\n\nRegards,\nTeam");
        mailSender.send(message);
    }
}

