package ro.unibuc.egv.finalProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ro.unibuc.egv.finalProject.models.Accessory;
import ro.unibuc.egv.finalProject.services.AccessoryService;

@Controller
public class AccessoryController {

    private final AccessoryService accessoryService;

    public AccessoryController(AccessoryService accessoryService) {
        this.accessoryService = accessoryService;
    }

    //region Accessories page
    @RequestMapping("/store/accessories")
    @GetMapping("/store/accessories")
    public String accessoriesInit(Model model){
        System.out.println("Accessories page accessed!");
        model.addAttribute("accessoriesList", accessoryService.getAccessories());
        return "accessories";
    }

    @PostMapping("store/accessories")
    public String buyAccessory(@RequestParam("accessoryToBuyID") Long id, RedirectAttributes redirectAttributes){
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
        return "redirect:/store/accessories";
    }
    //endregion

    //region Add Accessories page
    @RequestMapping("/admin/add_products/add_accessories")
    public String addAccessoriesInit(){
        System.out.println("Add accessories page accessed!");
        return "add_accessories";
    }
    //endregion

    //region Edit Accessories page
    @RequestMapping("/admin/edit_products/edit_accessories")
    public String editAccessoriesInit(){
        System.out.println("Edit accessories page accessed!");
        return "edit_accessories";
    }
    //endregion

    //region Delete Accessories page
    @RequestMapping("/admin/delete_products/delete_accessories")
    public String deleteAccessoriesInit(){
        System.out.println("Delete accessories page accessed!");
        return "delete_accessories";
    }
    //endregion

}
