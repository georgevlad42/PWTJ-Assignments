package ro.unibuc.egv.finalProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.unibuc.egv.finalProject.models.Address;

public interface AddressRepository extends JpaRepository <Address, Integer> {
}
