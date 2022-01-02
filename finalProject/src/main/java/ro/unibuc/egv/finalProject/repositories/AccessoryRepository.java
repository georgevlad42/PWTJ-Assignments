package ro.unibuc.egv.finalProject.repositories;

import ro.unibuc.egv.finalProject.models.Accessory;

import java.util.Map;

public interface AccessoryRepository {

    void save (Accessory accessory);
    Map<String, Accessory> findAll();
    void update (Accessory accessory);
    void delete (Accessory accessory);

}
