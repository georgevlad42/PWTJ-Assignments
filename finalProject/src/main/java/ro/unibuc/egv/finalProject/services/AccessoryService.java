package ro.unibuc.egv.finalProject.services;

import org.springframework.stereotype.Service;
import ro.unibuc.egv.finalProject.models.Accessory;
import ro.unibuc.egv.finalProject.repositories.AccessoryRepository;

import java.util.List;

@Service
public class AccessoryService {

    private AccessoryRepository accessoryRepository;

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
}
