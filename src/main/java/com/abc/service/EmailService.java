package com.abc.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.abc.utils.EmailUtils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

	private JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String fromMail;

	@Async
	public void sendSimpleMailMessage(String to, String subject, String name) {

		try {

			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(to);
			message.setSubject(subject);
			message.setFrom(fromMail);
			message.setText(EmailUtils.getEmailVerificataionMessage(name));
			mailSender.send(message);

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment)
			throws MessagingException {

		MimeMessage message = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom("noreply@customsite.com");
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text);

		FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
		helper.addAttachment("Invoice", file);

		mailSender.send(message);
	}

}
