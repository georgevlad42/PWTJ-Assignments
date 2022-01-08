package ro.unibuc.egv.finalProject.services;

import org.springframework.stereotype.Service;
import ro.unibuc.egv.finalProject.models.User;
import ro.unibuc.egv.finalProject.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public String signInCheck(String username, String password) {
        if (userRepository.findUserByUsername(username) == null || username.compareTo(userRepository.findUserByUsername(username).getUsername()) != 0) return "Account doesn't exist!";
        if (password.compareTo(userRepository.findUserByUsername(username).getPassword()) != 0) return "Wrong password!";
        return "OK";
    }

    public User signIn(String username, String password) {
        return userRepository.findUserByUsernameAndPassword(username, password);
    }

    public boolean isUsernameUnique(String username) {
        if (userRepository.findUserByUsername(username) == null) return true;
        return username.compareTo(userRepository.findUserByUsername(username).getUsername()) != 0;
    }

    public boolean isEmailUnique(String email) {
        if (userRepository.findUserByEmail(email) == null) return true;
        return email.compareTo(userRepository.findUserByEmail(email).getEmail()) != 0;
    }

    public boolean isPhoneNrUnique(String phoneNr) {
        if (userRepository.findUserByPhoneNr(phoneNr) == null) return true;
        return phoneNr.compareTo(userRepository.findUserByPhoneNr(phoneNr).getPhoneNr()) != 0;
    }

    public void signUp(User user){
        if (user.getAddress().getBuilding().equals("")){
            user.getAddress().setBuilding(null);
        }
        if (user.getAddress().getEntrance().equals("")){
            user.getAddress().setEntrance(null);
        }
        if (user.getAddress().getInterphone().equals("")){
            user.getAddress().setInterphone(null);
        }
        userRepository.save(user);
    }

    public void editUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

}
