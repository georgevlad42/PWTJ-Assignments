package ro.unibuc.egv.finalProject.services;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import ro.unibuc.egv.finalProject.models.Product;
import ro.unibuc.egv.finalProject.repositories.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    @DisplayName("Product name is not unique")
    void testNotUniqueProductName(){
        // Arrange
        Product product = new Product("PlayStation Test", 100.00, 100, "Test Description", "Available");
        Product savedProduct = new Product(1L,"PlayStation Test", 100.00, 100, "Test Description", "Available");
        when(productRepository.findProductByName(product.getName())).thenReturn(savedProduct);

        // Act
        boolean result = productService.isProductNameNotUnique(product.getName());

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Product name is unique")
    void testUniqueProductName(){
        // Arrange
        Product product = new Product("PlayStation Test", 100.00, 100, "Test Description", "Available");
        when(productRepository.findProductByName(product.getName())).thenReturn(null);

        // Act
        boolean result = productService.isProductNameNotUnique(product.getName());

        // Assert
        assertFalse(result);
    }

}
