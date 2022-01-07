package ro.unibuc.egv.finalProject.services;

import org.springframework.stereotype.Service;
import ro.unibuc.egv.finalProject.models.User;
import ro.unibuc.egv.finalProject.repositories.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public String signIn(String username, String password) {
        if (userRepository.findUserByUsername(username).isEmpty()) return "Account doesn't exist!";
        if (userRepository.findUserByUsernameAndPassword(username, password).isEmpty()) return "Wrong password!";
        return "OK";
    }

    public boolean isUsernameUnique(String username) {
        return userRepository.findUserByUsername(username).isEmpty(); // "Username is already in use!"
    }

    public boolean isEmailUnique(String email) {
        return userRepository.findUserByEmail(email).isEmpty(); // "Email is already in use!"
    }

    public boolean isPhoneNrUnique(String phoneNr) {
        return userRepository.findUserByPhoneNr(phoneNr).isEmpty(); // "Phone number is already in use!"
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

}
