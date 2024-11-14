package banquemisr.challenge05.Task.Management.System.Repository;

import banquemisr.challenge05.Task.Management.System.Entity.Task.Task;
import banquemisr.challenge05.Task.Management.System.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {

    /*
    SELECT * FROM tasks
    WHERE user_id = :userId;
    */
    List<Task> findAllByUser(User user);

    /*
    SELECT * FROM tasks
    WHERE user_id = :userId
    AND due_date < :date;
    */
    List<Task> findAllByUserAndDueDateBefore(User user, LocalDateTime date);

    /*
    SELECT * FROM tasks
    WHERE due_date BETWEEN :start AND :end;
    */
    List<Task> findByDueDateBetween(LocalDateTime start, LocalDateTime end);


}
