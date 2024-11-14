package com.example.Task.Management.System.Service;

import com.example.Task.Management.System.Entity.Task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendDueSoonEmail(String toEmail, Task task) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Task Due Soon: " + task.getTitle());
        message.setText("Hello, your task \"" + task.getTitle() + "\" is due within 24 hours. Please check it.");

        mailSender.send(message);
    }
}

