package com.siwar.API_pointeuse.service.impl;

import com.siwar.API_pointeuse.security.services.MailService;
import com.siwar.API_pointeuse.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private MailService mailService;

	@Override
	public void sendResetPasswordEmail(String email, String token) {
		String url = "http://localhost:3000/changePassword?token=" + token;
		String subject = "Password Reset Request";
		String body = "<!DOCTYPE html>" +
				"<html>" +
				"<head>" +
				"<style>" +
				"body {font-family: Arial, sans-serif;}" +
				".container {max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #e0e0e0; border-radius: 10px;}" +
				".header {text-align: center; padding-bottom: 20px;}" +
				".content {padding: 20px; background-color: #f9f9f9; border-radius: 10px;}" +
				".footer {text-align: center; padding-top: 20px;}" +
				"</style>" +
				"</head>" +
				"<body>" +
				"<div class='container'>" +
				"<div class='header'>" +
				"<h2>" + subject + "</h2>" +
				"</div>" +
				"<div class='content'>" +
				"<p>Dear User,</p>" +
				"<p>You have requested to reset your password. Click the link below to reset it:</p>" +
				"<p><a href='" + url + "'>Reset Password</a></p>" +
				"<p>If you did not request a password reset, please ignore this email.</p>" +
				"</div>" +
				"<div class='footer'>" +
				"<p>Regards,<br>" +
				"Your Support Team</p>" +
				"</div>" +
				"</div>" +
				"</body>" +
				"</html>";

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject(subject);
		message.setText(body);

		try {
			mailService.sendEmail(email, subject, body);
			System.out.println("Email sent to " + email);
		} catch (Exception e) {
			System.err.println("Failed to send email: " + e.getMessage());
		}
	}
}
