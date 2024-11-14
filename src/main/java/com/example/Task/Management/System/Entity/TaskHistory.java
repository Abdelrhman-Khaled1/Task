package com.example.Task.Management.System.Entity;

import com.example.Task.Management.System.Entity.Task.Priority;
import com.example.Task.Management.System.Entity.Task.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "task_history")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long taskId;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private LocalDateTime dueDate;

    private LocalDateTime changeTime;  // Timestamp of this history record
}
