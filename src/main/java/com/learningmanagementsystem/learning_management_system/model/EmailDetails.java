package com.learningmanagementsystem.learning_management_system.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmailDetails {
    private String recipient;
    private String emailBody = "Welcome you to our learning management system, where you can learn programming from basic to advance";
    private String emailSubject = "WELCOME MESSAGE";
    private String emailAttachment = "";

    public EmailDetails(String recipient) {
        this.recipient = recipient;
    }

    public EmailDetails(String recipient, String emailAttachment) {
        this.recipient = recipient;
        this.emailAttachment = emailAttachment;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getEmailAttachment() {
        return emailAttachment;
    }

    public void setEmailAttachment(String emailAttachment) {
        this.emailAttachment = emailAttachment;
    }
    
}
