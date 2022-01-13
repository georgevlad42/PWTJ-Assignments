package ro.unibuc.egv.finalProject.services;

import org.springframework.stereotype.Service;
import ro.unibuc.egv.finalProject.models.Accessory;
import ro.unibuc.egv.finalProject.repositories.AccessoryRepository;

import java.util.List;

@Service
public class AccessoryService {

    private final AccessoryRepository accessoryRepository;

    public AccessoryService(AccessoryRepository accessoryRepository) {
        this.accessoryRepository = accessoryRepository;
    }

    public Accessory getAccessoryByID(Long id) {
        return accessoryRepository.findAccessoryByAccessoryID(id);
    }

    public List<Accessory> getAccessories(){
        return accessoryRepository.findAll();
    }

    public void updateAccessoryQuantity(Accessory accessory){
        accessoryRepository.save(accessory);
    }

    public void addAccessory(Accessory accessory){
        accessory.getProduct().setStatus("Available");
        accessoryRepository.save(accessory);
    }

    public void editAccessory(Accessory accessory){
        if (accessory.getProduct().getQuantity() > 0){
            accessory.getProduct().setStatus("Available");
        } else {
            accessory.getProduct().setStatus("Unavailable");
        }
        accessoryRepository.save(accessory);
    }

    public void deleteAccessory(Long id){
        accessoryRepository.deleteById(id);
    }
}
