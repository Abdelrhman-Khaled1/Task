package banquemisr.challenge05.Task.Management.System.Repository;

import banquemisr.challenge05.Task.Management.System.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
