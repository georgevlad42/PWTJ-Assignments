package ro.unibuc.egv.finalProject.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.unibuc.egv.finalProject.services.UserService;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String index(){
        System.out.println("Index page accessed!");
        return "index";
    }

    @RequestMapping("/signIn")
    public String signInInit(){
        System.out.println("Sign in page accessed!");
        return "signIn";
    }

    @RequestMapping("/signUp")
    public String signUpInit(){
        System.out.println("Sign up page accessed!");
        return "signUp";
    }

}
