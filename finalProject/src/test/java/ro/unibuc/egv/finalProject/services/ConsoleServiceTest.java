package ro.unibuc.egv.finalProject.services;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import ro.unibuc.egv.finalProject.models.Product;
import ro.unibuc.egv.finalProject.models.Console;
import ro.unibuc.egv.finalProject.repositories.ConsoleRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ConsoleServiceTest {

    @Mock
    private ConsoleRepository consoleRepository;

    @InjectMocks
    private ConsoleService consoleService;

    @Test
    @DisplayName("A console was found by its ID")
    void testFindConsoleByID(){
        //Arrange
        Console console = new Console(new Product("PS Console", 100.00, 100, "Console Description", null), "Standard", "New GPU", "New CPU", "32GB RAM", "1TB HDD", "New Sound Card", "ConsoleOS", "Digital", "White");
        Console dbConsole = new Console(1L, new Product("PS Console", 100.00, 100, "Console Description", "Available"), "Standard", "New GPU", "New CPU", "32GB RAM", "1TB HDD", "New Sound Card", "ConsoleOS", "Digital", "White");
        when(consoleRepository.findConsoleByConsoleID(1L)).thenReturn(dbConsole);

        //Act
        consoleService.addConsole(console);
        Console result = consoleRepository.findConsoleByConsoleID(dbConsole.getConsoleID());

        //Assert
        assertNotNull(result);
        assertEquals(dbConsole, result);
    }

    @Test
    @DisplayName("All consoles were found")
    void testFindAllConsoles(){
        //Arrange
        Console console1 = new Console(1L, new Product("PS Console 1", 100.00, 100, "Console 1 Description", null), "Standard", "New GPU", "New CPU", "32GB RAM", "1TB HDD", "New Sound Card", "ConsoleOS", "Digital", "White");
        Console console2 = new Console(2L, new Product("PS Console 2", 200.00, 200, "Console 2 Description", null), "Standard", "New GPU", "New CPU", "32GB RAM", "1TB HDD", "New Sound Card", "ConsoleOS", "Digital", "White");
        Console console3 = new Console(3L, new Product("PS Console 3", 300.00, 300, "Console 3 Description", null), "Standard", "New GPU", "New CPU", "32GB RAM", "1TB HDD", "New Sound Card", "ConsoleOS", "Digital", "White");
        Console dbConsole1 = new Console(1L, new Product("PS Console 1", 100.00, 100, "Console 1 Description", "Available"), "Standard", "New GPU", "New CPU", "32GB RAM", "1TB HDD", "New Sound Card", "ConsoleOS", "Digital", "White");
        Console dbConsole2 = new Console(2L, new Product("PS Console 2", 200.00, 200, "Console 2 Description", "Available"), "Standard", "New GPU", "New CPU", "32GB RAM", "1TB HDD", "New Sound Card", "ConsoleOS", "Digital", "White");
        Console dbConsole3 = new Console(3L, new Product("PS Console 3", 300.00, 300, "Console 3 Description", "Available"), "Standard", "New GPU", "New CPU", "32GB RAM", "1TB HDD", "New Sound Card", "ConsoleOS", "Digital", "White");
        List<Console> consoleList = new ArrayList<>();
        consoleList.add(dbConsole1);
        consoleList.add(dbConsole2);
        consoleList.add(dbConsole3);
        when(consoleRepository.save(console1)).thenReturn(dbConsole1);
        when(consoleRepository.save(console2)).thenReturn(dbConsole2);
        when(consoleRepository.save(console3)).thenReturn(dbConsole3);
        when(consoleRepository.findAll()).thenReturn(consoleList);

        //Act
        consoleService.addConsole(console1);
        consoleService.addConsole(console2);
        consoleService.addConsole(console3);
        List<Console> result = consoleRepository.findAll();

        //Assert
        assertNotNull(result);
        assertEquals(consoleList, result);
    }

    @Test
    @DisplayName("No consoles were found")
    void testFindNoConsoles(){
        //Arrange
        List<Console> consoleList = new ArrayList<>();
        when(consoleRepository.findAll()).thenReturn(consoleList);

        //Act
        List<Console> result = consoleRepository.findAll();

        //Assert
        assertEquals(consoleList, result);
    }

    @Test
    @DisplayName("Last console was bought successfully")
    void testBuyLastConsole(){
        //Arrange
        Console beforeBuyConsole = new Console(1L,new Product("PS Console", 100.00, 1, "Console Description", "Available"), "Standard", "New GPU", "New CPU", "32GB RAM", "1TB HDD", "New Sound Card", "ConsoleOS", "Digital", "White");
        Console duringBuyConsole = new Console(1L,new Product("PS Console", 100.00, 0, "Console Description", "Available"), "Standard", "New GPU", "New CPU", "32GB RAM", "1TB HDD", "New Sound Card", "ConsoleOS", "Digital", "White");
        Console afterBuyConsole = new Console(1L,new Product("PS Console", 100.00, 0, "Console Description", "Unavailable"), "Standard", "New GPU", "New CPU", "32GB RAM", "1TB HDD", "New Sound Card", "ConsoleOS", "Digital", "White");
        when(consoleRepository.save(duringBuyConsole)).thenReturn(afterBuyConsole);
        when(consoleRepository.findConsoleByConsoleID(1L)).thenReturn(afterBuyConsole);

        //Act
        consoleService.updateConsoleStatus(duringBuyConsole);
        Console result = consoleRepository.findConsoleByConsoleID(afterBuyConsole.getConsoleID());

        //Assert
        assertNotNull(result);
        assertNotEquals(beforeBuyConsole.getProduct().getQuantity(), result.getProduct().getQuantity());
        assertNotEquals(beforeBuyConsole.getProduct().getStatus(), result.getProduct().getStatus());
        assertEquals(duringBuyConsole.getProduct().getQuantity(), result.getProduct().getQuantity());
        assertNotEquals(duringBuyConsole.getProduct().getStatus(), result.getProduct().getStatus());
        assertEquals(afterBuyConsole.getProduct().getQuantity(), result.getProduct().getQuantity());
        assertEquals(afterBuyConsole.getProduct().getStatus(), result.getProduct().getStatus());
    }

    @Test
    @DisplayName("Console was created successfully")
    void testCreateConsole(){
        //Arrange
        Console console = new Console(new Product("PS Console", 100.00, 100, "Console Description", null), "Standard", "New GPU", "New CPU", "32GB RAM", "1TB HDD", "New Sound Card", "ConsoleOS", "Digital", "White");
        Console savedConsole = new Console(1L, new Product("PS Console", 100.00, 100, "Console Description", "Available"), "Standard", "New GPU", "New CPU", "32GB RAM", "1TB HDD", "New Sound Card", "ConsoleOS", "Digital", "White");
        when(consoleRepository.save(console)).thenReturn(savedConsole);
        when(consoleRepository.findConsoleByConsoleID(1L)).thenReturn(savedConsole);

        //Act
        consoleService.addConsole(console);
        Console result = consoleRepository.findConsoleByConsoleID(savedConsole.getConsoleID());

        //Assert
        assertNotNull(result);
        assertEquals(savedConsole, result);
    }

    @Test
    @DisplayName("Console was updated successfully")
    void testUpdateConsole(){
        //Arrange
        Console console = new Console(new Product("PS Console", 100.00, 100, "Console Description", null), "Standard", "New GPU", "New CPU", "32GB RAM", "1TB HDD", "New Sound Card", "ConsoleOS", "Digital", "White");
        Console updatedConsole = new Console(1L, new Product("PS New Console", 200.00, 200, "New Console Description", "Available"), "Limited", "Newer GPU", "Newer CPU", "64GB RAM", "2TB HDD", "Newer Sound Card", "NewConsoleOS", "Blu-ray, Digital", "Black");
        when(consoleRepository.findConsoleByConsoleID(1L)).thenReturn(updatedConsole);

        //Act
        consoleService.addConsole(console);
        consoleService.editConsole(updatedConsole);
        Console result = consoleRepository.findConsoleByConsoleID(updatedConsole.getConsoleID());

        //Assert
        assertNotNull(result);
        assertEquals(updatedConsole, result);
    }

    @Test
    @DisplayName("Console was deleted successfully")
    void testDeleteConsole(){
        //Arrange
        Console console = new Console(1L, new Product("PS Console", 100.00, 100, "Console Description", null), "Standard", "New GPU", "New CPU", "32GB RAM", "1TB HDD", "New Sound Card", "ConsoleOS", "Digital", "White");
        when(consoleRepository.findConsoleByConsoleID(1L)).thenReturn(null);

        //Act
        consoleService.deleteConsole(console.getConsoleID());
        Console result = consoleRepository.findConsoleByConsoleID(console.getConsoleID());

        //Assert
        assertNull(result);
    }

}
