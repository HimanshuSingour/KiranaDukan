package com.v1.manage.v8.init.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class OtpSender {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendOtp(String toEmail, String otp) {
        // Create the message
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(toEmail  + "Ha yrr");
        message.setText("Your OTP code is: " + otp);

        // Send the message
        javaMailSender.send(message);
    }
}
