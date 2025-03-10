package com.nizam.megacabs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public boolean sendDriverCredentials(String to, String password) {
        try {
            logger.info("Attempting to send email to: {}", to);
            
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setSubject("Your Driver Account Credentials");
            message.setText("Welcome to MegaCabs!\n\n" +
                    "Your account has been created. Here are your login credentials:\n\n" +
                    "Email: " + to + "\n" +
                    "Password: " + password + "\n\n" +
                    "Please change your password after first login.\n\n" +
                    "Best regards,\nMegaCabs Team");

            mailSender.send(message);
            logger.info("Email sent successfully to: {}", to);
            return true;
        } catch (MailException e) {
            logger.error("Failed to send email to: {}. Error: {}", to, e.getMessage());
            return false;
        }
    }
}
