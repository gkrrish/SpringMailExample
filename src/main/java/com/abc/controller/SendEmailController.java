package com.abc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.service.EmailService;

@RestController
public class SendEmailController {

	@Autowired
	private EmailService emailService;

	@GetMapping("/send-email")
	public String sendEmail() {
		emailService.sendSimpleMailMessage("recipient@example.com", "Test Email", "This is a test email from Spring Boot.");
		return "Email sent successfully!";
	}

}
