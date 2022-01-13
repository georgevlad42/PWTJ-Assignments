package ro.unibuc.egv.finalProject.services;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import ro.unibuc.egv.finalProject.models.Address;
import ro.unibuc.egv.finalProject.repositories.AddressRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    @Test
    @DisplayName("Address was edited successfully")
    void testUpdateAddress(){
        // Arrange
        Address address = new Address("England", "East", "Essex", "High Street", 1, "A", "A", 1, 1, "C001", "JD10");
        Address updatedAddress = new Address(1L,"Romania", "Prahova", "Ploiesti", "Aleea Zarandului", 2, "B", "B", 2, 22, "C022", "100200");
        when(addressRepository.findById(1L)).thenReturn(Optional.of(updatedAddress));

        // Act
        addressService.editAddress(address);
        addressService.editAddress(updatedAddress);
        Optional<Address> result = addressRepository.findById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertNotEquals(address.getCountry(), result.get().getCountry());
        assertNotEquals(address.getDistrict(), result.get().getDistrict());
        assertNotEquals(address.getCity(), result.get().getCity());
        assertNotEquals(address.getStreet(), result.get().getStreet());
        assertNotEquals(address.getNumber(), result.get().getNumber());
        assertNotEquals(address.getBuilding(), result.get().getBuilding());
        assertNotEquals(address.getEntrance(), result.get().getEntrance());
        assertNotEquals(address.getFloor(), result.get().getFloor());
        assertNotEquals(address.getApartment(), result.get().getApartment());
        assertNotEquals(address.getInterphone(), result.get().getInterphone());
        assertNotEquals(address.getPostalCode(), result.get().getPostalCode());
    }

}
