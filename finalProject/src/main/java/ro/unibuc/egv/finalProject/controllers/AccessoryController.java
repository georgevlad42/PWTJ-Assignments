package ro.unibuc.egv.finalProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ro.unibuc.egv.finalProject.mail.MailSenderPSM;
import ro.unibuc.egv.finalProject.models.Accessory;
import ro.unibuc.egv.finalProject.models.Product;
import ro.unibuc.egv.finalProject.models.User;
import ro.unibuc.egv.finalProject.services.AccessoryService;
import ro.unibuc.egv.finalProject.services.ProductService;

import javax.servlet.http.HttpSession;

@Controller
public class AccessoryController {

    private final ProductService productService;
    private final AccessoryService accessoryService;

    public AccessoryController(ProductService productService, AccessoryService accessoryService) {
        this.productService = productService;
        this.accessoryService = accessoryService;
    }

    //region Accessories page
    @RequestMapping(value = "/store/accessories", method = RequestMethod.GET)
    public String accessoriesInit(Model model){
        System.out.println("Accessories page accessed!");
        model.addAttribute("accessoriesList", accessoryService.getAccessories());
        return "accessories";
    }

    @RequestMapping(value = "/store/accessories", method = RequestMethod.POST)
    public String buyAccessory(@RequestParam("accessoryToBuyID") Long id, RedirectAttributes redirectAttributes, HttpSession httpSession){
        Accessory accessory = accessoryService.getAccessoryByID(id);
        if (accessory.getProduct().getStatus().equals("Unavailable")){
            redirectAttributes.addFlashAttribute("errorBuyAccessory", accessory.getProduct().getName() + " is out of stock!");
            return "redirect:/store/accessories";
        }
        if (accessory.getProduct().getQuantity() >= 1) {
            accessory.getProduct().setQuantity(accessory.getProduct().getQuantity() - 1);
        }
        if (accessory.getProduct().getQuantity() == 0) {
            accessory.getProduct().setStatus("Unavailable");
        }
        accessoryService.updateAccessoryQuantity(accessory);
        redirectAttributes.addFlashAttribute("successBuyAccessory", accessory.getProduct().getName() + " has been bought successfully!");
        redirectAttributes.addFlashAttribute("checkMail", "Check your email for more details!");
        MailSenderPSM mailSenderPSM = new MailSenderPSM((User) httpSession.getAttribute("currentUser"), accessory.getProduct(), "PSM - Accessory Purchase");
        mailSenderPSM.sendMessage();
        return "redirect:/store/accessories";
    }
    //endregion

    //region Add Accessories page
    @RequestMapping(value = "/admin/add_products/add_accessories", method = RequestMethod.GET)
    public String addAccessoriesInit(Model model){
        System.out.println("Add accessories page accessed!");
        model.addAttribute("newAccessory", new Accessory());
        return "add_accessories";
    }

    @RequestMapping(value = "/admin/add_products/add_accessories", method = RequestMethod.POST)
    public String addNewAccessory(@ModelAttribute("newAccessory") Accessory newAccessory, Model model, RedirectAttributes redirectAttributes){
        if (productService.isProductNameNotUnique(newAccessory.getProduct().getName())){
            model.addAttribute("errorProductName", "Product name is already in use!");
            return "add_accessories";
        }
        accessoryService.addAccessory(newAccessory);
        redirectAttributes.addFlashAttribute("successAddAccessory", "The accessory has been added successfully!");
        return "redirect:/admin/add_products/add_accessories";
    }
    //endregion

    //region Edit Accessories page
    @RequestMapping(value = "/admin/edit_products/edit_accessories", method = RequestMethod.GET)
    public String editAccessoriesInit(Model model){
        System.out.println("Edit accessories page accessed!");
        model.addAttribute("accessoriesList", accessoryService.getAccessories());
        return "edit_accessories";
    }

    @RequestMapping(value = "/admin/edit_products/edit_accessories", method = RequestMethod.POST)
    public String editAccessory(@RequestParam("accessoryToEditID") Long id, @RequestParam("accessoryToEditProductID") Long productID,
                                @RequestParam("accessoryProductNameToEdit") String name, @RequestParam("accessoryProductPriceToEdit") Double price,
                                @RequestParam("accessoryProductQtyToEdit") Integer quantity, @RequestParam("accessoryProductDescToEdit") String description,
                                @RequestParam("accessoryTypeToEdit") String type, @RequestParam("accessoryBrandToEdit") String brand,
                                @RequestParam("accessoryCompToEdit") String compatibility, @RequestParam("accessoryColorToEdit") String color,
                                Model model, RedirectAttributes redirectAttributes){
        Accessory accessoryToEdit = new Accessory(id, new Product(productID, name, price, quantity, description, accessoryService.getAccessoryByID(id).getProduct().getStatus()), type, brand, compatibility, color);
        if (accessoryToEdit.equals(accessoryService.getAccessoryByID(accessoryToEdit.getAccessoryID()))) {
            model.addAttribute("errorEditAccessory", "There are no changes made to the selected accessory!");
            model.addAttribute("accessoriesList", accessoryService.getAccessories());
            return "edit_accessories";
        }
        if (productService.isProductNameNotUnique(accessoryToEdit.getProduct().getName()) && !accessoryToEdit.getProduct().getName().equals(accessoryService.getAccessoryByID(id).getProduct().getName())) {
            model.addAttribute("errorEditProductName", "The product name " + accessoryToEdit.getProduct().getName() + " is already in use!");
            model.addAttribute("accessoriesList", accessoryService.getAccessories());
            return "edit_accessories";
        }
        accessoryService.editAccessory(accessoryToEdit);
        redirectAttributes.addFlashAttribute("successEditAccessory", "The accessory has been updated successfully!");
        return "redirect:/admin/edit_products/edit_accessories";
    }
    //endregion

    //region Delete Accessories page
    @RequestMapping(value = "/admin/delete_products/delete_accessories", method = RequestMethod.GET)
    public String deleteAccessoriesInit(Model model){
        System.out.println("Delete accessories page accessed!");
        model.addAttribute("accessoriesList", accessoryService.getAccessories());
        return "delete_accessories";
    }

    @RequestMapping(value = "/admin/delete_products/delete_accessories", method = RequestMethod.POST)
    public String deleteAccessory(@RequestParam("accessoryToDeleteID") Long id, RedirectAttributes redirectAttributes){
        accessoryService.deleteAccessory(id);
        redirectAttributes.addFlashAttribute("successDeleteAccessory", "Accessory was deleted successfully");
        return "redirect:/admin/delete_products/delete_accessories";
    }
    //endregion

}
