package com.beerapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String fromAddress;

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendVerificationEmail(String toAddress, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromAddress);
        message.setTo(toAddress);
        message.setSubject("Email Verification");
        String confirmationUrl = "http://yourapp.com/api/auth/verify?token=" + token;
        String body = "Please click on the link below to verify your email:\n" + confirmationUrl;
        message.setText(body);
        try {
            javaMailSender.send(message);
        } catch (MailException e) {
            logger.error("malakia");
        }
    }
}
