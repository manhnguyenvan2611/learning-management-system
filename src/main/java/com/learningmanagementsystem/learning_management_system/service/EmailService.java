package com.learningmanagementsystem.learning_management_system.service;

import com.learningmanagementsystem.learning_management_system.model.EmailDetails;

public interface EmailService {
    // Method to send simple email
    String sendSimpleMail(EmailDetails details);

    // Method to send email with attachment
    String sendMailWithAttachment(EmailDetails details);
}
