package ro.unibuc.egv.finalProject.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ro.unibuc.egv.finalProject.models.User;
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
    @GetMapping("/signUp")
    public String signUpInit(Model model){
        System.out.println("Sign up page accessed!");
        model.addAttribute("newUser", new User());
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUpNewUser(@ModelAttribute("newUser") User newUser, Model model, RedirectAttributes redirectAttributes){
        if (userService.signUpStatus(newUser) != 0) {
            model.addAttribute("errorCode", userService.signUpStatus(newUser));
        } else {
            userService.signUp(newUser);
        }
        return "signUp";
    }

}
