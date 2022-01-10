package ro.unibuc.egv.finalProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ro.unibuc.egv.finalProject.models.Console;
import ro.unibuc.egv.finalProject.services.ConsoleService;

@Controller
public class ConsoleController {

    private final ConsoleService consoleService;

    public ConsoleController(ConsoleService consoleService) {
        this.consoleService = consoleService;
    }

    //region Consoles page
    @RequestMapping("/store/consoles")
    @GetMapping("store/consoles")
    public String consolesInit(Model model){
        System.out.println("Consoles page accessed!");
        model.addAttribute("consolesList", consoleService.getConsoles());
        return "consoles";
    }

    @PostMapping("store/consoles")
    public String buyConsole(@RequestParam("consoleToBuyID") Long id, RedirectAttributes redirectAttributes){
        Console console = consoleService.getConsoleByID(id);
        if (console.getProduct().getStatus().equals("Unavailable")){
            redirectAttributes.addFlashAttribute("errorBuyConsole", console.getProduct().getName() + " is out of stock!");
            return "redirect:/store/consoles";
        }
        if (console.getProduct().getQuantity() >= 1) {
            console.getProduct().setQuantity(console.getProduct().getQuantity() - 1);
        }
        if (console.getProduct().getQuantity() == 0) {
            console.getProduct().setStatus("Unavailable");
        }
        consoleService.updateConsoleQuantity(console);
        redirectAttributes.addFlashAttribute("successBuyConsole", console.getProduct().getName() + " has been bought successfully!");
        redirectAttributes.addFlashAttribute("checkMail", "Check your email for more details!");
        return "redirect:/store/consoles";
    }
    //endregion

    //region Add Consoles page
    @RequestMapping("/admin/add_products/add_consoles")
    public String addConsolesInit(){
        System.out.println("Add consoles page accessed!");
        return "add_consoles";
    }
    //endregion

    //region Edit Consoles page
    @RequestMapping("/admin/edit_products/edit_consoles")
    public String editConsolesInit(){
        System.out.println("Edit consoles page accessed!");
        return "edit_consoles";
    }
    //endregion

    //region Delete Consoles page
    @RequestMapping("/admin/delete_products/delete_consoles")
    public String deleteConsolesInit(){
        System.out.println("Delete consoles page accessed!");
        return "delete_consoles";
    }
    //endregion

}
