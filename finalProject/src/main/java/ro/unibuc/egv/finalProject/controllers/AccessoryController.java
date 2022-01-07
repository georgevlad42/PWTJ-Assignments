package ro.unibuc.egv.finalProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.unibuc.egv.finalProject.services.AccessoryService;

@Controller
public class AccessoryController {

    private final AccessoryService accessoryService;

    public AccessoryController(AccessoryService accessoryService) {
        this.accessoryService = accessoryService;
    }

    //region Accessories page
    @RequestMapping("/store/accessories")
    public String accessoriesInit(){
        System.out.println("Accessories page accessed!");
        return "accessories";
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
