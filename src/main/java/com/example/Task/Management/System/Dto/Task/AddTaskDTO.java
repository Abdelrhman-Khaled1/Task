package com.example.Task.Management.System.Dto.Task;

import com.example.Task.Management.System.Entity.Task.Priority;
import com.example.Task.Management.System.Entity.Task.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTaskDTO {
    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must be less than 100 characters")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;

    @NotNull(message = "Status is required")
    private Status status;
    @NotNull(message = "Priority is required")
    private Priority priority;

    private LocalDateTime dueDate;
}
