package ro.unibuc.egv.finalProject.repositories;

import org.springframework.stereotype.Repository;
import ro.unibuc.egv.finalProject.models.Accessory;

import java.util.Map;

@Repository
public class AccessoryRepositoryImpl implements AccessoryRepository {
    @Override
    public void save(Accessory accessory) {

    }

    @Override
    public Map<String, Accessory> findAll() {
        return null;
    }

    @Override
    public void update(Accessory accessory) {

    }

    @Override
    public void delete(Accessory accessory) {

    }
}
