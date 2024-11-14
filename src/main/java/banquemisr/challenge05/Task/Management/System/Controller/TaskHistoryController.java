package banquemisr.challenge05.Task.Management.System.Controller;

import banquemisr.challenge05.Task.Management.System.Entity.TaskHistory;
import banquemisr.challenge05.Task.Management.System.Service.TaskHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/tasks/history")
public class TaskHistoryController {

    private final TaskHistoryService taskHistoryService;

    @GetMapping("/{taskId}")
    public ResponseEntity<List<TaskHistory>> getHistoryByTaskId(@PathVariable Long taskId) {
        List<TaskHistory> historyList = taskHistoryService.getHistoryByTaskId(taskId);
        return ResponseEntity.ok(historyList);
    }
}
