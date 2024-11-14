package banquemisr.challenge05.Task.Management.System.Repository;

import banquemisr.challenge05.Task.Management.System.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
