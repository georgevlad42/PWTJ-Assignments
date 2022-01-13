package ro.unibuc.egv.finalProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ro.unibuc.egv.finalProject.models.User;
import ro.unibuc.egv.finalProject.services.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //region Main page
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpSession httpSession){
        System.out.println("Index page accessed!");
        httpSession.setAttribute("currentUser", null);
        return "index";
    }
    //endregion

    //region Sign In page
    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public String signInInit(){
        System.out.println("Sign in page accessed!");
        return "signIn";
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public String signInUser(@RequestParam("userToSignIn") String username, @RequestParam("passToSignIn") String password, HttpSession httpSession, RedirectAttributes redirectAttributes) {
        if (!userService.signInCheck(username, password).equals("OK")) {
            redirectAttributes.addFlashAttribute("signInCheck", userService.signInCheck(username, password));
            return "redirect:/signIn";
        }
        if (username.equals("adminPSM")) return "redirect:/admin";
        httpSession.setAttribute("currentUser", userService.signIn(username, password));
        return "redirect:/store";
    }
    //endregion

    //region Sign Up page
    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public String signUpInit(Model model){
        System.out.println("Sign up page accessed!");
        model.addAttribute("newUser", new User());
        return "signUp";
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public String signUpNewUser(@ModelAttribute("newUser") User newUser, Model model, RedirectAttributes redirectAttributes){
        boolean isUnique = true;
        if (userService.isUsernameNotUnique(newUser.getUsername())) {
            isUnique = false;
            model.addAttribute("errorUsername", "Username is already in use!");
        }
        if (userService.isEmailNotUnique(newUser.getEmail())) {
            isUnique = false;
            model.addAttribute("errorEmail", "Email is already in use!");
        }
        if (userService.isPhoneNrNotUnique(newUser.getPhoneNr())) {
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
    @RequestMapping(value = "/store", method = RequestMethod.GET)
    public String storeInit(){
        System.out.println("Store page accessed!");
        return "store";
    }
    //endregion

    //region Settings page
    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String settingsInit(){
        System.out.println("User settings page accessed!");
        return "settings";
    }
    //endregion

    //region Edit User Details page
    @RequestMapping(value = "/settings/edit_user", method = RequestMethod.GET)
    public String editUserInit(Model model, HttpSession httpSession){
        System.out.println("Edit user details page accessed!");
        model.addAttribute("tempUser", httpSession.getAttribute("currentUser"));
        return "edit_user";
    }

    @RequestMapping(value = "/settings/edit_user", method = RequestMethod.POST)
    public String editUser(@ModelAttribute("tempUser") User tempUser, HttpSession httpSession, Model model, RedirectAttributes redirectAttributes){
        boolean isUnique = true;
        tempUser.setUserID(((User) httpSession.getAttribute("currentUser")).getUserID());
        tempUser.setAddress(((User) httpSession.getAttribute("currentUser")).getAddress());
        if (tempUser.getPassword().equals("")) {
            tempUser.setPassword(((User) httpSession.getAttribute("currentUser")).getPassword());
        }
        if (tempUser.equals(httpSession.getAttribute("currentUser"))) {
            model.addAttribute("errorEditUser", "There are no changes made to the user details!");
            return "edit_user";
        }
        if (userService.isEmailNotUnique(tempUser.getEmail()) && !tempUser.getEmail().equals(((User) httpSession.getAttribute("currentUser")).getEmail())) {
            isUnique = false;
            model.addAttribute("errorEditEmail", "Email is already in use!");
        }
        if (userService.isPhoneNrNotUnique(tempUser.getPhoneNr()) && !tempUser.getPhoneNr().equals(((User) httpSession.getAttribute("currentUser")).getPhoneNr())) {
            isUnique = false;
            model.addAttribute("errorEditPhoneNr", "Phone number is already in use!");
        }
        if (isUnique) {
            userService.editUser(tempUser);
            redirectAttributes.addFlashAttribute("successEditUser", "Your user details have been updated!");
            httpSession.setAttribute("currentUser", tempUser);
            return "redirect:/settings/edit_user";
        }
        return "edit_user";
    }
    //endregion

    //region Delete Account page
    @RequestMapping(value = "/settings/delete_user", method = RequestMethod.GET)
    public String deleteAccountInit(){
        System.out.println("Delete user page accessed!");
        return "delete_user";
    }

    @RequestMapping(value = "/settings/delete_user", method = RequestMethod.POST)
    public String deleteAccount(HttpSession httpSession, RedirectAttributes redirectAttributes){
        userService.deleteUser((User) httpSession.getAttribute("currentUser"));
        redirectAttributes.addFlashAttribute("deleteMessage", "Account deleted successfully!");
        return "redirect:/";
    }
    //endregion

    //region Admin page
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminInit(){
        System.out.println("Admin page accessed!");
        return "admin";
    }
    //endregion
}
