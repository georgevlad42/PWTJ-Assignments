package ro.unibuc.egv.finalProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.unibuc.egv.finalProject.models.Address;

@Repository
public interface AddressRepository extends JpaRepository <Address, Long> {

}
