package com.example.Task.Management.System.Service;

import com.example.Task.Management.System.Entity.Task.Task;
import com.example.Task.Management.System.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskNotificationService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 0 7 * * *") // Runs every day at 7 am
    public void checkTasksDueSoon() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime deadline = now.plusHours(24);

        List<Task> tasksDueSoon = taskRepository.findByDueDateBetween(now, deadline);

        tasksDueSoon.forEach(task -> {
            // Sending Email
            emailService.sendDueSoonEmail(task.getUser().getEmail(), task);

        });
    }
}

