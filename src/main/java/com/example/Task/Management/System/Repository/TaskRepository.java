package com.example.Task.Management.System.Repository;

import com.example.Task.Management.System.Entity.Task.Task;
import com.example.Task.Management.System.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {
    List<Task> findByDueDateBefore(LocalDateTime date);

    List<Task> findAllByUser(User user);

    List<Task> findAllByUserAndDueDateBefore(User user, LocalDateTime date);

}
