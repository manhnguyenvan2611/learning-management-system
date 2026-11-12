package com.learningmanagementsystem.learning_management_system.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.learningmanagementsystem.learning_management_system.model.EmailDetails;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public String sendSimpleMail(EmailDetails details) {
        try {

            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getEmailBody());
            mailMessage.setSubject(details.getEmailSubject());

            mailSender.send(mailMessage);

            return "Mail Sent Successfully";

        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public String sendMailWithAttachment(EmailDetails details) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper;

        try {
            helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom(sender);
            helper.setTo(details.getRecipient());
            helper.setText(details.getEmailBody());
            helper.setSubject(details.getEmailSubject());

            File attachment = new File(details.getEmailAttachment());
            FileSystemResource file = new FileSystemResource(attachment);

            helper.addAttachment(file.getFilename(), file);
            mailSender.send(mimeMessage);

            return "Mail Sent Successfully";

        } catch (MessagingException e) {
            return e.getMessage();
        }
    }
    
}
