package ro.unibuc.egv.finalProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.unibuc.egv.finalProject.models.Product;

public interface ProductRepository extends JpaRepository <Product, Integer> {
}
