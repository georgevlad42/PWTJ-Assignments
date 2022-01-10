package ro.unibuc.egv.finalProject.services;

import org.springframework.stereotype.Service;
import ro.unibuc.egv.finalProject.repositories.ProductRepository;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public boolean isProductNameUnique(String productName) {
        if (productRepository.findProductByName(productName) == null) return true;
        return productName.compareTo(productRepository.findProductByName(productName).getName()) != 0;
    }
}
