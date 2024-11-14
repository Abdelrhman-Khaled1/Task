package banquemisr.challenge05.Task.Management.System.Repository;

import banquemisr.challenge05.Task.Management.System.Entity.TaskHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskHistoryRepository extends JpaRepository<TaskHistory, Long> {

    List<TaskHistory> findByTaskId(Long taskId);

}
