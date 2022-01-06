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

    public int signUpStatus(User user){
        if (!userRepository.findUserByUsername(user.getUsername()).isEmpty()){
            return 1; // "Username is already in use!"
        }
        if (!userRepository.findUserByEmail(user.getEmail()).isEmpty()){
            return 2; // "Email is already in use!"
        }
        if (!userRepository.findUserByPhoneNr(user.getPhoneNr()).isEmpty()){
            return 3; // "Phone number is already in use!"
        }
        return 0; // "Success!"
    }

    public void signUp(User user){
        userRepository.save(user);
    }

}
