package com.anoshindanil.restfullregistrationproject.service;

public interface EmailService {
    void sendEmail(String to, String from, String subject, String body);
}
