package ro.unibuc.egv.finalProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.unibuc.egv.finalProject.models.Product;

@Repository
public interface ProductRepository extends JpaRepository <Product, Long> {

}
