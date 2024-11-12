package com.example.Task.Management.System.Controller;

import com.example.Task.Management.System.Dto.Task.AddTaskDTO;
import com.example.Task.Management.System.Dto.Task.TaskDto;
import com.example.Task.Management.System.Dto.Task.UpdateTaskDTO;
import com.example.Task.Management.System.Service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private final TaskService taskService;


    @GetMapping
    public List<TaskDto> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long id) {
        TaskDto task = taskService.getTaskById(id);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @PostMapping
    public ResponseEntity<TaskDto> addTask(@Valid @RequestBody AddTaskDTO addTaskDto) {
        TaskDto savedPatron = taskService.saveTask(addTaskDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPatron);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updatePatron(@PathVariable Long id, @Valid @RequestBody UpdateTaskDTO updateTaskDto) {
        TaskDto updated = taskService.updateTask(id, updateTaskDto);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
