package ro.unibuc.egv.finalProject.services;

import org.springframework.stereotype.Service;
import ro.unibuc.egv.finalProject.models.Address;
import ro.unibuc.egv.finalProject.repositories.AddressRepository;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    //region Edit Address
    public void editAddress(Address address){
        addressRepository.save(address);
    }
    //endregion
}
