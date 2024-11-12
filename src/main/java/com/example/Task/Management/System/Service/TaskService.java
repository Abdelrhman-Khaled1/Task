package com.example.Task.Management.System.Service;

import com.example.Task.Management.System.Dto.Task.AddTaskDTO;
import com.example.Task.Management.System.Dto.Task.TaskDto;
import com.example.Task.Management.System.Dto.Task.UpdateTaskDTO;
import com.example.Task.Management.System.Entity.Task.Task;
import com.example.Task.Management.System.Exception.TaskNotFoundException;
import com.example.Task.Management.System.Mapper.TaskMapper;
import com.example.Task.Management.System.Repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;


    public TaskDto saveTask(AddTaskDTO taskRequestDTO) {
        Task task = taskMapper.toEntity(taskRequestDTO);
        Task savedTask = taskRepository.save(task);
        return taskMapper.toDto(savedTask);
    }

    public List<TaskDto> getAllTasks() {
        return taskMapper.toDtos(taskRepository.findAll());
    }

    public TaskDto getTaskById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be greater than 0");
        }

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        return taskMapper.toDto(task);
    }

    public TaskDto updateTask(Long id, UpdateTaskDTO updateTaskDTO) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be greater than 0");
        }
        if (taskRepository.findById(id).isPresent()) {
            Task updatedTask = taskMapper.toEntity(updateTaskDTO);
            updatedTask.setId(id);
            Task patron = taskRepository.save(updatedTask);
            return taskMapper.toDto(patron);
        }
        throw new TaskNotFoundException(id);
    }

    public void deleteTask(Long id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be greater than 0");
        }

        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        taskRepository.deleteById(id);
    }

    public Task getTaskEntityById(Long taskId) {
        return taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
    }
}
