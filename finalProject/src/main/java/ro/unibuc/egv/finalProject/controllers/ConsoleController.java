package ro.unibuc.egv.finalProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ro.unibuc.egv.finalProject.mail.MailSenderPSM;
import ro.unibuc.egv.finalProject.models.Console;
import ro.unibuc.egv.finalProject.models.Product;
import ro.unibuc.egv.finalProject.models.User;
import ro.unibuc.egv.finalProject.services.ConsoleService;
import ro.unibuc.egv.finalProject.services.ProductService;

import javax.servlet.http.HttpSession;

@Controller
public class ConsoleController {

    private final ProductService productService;
    private final ConsoleService consoleService;

    public ConsoleController(ProductService productService, ConsoleService consoleService) {
        this.productService = productService;
        this.consoleService = consoleService;
    }

    //region Consoles page
    @RequestMapping(value = "/store/consoles", method = RequestMethod.GET)
    public String consolesInit(Model model){
        System.out.println("Consoles page accessed!");
        model.addAttribute("consolesList", consoleService.getConsoles());
        return "consoles";
    }

    @RequestMapping(value = "/store/consoles", method = RequestMethod.POST)
    public String buyConsole(@RequestParam("consoleToBuyID") Long id, RedirectAttributes redirectAttributes, HttpSession httpSession){
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
        MailSenderPSM mailSenderPSM = new MailSenderPSM((User) httpSession.getAttribute("currentUser"), console.getProduct(), "PSM - Console Purchase");
        mailSenderPSM.sendMessage();
        return "redirect:/store/consoles";
    }
    //endregion

    //region Add Consoles page
    @RequestMapping(value = "/admin/add_products/add_consoles", method = RequestMethod.GET)
    public String addConsolesInit(Model model){
        System.out.println("Add consoles page accessed!");
        model.addAttribute("newConsole", new Console());
        return "add_consoles";
    }

    @RequestMapping(value = "/admin/add_products/add_consoles", method = RequestMethod.POST)
    public String addNewConsole(@ModelAttribute("newConsole") Console newConsole, Model model, RedirectAttributes redirectAttributes){
        if (productService.isProductNameNotUnique(newConsole.getProduct().getName())){
            model.addAttribute("errorProductName", "Product name is already in use!");
            return "add_consoles";
        }
        consoleService.addConsole(newConsole);
        redirectAttributes.addFlashAttribute("successAddConsole", "The console has been added successfully!");
        return "redirect:/admin/add_products/add_consoles";
    }
    //endregion

    //region Edit Consoles page
    @RequestMapping(value = "/admin/edit_products/edit_consoles", method = RequestMethod.GET)
    public String editConsolesInit(Model model){
        System.out.println("Edit consoles page accessed!");
        model.addAttribute("consolesList", consoleService.getConsoles());
        return "edit_consoles";
    }

    @RequestMapping(value = "/admin/edit_products/edit_consoles", method = RequestMethod.POST)
    public String editConsole(@RequestParam("consoleToEditID") Long id, @RequestParam("consoleToEditProductID") Long productID,
                              @RequestParam("consoleProductNameToEdit") String name, @RequestParam("consoleProductPriceToEdit") Double price,
                              @RequestParam("consoleProductQtyToEdit") Integer quantity, @RequestParam("consoleProductDescToEdit") String description,
                              @RequestParam("consoleEditionToEdit") String edition, @RequestParam("consoleGPUToEdit") String GPU,
                              @RequestParam("consoleCPUToEdit") String CPU, @RequestParam("consoleMemoryToEdit") String memory,
                              @RequestParam("consoleStorageToEdit") String storage, @RequestParam("consoleSoundToEdit") String sound,
                              @RequestParam("consoleOSToEdit") String OS, @RequestParam("consoleMediaToEdit") String media,
                              @RequestParam("consoleColorToEdit") String color, Model model, RedirectAttributes redirectAttributes){
        Console consoleToEdit = new Console(id, new Product(productID, name, price, quantity, description, consoleService.getConsoleByID(id).getProduct().getStatus()), edition, GPU, CPU, memory, storage, sound, OS, media, color);
        if (consoleToEdit.equals(consoleService.getConsoleByID(consoleToEdit.getConsoleID()))) {
            model.addAttribute("errorEditConsole", "There are no changes made to the selected console!");
            model.addAttribute("consolesList", consoleService.getConsoles());
            return "edit_consoles";
        }
        if (productService.isProductNameNotUnique(consoleToEdit.getProduct().getName()) && !consoleToEdit.getProduct().getName().equals(consoleService.getConsoleByID(id).getProduct().getName())) {
            model.addAttribute("errorEditProductName", "The product name " + consoleToEdit.getProduct().getName() + " is already in use!");
            model.addAttribute("consolesList", consoleService.getConsoles());
            return "edit_consoles";
        }
        consoleService.editConsole(consoleToEdit);
        redirectAttributes.addFlashAttribute("successEditConsole", "The console has been updated successfully!");
        return "redirect:/admin/edit_products/edit_consoles";
    }
    //endregion

    //region Delete Consoles page
    @RequestMapping(value = "/admin/delete_products/delete_consoles", method = RequestMethod.GET)
    public String deleteConsolesInit(Model model){
        System.out.println("Delete consoles page accessed!");
        model.addAttribute("consolesList", consoleService.getConsoles());
        return "delete_consoles";
    }

    @RequestMapping(value = "/admin/delete_products/delete_consoles", method = RequestMethod.POST)
    public String deleteConsole(@RequestParam("consoleToDeleteID") Long id, RedirectAttributes redirectAttributes){
        consoleService.deleteConsole(id);
        redirectAttributes.addFlashAttribute("successDeleteConsole", "Console was deleted successfully");
        return "redirect:/admin/delete_products/delete_consoles";
    }
    //endregion

}
