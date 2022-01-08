package ro.unibuc.egv.finalProject.services;

import org.springframework.stereotype.Service;
import ro.unibuc.egv.finalProject.models.Address;
import ro.unibuc.egv.finalProject.repositories.AddressRepository;

@Service
public class AddressService {

    private AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public void editAddress(Address address){
        addressRepository.save(address);
    }
}
