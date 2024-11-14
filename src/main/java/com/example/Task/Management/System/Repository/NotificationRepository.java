package com.example.Task.Management.System.Repository;

import com.example.Task.Management.System.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
