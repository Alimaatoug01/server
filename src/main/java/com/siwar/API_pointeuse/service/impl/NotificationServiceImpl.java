package com.siwar.API_pointeuse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.siwar.API_pointeuse.Dto.NotificationDto;
import com.siwar.API_pointeuse.service.NotificationService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
@Service
public class NotificationServiceImpl implements NotificationService {

	 @Autowired
	    private JavaMailSender emailSender;
	 
	 public void sendEmail(String to, String subject, String text) {
	        try {
	            MimeMessage message = emailSender.createMimeMessage();
	            MimeMessageHelper helper = new MimeMessageHelper(message, true);
	            helper.setTo(to);
	            helper.setSubject(subject);
	            helper.setText(text);
	            emailSender.send(message);
	           }catch (MessagingException e) {
	        	
	            }
	        }
}
