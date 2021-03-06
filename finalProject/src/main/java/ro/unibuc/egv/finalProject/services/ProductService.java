package ro.unibuc.egv.finalProject.services;

import org.springframework.stereotype.Service;
import ro.unibuc.egv.finalProject.repositories.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //region Unique Check
    public boolean isProductNameNotUnique(String productName) {
        if (productRepository.findProductByName(productName) == null) return false;
        return productName.compareTo(productRepository.findProductByName(productName).getName()) == 0;
    }
    //endregion
}
