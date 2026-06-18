package com.project.SmartLeave.Service;

public interface EmailService {

    void sendEmail(
            String to,
            String subject,
            String body);
}
