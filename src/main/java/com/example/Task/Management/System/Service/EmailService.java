package com.example.Task.Management.System.Service;

import com.example.Task.Management.System.Entity.Notification;
import com.example.Task.Management.System.Entity.Task.Task;
import com.example.Task.Management.System.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private NotificationRepository notificationRepository;

    public void sendDueSoonEmail(String toEmail, Task task) {
        String subject = "Task Due Soon: " + task.getTitle();
        String message = "Hello, your task \"" + task.getTitle() + "\" is due within 24 hours. Please check it.";

        // Send the email
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailSender.send(mailMessage);

        // Save the notification record
        Notification notification = new Notification(toEmail, subject, message, LocalDateTime.now());
        notificationRepository.save(notification);
    }
}

