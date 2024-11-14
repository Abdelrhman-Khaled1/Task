package com.example.Task.Management.System.Controller;

import com.example.Task.Management.System.Dto.Page.PageDto;
import com.example.Task.Management.System.Dto.Search.TaskSearchDTOUser;
import com.example.Task.Management.System.Dto.Task.AddTaskDTO;
import com.example.Task.Management.System.Dto.Task.TaskDto;
import com.example.Task.Management.System.Dto.Search.TaskSearchDTOAdmin;
import com.example.Task.Management.System.Dto.Search.TaskSearchResultDto;
import com.example.Task.Management.System.Dto.Task.UpdateTaskDTO;
import com.example.Task.Management.System.Service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private final TaskService taskService;


    @GetMapping
    public List<TaskDto> getMyTasks() {
        return taskService.getAllTaskForLoggedInUser();
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
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @Valid @RequestBody UpdateTaskDTO updateTaskDto) {
        TaskDto updated = taskService.updateTask(id, updateTaskDto);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}/status")
    public TaskDto changeTaskStatus(@PathVariable Long id, @RequestParam String newStatus) {
        return taskService.changeStatus(id, newStatus);
    }

    @PutMapping("/{id}/priority")
    public TaskDto changeTaskPriority(@PathVariable Long id, @RequestParam String newPriority) {
        return taskService.changePriority(id, newPriority);
    }

    @GetMapping("/before")
    public List<TaskDto> getMyTasksBeforeDueDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return taskService.getTasksDueDate(date);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/all")
    public ResponseEntity<PageDto<TaskDto>> adminGetAllTasks(@RequestParam int page, @RequestParam int size) {
        PageDto<TaskDto> tasks = taskService.adminGetAllTasks(page, size);
        return ResponseEntity.ok(tasks);
    }



    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/search")
    public ResponseEntity<List<TaskSearchResultDto>> adminSearch(@RequestBody TaskSearchDTOAdmin searchDTO) {
        return new ResponseEntity<>(taskService.adminSearchTasks(searchDTO), HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<TaskDto>> userSearch(@RequestBody TaskSearchDTOUser searchDTO) {
        return new ResponseEntity<>(taskService.userSearchTasks(searchDTO), HttpStatus.OK);
    }

}
