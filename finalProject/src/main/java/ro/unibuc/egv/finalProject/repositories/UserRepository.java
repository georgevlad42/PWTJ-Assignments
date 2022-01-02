package ro.unibuc.egv.finalProject.repositories;

import ro.unibuc.egv.finalProject.models.User;

public interface UserRepository {

    void save(User user);
    void logIn(User user);
    void logOut(User user);
    void delete(User user);

}
