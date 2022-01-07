package ro.unibuc.egv.finalProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.unibuc.egv.finalProject.services.ProductService;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //region Add Products page
    @RequestMapping("/admin/add_products")
    public String addProductsInit(){
        System.out.println("Add products page accessed!");
        return "add_products";
    }
    //endregion

    //region Edit Products page
    @RequestMapping("/admin/edit_products")
    public String editProductsInit(){
        System.out.println("Edit products page accessed!");
        return "edit_products";
    }
    //endregion

    //region Delete Products page
    @RequestMapping("/admin/delete_products")
    public String deleteProductsInit(){
        System.out.println("Delete products page accessed!");
        return "delete_products";
    }
    //endregion

}
