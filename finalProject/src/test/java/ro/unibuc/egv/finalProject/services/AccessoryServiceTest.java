package ro.unibuc.egv.finalProject.services;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import ro.unibuc.egv.finalProject.models.Accessory;
import ro.unibuc.egv.finalProject.models.Product;
import ro.unibuc.egv.finalProject.repositories.AccessoryRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccessoryServiceTest {

    @Mock
    private AccessoryRepository accessoryRepository;

    @InjectMocks
    private AccessoryService accessoryService;

    @Test
    @DisplayName("A accessory was found by its ID")
    void testFindAccessoryByID(){
        //Arrange
        Accessory accessory = new Accessory(new Product("PS Accessory", 100.00, 100, "Accessory Description", null), "Basic Accessory", "No-Name", "Some consoles", "White");
        Accessory dbAccessory = new Accessory(1L, new Product("PS Accessory", 100.00, 100, "Accessory Description", "Available"), "Basic Accessory", "No-Name", "Some consoles", "White");
        when(accessoryRepository.findAccessoryByAccessoryID(1L)).thenReturn(dbAccessory);

        //Act
        accessoryService.addAccessory(accessory);
        Accessory result = accessoryRepository.findAccessoryByAccessoryID(dbAccessory.getAccessoryID());

        //Assert
        assertNotNull(result);
        assertEquals(dbAccessory, result);
    }

    @Test
    @DisplayName("All accessories were found")
    void testFindAllAccessories(){
        //Arrange
        Accessory accessory1 = new Accessory(1L, new Product("PS Accessory 1", 100.00, 100, "Accessory 1 Description", null), "Basic Accessory", "No-Name", "Some consoles", "White");
        Accessory accessory2 = new Accessory(2L, new Product("PS Accessory 2", 200.00, 200, "Accessory 2 Description", null), "Basic Accessory", "No-Name", "Some consoles", "White");
        Accessory accessory3 = new Accessory(3L, new Product("PS Accessory 3", 300.00, 300, "Accessory 3 Description", null), "Basic Accessory", "No-Name", "Some consoles", "White");
        Accessory dbAccessory1 = new Accessory(1L, new Product("PS Accessory 1", 100.00, 100, "Accessory 1 Description", "Available"), "Basic Accessory", "No-Name", "Some consoles", "White");
        Accessory dbAccessory2 = new Accessory(2L, new Product("PS Accessory 2", 200.00, 200, "Accessory 2 Description", "Available"), "Basic Accessory", "No-Name", "Some consoles", "White");
        Accessory dbAccessory3 = new Accessory(3L, new Product("PS Accessory 3", 300.00, 300, "Accessory 3 Description", "Available"), "Basic Accessory", "No-Name", "Some consoles", "White");
        List<Accessory> accessoryList = new ArrayList<>();
        accessoryList.add(dbAccessory1);
        accessoryList.add(dbAccessory2);
        accessoryList.add(dbAccessory3);
        when(accessoryRepository.save(accessory1)).thenReturn(dbAccessory1);
        when(accessoryRepository.save(accessory2)).thenReturn(dbAccessory2);
        when(accessoryRepository.save(accessory3)).thenReturn(dbAccessory3);
        when(accessoryRepository.findAll()).thenReturn(accessoryList);

        //Act
        accessoryService.addAccessory(accessory1);
        accessoryService.addAccessory(accessory2);
        accessoryService.addAccessory(accessory3);
        List<Accessory> result = accessoryRepository.findAll();

        //Assert
        assertNotNull(result);
        assertEquals(accessoryList, result);
    }

    @Test
    @DisplayName("No accessories were found")
    void testFindNoAccessories(){
        //Arrange
        List<Accessory> accessoryList = new ArrayList<>();
        when(accessoryRepository.findAll()).thenReturn(accessoryList);

        //Act
        List<Accessory> result = accessoryRepository.findAll();

        //Assert
        assertEquals(accessoryList, result);
    }

    @Test
    @DisplayName("Last accessory was bought successfully")
    void testBuyLastAccessory(){
        //Arrange
        Accessory beforeBuyAccessory = new Accessory(1L,new Product("PS Accessory", 100.00, 1, "Accessory Description", "Available"), "Basic Accessory", "No-Name", "Some consoles", "White");
        Accessory duringBuyAccessory = new Accessory(1L,new Product("PS Accessory", 100.00, 0, "Accessory Description", "Available"), "Basic Accessory", "No-Name", "Some consoles", "White");
        Accessory afterBuyAccessory = new Accessory(1L,new Product("PS Accessory", 100.00, 0, "Accessory Description", "Unavailable"), "Basic Accessory", "No-Name", "Some consoles", "White");
        when(accessoryRepository.save(duringBuyAccessory)).thenReturn(afterBuyAccessory);
        when(accessoryRepository.findAccessoryByAccessoryID(1L)).thenReturn(afterBuyAccessory);

        //Act
        accessoryService.updateAccessoryStatus(duringBuyAccessory);
        Accessory result = accessoryRepository.findAccessoryByAccessoryID(afterBuyAccessory.getAccessoryID());

        //Assert
        assertNotNull(result);
        assertNotEquals(beforeBuyAccessory.getProduct().getQuantity(), result.getProduct().getQuantity());
        assertNotEquals(beforeBuyAccessory.getProduct().getStatus(), result.getProduct().getStatus());
        assertEquals(duringBuyAccessory.getProduct().getQuantity(), result.getProduct().getQuantity());
        assertNotEquals(duringBuyAccessory.getProduct().getStatus(), result.getProduct().getStatus());
        assertEquals(afterBuyAccessory.getProduct().getQuantity(), result.getProduct().getQuantity());
        assertEquals(afterBuyAccessory.getProduct().getStatus(), result.getProduct().getStatus());
    }

    @Test
    @DisplayName("Accessory was created successfully")
    void testCreateAccessory(){
        //Arrange
        Accessory accessory = new Accessory(new Product("PS Accessory", 100.00, 100, "Accessory Description", null), "Basic Accessory", "No-Name", "Some consoles", "White");
        Accessory savedAccessory = new Accessory(1L, new Product("PS Accessory", 100.00, 100, "Accessory Description", "Available"), "Basic Accessory", "No-Name", "Some consoles", "White");
        when(accessoryRepository.save(accessory)).thenReturn(savedAccessory);
        when(accessoryRepository.findAccessoryByAccessoryID(1L)).thenReturn(savedAccessory);

        //Act
        accessoryService.addAccessory(accessory);
        Accessory result = accessoryRepository.findAccessoryByAccessoryID(savedAccessory.getAccessoryID());

        //Assert
        assertNotNull(result);
        assertEquals(savedAccessory, result);
    }

    @Test
    @DisplayName("Accessory was updated successfully")
    void testUpdateAccessory(){
        //Arrange
        Accessory accessory = new Accessory(new Product("PS Accessory", 100.00, 100, "Accessory Description", null), "Basic Accessory", "No-Name", "Some consoles", "White");
        Accessory updatedAccessory = new Accessory(1L, new Product("PS New Accessory", 200.00, 200, "New Accessory Description", "Available"), "Unusual Accessory", "The One", "All consoles", "Black");
        when(accessoryRepository.findAccessoryByAccessoryID(1L)).thenReturn(updatedAccessory);

        //Act
        accessoryService.addAccessory(accessory);
        accessoryService.editAccessory(updatedAccessory);
        Accessory result = accessoryRepository.findAccessoryByAccessoryID(updatedAccessory.getAccessoryID());

        //Assert
        assertNotNull(result);
        assertEquals(updatedAccessory, result);
    }

    @Test
    @DisplayName("Accessory was deleted successfully")
    void testDeleteAccessory(){
        //Arrange
        Accessory accessory = new Accessory(1L, new Product("PS Accessory", 100.00, 100, "Accessory Description", null), "Basic Accessory", "No-Name", "Some consoles", "White");
        when(accessoryRepository.findAccessoryByAccessoryID(1L)).thenReturn(null);

        //Act
        accessoryService.deleteAccessory(accessory.getAccessoryID());
        Accessory result = accessoryRepository.findAccessoryByAccessoryID(accessory.getAccessoryID());

        //Assert
        assertNull(result);
    }

}
