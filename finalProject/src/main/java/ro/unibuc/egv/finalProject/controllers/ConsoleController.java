package ro.unibuc.egv.finalProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.unibuc.egv.finalProject.services.ConsoleService;

@Controller
public class ConsoleController {

    private final ConsoleService consoleService;

    public ConsoleController(ConsoleService consoleService) {
        this.consoleService = consoleService;
    }

    //region Consoles page
    @RequestMapping("/store/consoles")
    public String consolesInit(){
        System.out.println("Consoles page accessed!");
        return "consoles";
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
