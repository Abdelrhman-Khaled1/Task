package banquemisr.challenge05.Task.Management.System.Service;

import banquemisr.challenge05.Task.Management.System.Repository.TaskHistoryRepository;
import banquemisr.challenge05.Task.Management.System.Entity.TaskHistory;
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
