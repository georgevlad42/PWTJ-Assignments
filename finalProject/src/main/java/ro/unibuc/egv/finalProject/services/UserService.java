package ro.unibuc.egv.finalProject.services;

import ro.unibuc.egv.finalProject.models.User;

public interface UserService {

    void signUp(User user);
    void signIn(User user);
    void signOut(User user);
    void deleteUser(User user);

}
