package ro.unibuc.egv.finalProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ProductController {

    public ProductController() {

    }

    //region Add Products page
    @RequestMapping(value = "/admin/add_products", method = RequestMethod.GET)
    public String addProductsInit(){
        System.out.println("Add products page accessed!");
        return "add_products";
    }
    //endregion

    //region Edit Products page
    @RequestMapping(value = "/admin/edit_products", method = RequestMethod.GET)
    public String editProductsInit(){
        System.out.println("Edit products page accessed!");
        return "edit_products";
    }
    //endregion

    //region Delete Products page
    @RequestMapping(value = "/admin/delete_products", method = RequestMethod.GET)
    public String deleteProductsInit(){
        System.out.println("Delete products page accessed!");
        return "delete_products";
    }
    //endregion

}
