package ro.unibuc.egv.finalProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ro.unibuc.egv.finalProject.models.Address;
import ro.unibuc.egv.finalProject.models.User;
import ro.unibuc.egv.finalProject.services.AddressService;

import javax.servlet.http.HttpSession;

@Controller
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    //region Edit Address Details page
    @RequestMapping("/settings/edit_address")
    @GetMapping("/settings/edit_address")
    public String editAddressInit(Model model, HttpSession httpSession){
        System.out.println("Edit address details page accessed!");
        model.addAttribute("tempAddress", ((User) httpSession.getAttribute("currentUser")).getAddress());
        return "edit_address";
    }

    @PostMapping("/settings/edit_address")
    public String editAddress(@ModelAttribute("tempAddress") Address tempAddress, HttpSession httpSession, Model model, RedirectAttributes redirectAttributes){
        tempAddress.setAddressID(((User) httpSession.getAttribute("currentUser")).getAddress().getAddressID());
        if (tempAddress.getBuilding().equals("")){
            tempAddress.setBuilding(null);
        }
        if (tempAddress.getEntrance().equals("")){
            tempAddress.setEntrance(null);
        }
        if (tempAddress.getInterphone().equals("")){
            tempAddress.setInterphone(null);
        }
        if (!tempAddress.equals(((User) httpSession.getAttribute("currentUser")).getAddress())) {
            addressService.editAddress(tempAddress);
            redirectAttributes.addFlashAttribute("successEditAddress", "Your address details have been updated!");
            User tempUser = (User) httpSession.getAttribute("currentUser");
            tempUser.setAddress(tempAddress);
            httpSession.setAttribute("currentUser", tempUser);
            return "redirect:/settings/edit_address";
        }
        model.addAttribute("errorEditAddress", "There are no changes made to the address details!");
        return "edit_address";
    }
    //endregion

}
