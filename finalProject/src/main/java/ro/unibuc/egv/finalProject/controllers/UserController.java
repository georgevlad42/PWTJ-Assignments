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

    //region Main page
    @RequestMapping("/")
    public String index(){
        System.out.println("Index page accessed!");
        return "index";
    }
    //endregion

    //region Sign In page
    @RequestMapping("/signIn")
    public String signInInit(){
        System.out.println("Sign in page accessed!");
        return "signIn";
    }

    @PostMapping("/signIn")
    public String signInUser(@RequestParam("userToSignIn") String username, @RequestParam("passToSignIn") String password, RedirectAttributes redirectAttributes) {
        if (!userService.signIn(username, password).equals("OK")) {
            redirectAttributes.addFlashAttribute("signInCheck", userService.signIn(username, password));
            return "redirect:/signIn";
        }
        if (username.equals("adminPSM")) return "redirect:/admin";
        return "redirect:/store";
    }
    //endregion

    //region Sign Up page
    @RequestMapping("/signUp")
    @GetMapping("/signUp")
    public String signUpInit(Model model){
        System.out.println("Sign up page accessed!");
        model.addAttribute("newUser", new User());
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUpNewUser(@ModelAttribute("newUser") User newUser, Model model, RedirectAttributes redirectAttributes){
        boolean isUnique = true;
        if (!userService.isUsernameUnique(newUser.getUsername())) {
            isUnique = false;
            model.addAttribute("errorUsername", "Username is already in use!");
        }
        if (!userService.isEmailUnique(newUser.getEmail())) {
            isUnique = false;
            model.addAttribute("errorEmail", "Email is already in use!");
        }
        if (!userService.isPhoneNrUnique(newUser.getPhoneNr())) {
            isUnique = false;
            model.addAttribute("errorPhoneNr", "Phone number is already in use!");
        }
        if (isUnique) {
            userService.signUp(newUser);
            redirectAttributes.addFlashAttribute("successSignUp", "Your new account has been created! Enter your details below in order to sign in!");
            return "redirect:/signIn";
        }
        return "signUp";
    }
    //endregion

    //region Store page
    @RequestMapping("/store")
    public String storeInit(){
        System.out.println("Store page accessed!");
        return "store";
    }
    //endregion

    //region Settings page
    @RequestMapping("/settings")
    public String settingsInit(){
        System.out.println("User settings page accessed!");
        return "settings";
    }
    //endregion

    //region Edit User Details page
    @RequestMapping("/settings/edit_user")
    public String editUserInit(){
        System.out.println("Edit user details page accessed!");
        return "edit_user";
    }
    //endregion

    //region Admin page
    @RequestMapping("/admin")
    public String adminInit(){
        System.out.println("Admin page accessed!");
        return "admin";
    }
    //endregion
}
