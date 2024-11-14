package com.example.Task.Management.System.Service;

import com.example.Task.Management.System.Entity.TaskHistory;
import com.example.Task.Management.System.Repository.TaskHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskHistoryService {

    private final TaskHistoryRepository taskHistoryRepository;

    public List<TaskHistory> getHistoryByTaskId(Long taskId) {
        return taskHistoryRepository.findByTaskId(taskId);
    }



}
