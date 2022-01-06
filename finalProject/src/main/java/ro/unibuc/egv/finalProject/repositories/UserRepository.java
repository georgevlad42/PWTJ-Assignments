package ro.unibuc.egv.finalProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.unibuc.egv.finalProject.models.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    List<User> findUserByUsername(String username);
    List<User> findUserByEmail(String email);
    List<User> findUserByPhoneNr(String phoneNr);

}