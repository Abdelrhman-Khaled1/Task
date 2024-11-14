package com.example.Task.Management.System;

import com.example.Task.Management.System.Entity.Task.Priority;
import com.example.Task.Management.System.Entity.Task.Status;
import com.example.Task.Management.System.Entity.Task.Task;
import com.example.Task.Management.System.Entity.User.Role;
import com.example.Task.Management.System.Entity.User.User;
import com.example.Task.Management.System.Repository.TaskRepository;
import com.example.Task.Management.System.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Override
    public void run(String... args) throws Exception {
        User user = new User(
                1L,
                "Abdelrhman",
                "Khaled",
                "user@gmail.com",
                "1234",
                Role.USER,
                null
        );

        User admin = new User(
                2L,
                "Ali",
                "Hassan",
                "admin@gmail.com",
                "1234",
                Role.ADMIN,
                null
        );

        Task task1 = new Task();
        task1.setId(1L);
        task1.setTitle("User Task Title");
        task1.setDescription("This is a sample task description");
        task1.setStatus(Status.TODO);
        task1.setPriority(Priority.HIGH);
        task1.setDueDate(LocalDateTime.now().plusDays(7));
        task1.setUser(user);

        Task task2 = new Task();
        task2.setId(2L);
        task2.setTitle("Admin Task Title");
        task2.setDescription("This is a sample task description");
        task2.setStatus(Status.IN_PROGRESS);
        task2.setPriority(Priority.MEDIUM);
        task2.setDueDate(LocalDateTime.now().plusDays(7));
        task2.setUser(admin);


        userRepository.save(user);
        userRepository.save(admin);

        taskRepository.save(task1);
        taskRepository.save(task2);


    }
}
