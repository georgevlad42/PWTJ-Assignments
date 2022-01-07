package ro.unibuc.egv.finalProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.unibuc.egv.finalProject.services.AddressService;

@Controller
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    //region Edit Address Details page
    @RequestMapping("/settings/edit_address")
    public String editAddressInit(){
        System.out.println("Edit address details page accessed!");
        return "edit_address";
    }
    //endregion

}
