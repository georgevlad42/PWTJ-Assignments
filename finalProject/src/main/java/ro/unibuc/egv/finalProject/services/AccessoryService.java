package ro.unibuc.egv.finalProject.services;

import ro.unibuc.egv.finalProject.models.Accessory;

import java.util.Map;

public interface AccessoryService {

    void addAccessory (Accessory accessory);
    Map<String, Accessory> showAccessories();
    void updateAccessory (Accessory accessory);
    void deleteAccessory (Accessory accessory);

}
