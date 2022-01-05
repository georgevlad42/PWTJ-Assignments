package ro.unibuc.egv.finalProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.unibuc.egv.finalProject.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Integer> {

    Optional<User> findByUsername(String username);
}
