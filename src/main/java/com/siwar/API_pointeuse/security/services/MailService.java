package com.siwar.API_pointeuse.security.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailService.class);


    @Autowired
    private JavaMailSender emailSender;


    public void sendEmail(String to, String subject, String text) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper;

        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);  // Set to true to enable HTML

            emailSender.send(message);
            System.out.println("Email sent to " + to);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Failed to send email to " + to);
        }
    }

}
