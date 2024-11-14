package banquemisr.challenge05.Task.Management.System.Listener;

import banquemisr.challenge05.Task.Management.System.Entity.Task.Task;
import banquemisr.challenge05.Task.Management.System.Entity.TaskHistory;
import banquemisr.challenge05.Task.Management.System.Repository.TaskHistoryRepository;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class TaskListener {

    private static TaskHistoryRepository taskHistoryRepository;

    @Autowired
    public void setTaskHistoryRepository(TaskHistoryRepository repository) {
        TaskListener.taskHistoryRepository = repository;
    }

    @PostPersist
    public void onTaskCreate(Task task) {
        saveTaskHistory(task, "CREATED");
    }

    @PreUpdate
    public void onTaskUpdate(Task task) {
        saveTaskHistory(task, "UPDATED");
    }

    private void saveTaskHistory(Task task, String changeType) {
        TaskHistory history = TaskHistory.builder()
                .taskId(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .dueDate(task.getDueDate())
                .changeTime(LocalDateTime.now())
                .build();
        taskHistoryRepository.save(history);
    }
}

