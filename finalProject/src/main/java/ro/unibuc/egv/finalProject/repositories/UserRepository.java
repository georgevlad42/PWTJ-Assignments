package ro.unibuc.egv.finalProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.unibuc.egv.finalProject.models.User;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    User findUserByUsernameAndPassword(String username, String password);
    User findUserByUsername(String username);
    User findUserByEmail(String email);
    User findUserByPhoneNr(String phoneNr);

}