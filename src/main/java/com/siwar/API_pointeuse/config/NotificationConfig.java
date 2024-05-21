package com.siwar.API_pointeuse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class NotificationConfig {
	
	 @Bean
	    public JavaMailSender javaMailSender() {
	        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	        mailSender.setHost("smtp.example.com");
	        mailSender.setPort(587);
	        mailSender.setUsername("myusername");
	        mailSender.setPassword("mypassword");
	        return mailSender;
	    }

}
