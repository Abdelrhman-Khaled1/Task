package com.example.Task.Management.System.Dto.Task;

import com.example.Task.Management.System.Entity.Task.Priority;
import com.example.Task.Management.System.Entity.Task.Status;
import com.example.Task.Management.System.Entity.Task.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskSearchResultDto {
    private Long id;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private LocalDateTime dueDate;
    private Long userId;
    private String userName;
    private String userEmail;

    public static TaskSearchResultDto from(Task task){
        return new TaskSearchResultDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getDueDate(),
                task.getUser().getId(),
                task.getUser().getFirstname(),
                task.getUser().getEmail()
        );
    }
}
